package com.trainning.spring.day1_bean.scope_bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class HelloBeanScopeController {

    @Autowired
    @Qualifier("bean_singleton")
    private HelloBeanService singletonService;

    @Autowired
    @Qualifier("bean_prototype")
    private HelloBeanService prototypeService;

    @GetMapping("/test")
    public void test() {
        System.out.println("1. Singleton: ");
        singletonService.confirmInstandBean();
        System.out.println("2. Prototype: ");
        prototypeService.confirmInstandBean();
    }
}
