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
 * @author Alatai
 * @version 1.0
 * @date 2021/07/16 20:46
 */
@Configuration
public class ShiroConfiguration {

    /**
     * 配置LifecycleBeanPostProcessor，保证实现了Shiro内部lifecycle函数的bean执行
     */
    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 配置shiroFilter工厂类，id要和web.xml中配置的过滤器一致
     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(SessionsSecurityManager sessionsSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(sessionsSecurityManager);
        return shiroFilterFactoryBean;
    }

    /**
     * 配置SecurityManager
     */
    @Bean
    public SessionsSecurityManager sessionsSecurityManager() {
        DefaultWebSecurityManager sessionsSecurityManager = new DefaultWebSecurityManager();
        sessionsSecurityManager.setRealm(getUserRealm());
        return sessionsSecurityManager;
    }

    /**
     * 配置Realm
     */
    @Bean
    public UserRealm getUserRealm() {
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return userRealm;
    }

    /**
     * 配置HashedCredentialsMatcher
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 加密算法
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        // 加密次数
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

    /**
     * 启用shiro注解
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SessionsSecurityManager sessionsSecurityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(sessionsSecurityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
