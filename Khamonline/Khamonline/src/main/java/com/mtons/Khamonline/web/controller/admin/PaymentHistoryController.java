package com.mtons.Khamonline.web.controller.admin;

import com.mtons.Khamonline.base.lang.Consts;
import com.mtons.Khamonline.modules.data.PostVO;
import com.mtons.Khamonline.modules.data.view.PaymentHistoryView;
import com.mtons.Khamonline.modules.entity.PaymentHistory;
import com.mtons.Khamonline.modules.service.PaymentHistoryService;
import com.mtons.Khamonline.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/payment")
public class PaymentHistoryController extends BaseController {
    @Autowired
    PaymentHistoryService paymentHistoryService;
    @RequestMapping("/history")
    public String list(ModelMap model, HttpServletRequest request) {

        Pageable pageable = wrapPageable(Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<PaymentHistoryView> page = paymentHistoryService.findAll(pageable);
        model.put("page", page);

        model.put("paymentHistoryViews", page.get().collect(Collectors.toList()));
        return "/admin/payment/history";
    }
}
