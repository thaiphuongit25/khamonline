package com.mtons.Khamonline.modules.hook.interceptor;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Beldon 2015/10/30
 */
public interface InterceptorHook {

    /**
     * <p>
     *
     * @return
     */
    String[] getInterceptor();

    void preHandle(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler) throws Exception;

    void postHandle(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler, ModelAndView modelAndView) throws Exception;

    void afterCompletion(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler, Exception ex) throws Exception;

    void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler) throws Exception;
}
