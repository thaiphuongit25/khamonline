package com.mtons.Khamonline.modules.hook.interceptor.impl;

import com.mtons.Khamonline.modules.data.PostVO;
import com.mtons.Khamonline.modules.hook.interceptor.InterceptorHookSupport;
import com.mtons.Khamonline.web.controller.site.ChannelController;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Beldon 2015/10/31
 */
@Component
public class ViewCopyrightPugin extends InterceptorHookSupport {

    @Override
    public String[] getInterceptor() {
        return new String[]{ChannelController.class.getName()};
    }

    @Override
    public void preHandle(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler) throws Exception {

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler, ModelAndView modelAndView) throws Exception {
        PostVO ret = (PostVO) modelAndView.getModel().get("view");
        Object editing = modelAndView.getModel().get("editing");
        if (null == editing && ret != null) {
            String content = ret.getContent();
            if (!content.contains("@Khamonline2020")) {
                content += "<br/><p class=\"copyright\">@khamonline2020</p>";
                ret.setContent(content);
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler, Exception ex) throws Exception {

    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler) throws Exception {

    }
}
