package com.mtons.Khamonline.web.controller.site.auth;

import com.mtons.Khamonline.modules.service.SecurityCodeService;
import com.mtons.Khamonline.modules.service.UserService;
import com.mtons.Khamonline.base.lang.Consts;
import com.mtons.Khamonline.base.lang.Result;
import com.mtons.Khamonline.modules.data.UserVO;
import com.mtons.Khamonline.web.controller.BaseController;
import com.mtons.Khamonline.web.controller.site.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author langhsu on 2015/8/14.
 */
@Controller
public class ForgotController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private SecurityCodeService securityCodeService;

    @GetMapping("/forgot")
    public String view() {
        return view(Views.FORGOT);
    }

    @PostMapping("/forgot")
    public String reset(String email, String code, String password, ModelMap model) {
        String view = view(Views.FORGOT);
        try {
            Assert.hasLength(email, "Vui lòng nhập địa chỉ email");
            Assert.hasLength(code, "vui lòng nhập mã xác nhận");
            UserVO user = userService.getByEmail(email);
            Assert.notNull(user, "Tài khoản không tồn tại");

            securityCodeService.verify(String.valueOf(user.getId()), Consts.CODE_FORGOT, code);
            userService.updatePassword(user.getId(), password);
            model.put("data", Result.successMessage("Xin chúc mừng, đặt lại mật khẩu thành công"));
            view = view(Views.LOGIN);
        } catch (Exception e) {
            model.put("data", Result.failure(e.getMessage()));
        }
        return view;
    }
}
