package com.trainning.spring.day1_bean.init_bean;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloBeanTest {
    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext= new ClassPathXmlApplicationContext("config_xml/bean.xml");
        configurableApplicationContext.close();
    }
}
