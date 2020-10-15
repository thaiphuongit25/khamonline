package com.mtons.Khamonline.config;

import com.mtons.Khamonline.shiro.AccountRealm;
import com.mtons.Khamonline.shiro.AccountSubjectFactory;
import com.mtons.Khamonline.shiro.AuthenticatedFilter;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SubjectFactory;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author langhsu
 * @since 3.0
 */
@Configuration
@ConditionalOnProperty(name = "shiro.web.enabled", matchIfMissing = true)
public class ShiroConfiguration {
    @Bean
    public SubjectFactory subjectFactory() {
        return new AccountSubjectFactory();
    }

    @Bean
    public Realm accountRealm() {
        return new AccountRealm();
    }

    @Bean
    public CacheManager shiroCacheManager(net.sf.ehcache.CacheManager cacheManager) {
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManager(cacheManager);
        return ehCacheManager;
    }

    /**
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        shiroFilter.setLoginUrl("/login");
        shiroFilter.setSuccessUrl("/");
        shiroFilter.setUnauthorizedUrl("/error/reject.html");

        HashMap<String, Filter> filters = new HashMap<>();
        filters.put("authc", new AuthenticatedFilter());
        shiroFilter.setFilters(filters);

        /**
         *
         * anon  No authentication required
         * authc Need certification
         * user  Anyone who is verified or logged in by RememberMe can
         *
         *
         */
        Map<String, String> hashMap = new LinkedHashMap<>();
        hashMap.put("/", "anon");
        hashMap.put("/review_payment", "anon");
        hashMap.put("/home", "anon");
        hashMap.put("/index", "anon");
        hashMap.put("/dist/**", "anon");
        hashMap.put("/theme/**", "anon");
        hashMap.put("/storage/**", "anon");
        hashMap.put("/login", "anon");
        hashMap.put("/register", "anon");
        hashMap.put("/forgot", "anon");
        hashMap.put("/logout", "authc");
        hashMap.put("/user/**", "authc");
        hashMap.put("/settings/**", "authc");
        hashMap.put("/*", "authc,perms[post:list]");
        hashMap.put("/post/*", "authc");
        hashMap.put("/post/editing", "authc");
        hashMap.put("/post/submit", "authc");
        hashMap.put("/post/delete/*", "authc");
        hashMap.put("/post/upload", "authc");

        hashMap.put("/admin/channel/list", "authc,perms[channel:list]");
        hashMap.put("/admin/channel/update", "authc,perms[channel:update]");
        hashMap.put("/admin/channel/delete", "authc,perms[channel:delete]");

        hashMap.put("/admin/post/list", "authc,perms[post:list]");
        hashMap.put("/admin/post/update", "authc,perms[post:update]");
        hashMap.put("/admin/post/delete", "authc,perms[post:delete]");

        hashMap.put("/admin/comment/list", "authc,perms[comment:list]");
        hashMap.put("/admin/comment/delete", "authc,perms[comment:delete]");

        hashMap.put("/admin/user/list", "authc,perms[user:list]");
        hashMap.put("/admin/user/update_role", "authc,perms[user:role]");
        hashMap.put("/admin/user/pwd", "authc,perms[user:pwd]");
        hashMap.put("/admin/user/open", "authc,perms[user:open]");
        hashMap.put("/admin/user/close", "authc,perms[user:close]");

        hashMap.put("/admin/options/index", "authc,perms[options:index]");
        hashMap.put("/admin/options/update", "authc,perms[options:update]");

        hashMap.put("/admin/role/list", "authc,perms[role:list]");
        hashMap.put("/admin/role/update", "authc,perms[role:update]");
        hashMap.put("/admin/role/delete", "authc,perms[role:delete]");

        hashMap.put("/admin/theme/*", "authc,perms[theme:index]");

        hashMap.put("/admin", "authc,perms[admin]");
        hashMap.put("/admin/*", "authc,perms[admin]");

        shiroFilter.setFilterChainDefinitionMap(hashMap);
        return shiroFilter;
    }

}
