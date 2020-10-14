package com.mtons.Khamonline.modules.hook.interceptor;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PreDestroy;

/**
 *
 * @author Beldon 2015/10/30
 */
public abstract class InterceptorHookSupport implements InterceptorHook {
    @Autowired
    protected InterceptorHookManager interceptorHookManager;

    @PreDestroy
    public void destroy() {
        interceptorHookManager.removeInterceptorHook(this);
    }
}
