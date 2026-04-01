package com.trainning.spring.day1_bean.init_bean;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class HelloBeanAnnotation {
    @PostConstruct
    public void initBean(){
        System.out.println("HelloBeanAnnotation init by annotation ");
    }

    @PreDestroy
    public void destroyBean(){
        System.out.println("HelloBeanAnnotation destroy by annotation ");
    }
}
