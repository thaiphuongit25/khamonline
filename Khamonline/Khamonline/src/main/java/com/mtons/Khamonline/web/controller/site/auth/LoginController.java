/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package com.mtons.Khamonline.web.controller.site.auth;

import com.mtons.Khamonline.base.lang.Result;
import com.mtons.Khamonline.modules.data.AccountProfile;
import com.mtons.Khamonline.web.controller.BaseController;
import com.mtons.Khamonline.web.controller.site.Views;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author langhsu
 */
@Controller
public class LoginController extends BaseController {

    /**
     * @return
     */
	@GetMapping(value = "/login")
	public String view() {
		return view(Views.LOGIN);
	}

    /**
     * @param model
     * @return
     */
	@PostMapping(value = "/login")
	public String login(String username,
                        String password,
                        @RequestParam(value = "rememberMe",defaultValue = "0") Boolean rememberMe,
                        ModelMap model) {
		String view = view(Views.LOGIN);

        Result<AccountProfile> result = executeLogin(username, password, rememberMe);

        if (result.isOk()) {
            view = String.format(Views.REDIRECT_USER_HOME, result.getData().getId());
        } else {
            model.put("message", result.getMessage());
        }
        return view;
	}

}
