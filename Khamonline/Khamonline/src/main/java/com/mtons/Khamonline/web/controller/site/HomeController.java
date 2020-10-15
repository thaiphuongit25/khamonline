package com.mtons.Khamonline.web.controller.site;

import com.mtons.Khamonline.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */
@Controller
public class HomeController extends BaseController {
    @RequestMapping(value= {"/", "/home"})
    public String root(ModelMap model, HttpServletRequest request) {

        return view(Views.HOME);
    }
}
