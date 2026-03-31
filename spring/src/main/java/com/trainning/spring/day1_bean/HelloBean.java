package com.trainning.spring.day1_bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class HelloBean implements InitializingBean, DisposableBean {

    /**
     * Triển khai method của interface DisposableBean
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        System.out.println("Bean for HelloBean implement DisposableBean . Destroy !");
    }

    /**
     * Triển khai method của interface InitializingBean
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Bean for HelloBean implement InitializingBean . Instantiated and I am init() method !");
    }
}
