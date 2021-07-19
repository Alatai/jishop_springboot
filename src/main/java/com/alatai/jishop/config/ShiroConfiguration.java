package com.alatai.jishop.config;

import com.alatai.jishop.realm.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Shiro設定
 *
 * @author M20W0324 saihou
 * @version 1.0
 * @date 2021/07/16 20:46
 */
@Configuration
public class ShiroConfiguration {

    /**
     * LifecycleBeanPostProcessor設定
     */
    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * ShiroFilterFactory設定
     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(SessionsSecurityManager sessionsSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(sessionsSecurityManager);
        return shiroFilterFactoryBean;
    }

    /**
     * SecurityManager設定
     */
    @Bean
    public SessionsSecurityManager sessionsSecurityManager() {
        DefaultWebSecurityManager sessionsSecurityManager = new DefaultWebSecurityManager();
        sessionsSecurityManager.setRealm(getUserRealm());
        return sessionsSecurityManager;
    }

    /**
     * Realm設定
     */
    @Bean
    public UserRealm getUserRealm() {
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return userRealm;
    }

    /**
     * HashedCredentialsMatcher設定
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 暗号化アルゴリズム
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        // 暗号化回数
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

    /**
     * Shiroアノデーション設定
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SessionsSecurityManager sessionsSecurityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(sessionsSecurityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
