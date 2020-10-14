/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package com.mtons.Khamonline.web.controller.site.user;

import com.mtons.Khamonline.modules.service.MessageService;
import com.mtons.Khamonline.modules.service.UserService;
import com.mtons.Khamonline.base.lang.MtonsException;
import com.mtons.Khamonline.modules.data.AccountProfile;
import com.mtons.Khamonline.web.controller.BaseController;
import com.mtons.Khamonline.web.controller.site.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author langhsu
 */
@Controller
@RequestMapping("/users")
public class UsersController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private MessageService messageService;

    /**
     */
    @GetMapping(value = "/{userId}")
    public String posts(@PathVariable(value = "userId") Long userId,
                        ModelMap model, HttpServletRequest request) {
        return method(userId, Views.METHOD_POSTS, model, request);
    }

    /**
     * @return template name
     */
    @GetMapping(value = "/{userId}/{method}")
    public String method(@PathVariable(value = "userId") Long userId,
                         @PathVariable(value = "method") String method,
                         ModelMap model, HttpServletRequest request) {
        model.put("pageNo", ServletRequestUtils.getIntParameter(request, "pageNo", 1));
        AccountProfile profile = getProfile();
        if (null == profile || profile.getId() != userId && !isAdmin()) {
            throw new MtonsException("Bạn không có quyền truy cập trang này");
        }
        // Truy cập trang tin nhắn, đánh giá đăng nhập
        if (Views.METHOD_MESSAGES.equals(method)) {
            // Đánh dấu là đã đọc
            if (null == profile || profile.getId() != userId && !isAdmin()) {
                throw new MtonsException("Bạn không có quyền truy cập trang này");
            }
            messageService.readed4Me(profile.getId());
        }

        initUser(userId, model);
        return view(String.format(Views.USER_METHOD_TEMPLATE, method));
    }

    private void initUser(long userId, ModelMap model) {
        model.put("user", userService.get(userId));
        boolean owner = false;

        AccountProfile profile = getProfile();
        if (null != profile && profile.getId() == userId) {
            owner = true;
            putProfile(userService.findProfile(profile.getId()));
        }
        model.put("owner", owner);
    }

}
