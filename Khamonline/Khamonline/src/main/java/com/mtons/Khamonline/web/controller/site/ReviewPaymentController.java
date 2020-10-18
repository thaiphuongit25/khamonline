package com.mtons.Khamonline.web.controller.site;

import com.mtons.Khamonline.modules.data.AccountProfile;
import com.mtons.Khamonline.modules.data.PaymentServices;
import com.mtons.Khamonline.modules.data.PostVO;
import com.mtons.Khamonline.modules.service.PaymentHistoryService;
import com.mtons.Khamonline.modules.service.PostService;
import com.mtons.Khamonline.web.controller.BaseController;
import com.paypal.api.payments.*;
import com.paypal.base.rest.PayPalRESTException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ReviewPaymentController extends BaseController {

    @Autowired
    private PostService postService;

    @Autowired
    private PaymentHistoryService paymentHistoryService;

    @RequestMapping(value= {"/review_payment"})
    public String root(HttpServletRequest request, HttpServletResponse response, ModelMap model, HttpSession session) {
        String paymentId = request.getParameter("paymentId");
        String payerId = request.getParameter("PayerID");

        try {
            PaymentServices paymentServices = new PaymentServices();
            Payment payment = paymentServices.getPaymentDetails(paymentId);

            PayerInfo payerInfo = payment.getPayer().getPayerInfo();
            Transaction transaction = payment.getTransactions().get(0);
            ShippingAddress shippingAddress = transaction.getItemList().getShippingAddress();

            request.setAttribute("payer", payerInfo);
            request.setAttribute("transaction", transaction);
            session.setAttribute("transaction", transaction);
            request.setAttribute("shippingAddress", shippingAddress);

            //String url = "review.jsp?paymentId=" + paymentId + "&PayerID=" + payerId;
            Map<String, String> param = new HashMap<>();
            param.put("paymentId", paymentId);
            param.put("PayerID", payerId);
            model.put("payer", payerInfo);
            model.put("transaction", transaction);
            model.put("shippingAddress", shippingAddress);
            model.put("param", param);
            //request.getRequestDispatcher(Views.REVIEW_PAYMENT).forward(request, response);
            return view(Views.REVIEW_PAYMENT);

        } catch (PayPalRESTException ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    @PostMapping("/execute_payment")
    protected String doPost(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String paymentId = request.getParameter("paymentId");
        String payerId = request.getParameter("PayerID");
        long postId = Long.parseLong(session.getAttribute("postId").toString());
        Transaction transaction = (Transaction) session.getAttribute("transaction");
        AccountProfile profile = getProfile();


        try {
            PaymentServices paymentServices = new PaymentServices();
            Payment payment = paymentServices.executePayment(paymentId, payerId);
            PayerInfo payerInfo = payment.getPayer().getPayerInfo();
            transaction = payment.getTransactions().get(0);
            // Update post status
            updateOrderStatus(postId);
            paymentHistoryService.create(paymentId, postId, 0, transaction);
            request.setAttribute("payer", payerInfo);
            request.setAttribute("transaction", transaction);
            request.setAttribute("message", "Thanh toán thành công");

        } catch (Exception ex) {
            paymentHistoryService.create(paymentId, postId, 1, transaction);
            request.setAttribute("error", "Thanh toán thất bại");
            request.setAttribute("errorMessage", ex.getMessage());

            ex.printStackTrace();
        }
        session.removeAttribute("transaction");
        return String.format(Views.REDIRECT_USER_HOME, profile.getId());
    }

    private void updateOrderStatus(long orderId) {
        PostVO post = postService.get(orderId);
        post.setStatus(0);
        postService.update(post);
    }

}
