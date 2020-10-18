package com.mtons.Khamonline.modules.service.impl;

import com.mtons.Khamonline.modules.data.view.PaymentHistoryView;
import com.mtons.Khamonline.modules.entity.PaymentHistory;
import com.mtons.Khamonline.modules.entity.Post;
import com.mtons.Khamonline.modules.entity.User;
import com.mtons.Khamonline.modules.repository.PaymentHistoryRepository;
import com.mtons.Khamonline.modules.repository.PostRepository;
import com.mtons.Khamonline.modules.repository.UserRepository;
import com.mtons.Khamonline.modules.service.PaymentHistoryService;
import com.paypal.api.payments.Transaction;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PaymentHistoryServiceImpl implements PaymentHistoryService {

    @Autowired
    PaymentHistoryRepository paymentHistoryRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public PaymentHistory create(String paymentId, long postId, int status, Transaction transaction) {
        PaymentHistory paymentHistory = init(paymentId, postId, status, transaction);

        return paymentHistoryRepository.save(paymentHistory);
    }

    @Override
    public Page<PaymentHistoryView> findAll(Pageable page) {
        Page<PaymentHistory> pages = paymentHistoryRepository.findAll(page);
        List<PaymentHistory> paymentHistories = pages.get().collect(Collectors.toList());
        List<PaymentHistoryView> paymentHistoryViews = new ArrayList<>();
        PaymentHistoryView paymentHistoryView = null;
        for(PaymentHistory paymentHistory : paymentHistories) {
            paymentHistoryView = new PaymentHistoryView();
            BeanUtils.copyProperties(paymentHistory, paymentHistoryView);
            Post post = postRepository.getOne(paymentHistoryView.getPostId());
            paymentHistoryView.setPost(post);
            User user = userRepository.getOne(post.getAuthorId());
            paymentHistoryView.setUser(user);
            paymentHistoryViews.add(paymentHistoryView);
        }
        return new PageImpl<PaymentHistoryView>(paymentHistoryViews, pages.getPageable(), pages.getTotalElements());
    }

    private PaymentHistory init(String paymentId, long postId, int status, Transaction transaction) {
        PaymentHistory paymentHistory = new PaymentHistory();
        paymentHistory.setPostId(postId);
        if (transaction != null) {
            paymentHistory.setAmount(new BigDecimal(Double.parseDouble(transaction.getAmount().getTotal())));
            paymentHistory.setCurrency(transaction.getAmount().getCurrency());
        }

        paymentHistory.setInvoiceId(paymentId);
        paymentHistory.setStatus(status);
        paymentHistory.setCreatedAt(Calendar.getInstance().getTime());
        paymentHistory.setUpdatedAt(Calendar.getInstance().getTime());
        return paymentHistory;
    }
}
