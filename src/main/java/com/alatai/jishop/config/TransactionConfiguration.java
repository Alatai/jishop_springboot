package com.alatai.jishop.config;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * TransactionConfiguration
 *
 * @author M20W0324 saihou
 * @version 1.0
 * @date 2021/07/20 10:56
 */
@Aspect
@Configuration
public class TransactionConfiguration {

    private static final String AOP_POINTCUT_EXPRESSION =
            "execution(* com.alatai.jishop.service.impl.*ServiceImpl.*(..))";

    // transactionManager
    @Autowired
    private TransactionManager transactionManager;

    /**
     * txAdvice
     */
    @Bean
    public TransactionInterceptor txAdvice() {
        DefaultTransactionAttribute REQUIRED = new DefaultTransactionAttribute();
        REQUIRED.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        REQUIRED.setReadOnly(false);

        DefaultTransactionAttribute SUPPORTS = new DefaultTransactionAttribute();
        SUPPORTS.setPropagationBehavior(TransactionDefinition.PROPAGATION_SUPPORTS);
        SUPPORTS.setReadOnly(true);

        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        source.addTransactionalMethod("*", REQUIRED);
        source.addTransactionalMethod("find*", SUPPORTS);

        return new TransactionInterceptor(transactionManager, source);
    }

    /**
     * AOP
     */
    @Bean
    public Advisor txAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }
}
