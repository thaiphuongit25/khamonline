/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package com.mtons.Khamonline.web.controller;

import com.mtons.Khamonline.base.storage.StorageFactory;
import com.mtons.Khamonline.web.formatter.StringEscapeEditor;
import com.mtons.Khamonline.base.lang.Result;
//import com.mtons.Khamonline.base.storage.StorageFactory;
import com.mtons.Khamonline.base.utils.MD5;
import com.mtons.Khamonline.config.SiteOptions;
import com.mtons.Khamonline.modules.data.AccountProfile;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author langhsu
 * @since 3.0
 */
@Slf4j
public class BaseController {
    @Autowired
    protected StorageFactory storageFactory;
    @Autowired
    protected SiteOptions siteOptions;

    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        /**
         * Tự động chuyển đổi định dạng trường của loại ngày
         */
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));

        /**
         * Ngăn chặn các cuộc tấn công XSS
         */
        binder.registerCustomEditor(String.class, new StringEscapeEditor(true, false));
    }

    /**
     * Nhận thông tin đăng nhập
     *
     * @return
     */
    protected AccountProfile getProfile() {
        Subject subject = SecurityUtils.getSubject();
        return (AccountProfile) subject.getPrincipal();
    }

    protected boolean isAdmin() {
        Subject subject = SecurityUtils.getSubject();
        return subject.hasRole("admin");
    }

    protected void putProfile(AccountProfile profile) {
        SecurityUtils.getSubject().getSession(true).setAttribute("profile", profile);
    }

    protected boolean isAuthenticated() {
        return SecurityUtils.getSubject() != null && (SecurityUtils.getSubject().isAuthenticated() || SecurityUtils.getSubject().isRemembered());
    }

    protected PageRequest wrapPageable() {
        return wrapPageable(null);
    }

    protected PageRequest wrapPageable(Sort sort) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize", 10);
        int pageNo = ServletRequestUtils.getIntParameter(request, "pageNo", 1);

        if (null == sort) {
            sort = Sort.unsorted();
        }
        return PageRequest.of(pageNo - 1, pageSize, sort);
    }

    /**
     * @return
     */
    protected PageRequest wrapPageable(Integer pn, Integer pageSize) {
        if (pn == null || pn == 0) {
            pn = 1;
        }
        if (pageSize == null || pageSize == 0) {
            pageSize = 10;
        }
        return PageRequest.of(pn - 1, pageSize);
    }

    protected String view(String view) {
        return "/" + siteOptions.getValue("theme") + view;
    }

    protected Result<AccountProfile> executeLogin(String username, String password, boolean rememberMe) {
        Result<AccountProfile> ret = Result.failure("Login failed");

        if (StringUtils.isAnyBlank(username, password)) {
            return ret;
        }

        UsernamePasswordToken token = new UsernamePasswordToken(username, MD5.md5(password), rememberMe);

        try {
            SecurityUtils.getSubject().login(token);
            ret = Result.success(getProfile());
        } catch (UnknownAccountException e) {
            log.error(e.getMessage());
            ret = Result.failure("người dùng không tồn tại");
        } catch (LockedAccountException e) {
            log.error(e.getMessage());
            ret = Result.failure("Người dùng bị vô hiệu hóa");
        } catch (AuthenticationException e) {
            log.error(e.getMessage());
            ret = Result.failure("xác thực người dùng thất bại");
        }
        return ret;
    }
}
