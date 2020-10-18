package com.mtons.Khamonline.modules.service;


import com.mtons.Khamonline.modules.data.view.PaymentHistoryView;
import com.mtons.Khamonline.modules.entity.PaymentHistory;
import com.paypal.api.payments.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaymentHistoryService {
    PaymentHistory create(String paymentId, long postId, int status, Transaction transaction);
    Page<PaymentHistoryView> findAll(Pageable page);
}
