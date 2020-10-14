package com.mtons.Khamonline.shiro;

import com.mtons.Khamonline.modules.entity.Role;
import com.mtons.Khamonline.modules.service.UserRoleService;
import com.mtons.Khamonline.modules.service.UserService;
import com.mtons.Khamonline.base.lang.Consts;
import com.mtons.Khamonline.modules.data.AccountProfile;
import com.mtons.Khamonline.modules.data.UserVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AccountRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;

    public AccountRealm() {
        super(new AllowAllCredentialsMatcher());
        setAuthenticationTokenClass(UsernamePasswordToken.class);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        AccountProfile profile = (AccountProfile) SecurityUtils.getSubject().getPrincipal();
        if (profile != null) {
            UserVO user = userService.get(profile.getId());
            if (user != null) {
                SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
                List<Role> roles = userRoleService.listRoles(user.getId());

                roles.forEach(role -> {
                    info.addRole(role.getName());

                    role.getPermissions().forEach(permission -> info.addStringPermission(permission.getName()));
                });
                return info;
            }
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        AccountProfile profile = getAccount(userService, token);

        if (null == profile) {
            throw new UnknownAccountException(upToken.getUsername());
        }

        if (profile.getStatus() == Consts.STATUS_CLOSED) {
            throw new LockedAccountException(profile.getName());
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(profile, token.getCredentials(), getName());
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("profile", profile);
        return info;
    }

    protected AccountProfile getAccount(UserService userService, AuthenticationToken token) {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        return userService.login(upToken.getUsername(), String.valueOf(upToken.getPassword()));
    }
}
