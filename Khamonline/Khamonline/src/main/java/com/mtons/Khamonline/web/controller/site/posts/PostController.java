/**
 *
 */
package com.mtons.Khamonline.web.controller.site.posts;

import com.mtons.Khamonline.modules.data.OrderDetail;
import com.mtons.Khamonline.modules.data.PaymentServices;
import com.mtons.Khamonline.modules.data.PostVO;
import com.mtons.Khamonline.modules.service.ChannelService;
import com.mtons.Khamonline.modules.service.PostService;
import com.mtons.Khamonline.base.lang.Consts;
import com.mtons.Khamonline.base.lang.Result;
import com.mtons.Khamonline.modules.data.AccountProfile;
import com.mtons.Khamonline.web.controller.BaseController;
import com.mtons.Khamonline.web.controller.site.Views;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * PostController
 *
 */
@Controller
@RequestMapping("/post")
public class PostController extends BaseController {
    @Autowired
    private PostService postService;
    @Autowired
    private ChannelService channelService;


    /**
     * Đăng Form khám bệnh
     * @return
     */
    @GetMapping("/editing")
    public String view(Long id, ModelMap model) {
        model.put("channels", channelService.findAll(Consts.STATUS_NORMAL));
        model.put("editing", true);
        String editor = siteOptions.getValue("editor");
        if (null != id && id > 0) {
            AccountProfile profile = getProfile();
            PostVO view = postService.get(id);

            Assert.notNull(view, "Bài báo đã bị xóa");
            Assert.isTrue(view.getAuthorId() == profile.getId(), "Bài viết này không thuộc về bạn");

            Assert.isTrue(view.getChannel().getStatus() == Consts.STATUS_NORMAL, "Vui lòng chỉnh sửa bài viết này trong nền");
            model.put("view", view);

            if (StringUtils.isNoneBlank(view.getEditor())) {
                editor = view.getEditor();
            }
        }
        model.put("editor", editor);
        return view(Views.POST_EDITING);
    }


    /**
     * @return
     */
    @PostMapping("/submit")
    public String post(PostVO post, HttpServletResponse response, HttpSession session, ModelMap model) {
        Assert.notNull(post, "Thông số không đầy đủ");
        Assert.state(StringUtils.isNotBlank(post.getTitle()), "Tiêu đề không được để trống");
        Assert.state(StringUtils.isNotBlank(post.getTelephone()), "Số điện thoại không được để trống");
        Assert.state(StringUtils.isNotBlank(post.getContent()), "nội dung không được để trống");

        AccountProfile profile = getProfile();
        post.setAuthorId(profile.getId());
        String product = "Thanh toan cho đơn khám";
        String subtotal = "0";
        String shipping = "0";
        String tax = "0";
        String total = "10";
        OrderDetail orderDetail = new OrderDetail(product, subtotal, shipping, tax, total);
        try {
            String error = postService.validatePostData(post.getAuthorId(), post.getNgaykham());
            if (error != null) {
            	model.addAttribute("error", error);
                model.addAttribute("view", post);
            	return view(Views.POST_EDITING);
			}

            // When modifying, verify ownership
            if (post.getId() > 0) {
                PostVO exist = postService.get(post.getId());
                Assert.notNull(exist, "Bài báo không tồn tại");
                Assert.isTrue(exist.getAuthorId() == profile.getId(), "Bài viết này không thuộc về bạn");

                postService.update(post);
            } else {

                PaymentServices paymentServices = new PaymentServices();
                String approvalLink = paymentServices.authorizePayment(orderDetail);

                response.sendRedirect(approvalLink);
                post.setStatus(4);
                long postId = postService.post(post); // 2 delete
                session.setAttribute("postId", postId);


            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return String.format(Views.REDIRECT_USER_HOME, profile.getId());
    }

    /**
     * @return
     */
    @RequestMapping("/delete/{id}")
    @ResponseBody
    public Result delete(@PathVariable Long id) {
        Result data;
        try {
            postService.delete(id, getProfile().getId());
            data = Result.success();
        } catch (Exception e) {
            data = Result.failure(e.getMessage());
        }
        return data;
    }

}
