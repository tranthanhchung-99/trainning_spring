package com.trainning.spring.day1_bean.scope_bean;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;

public interface HelloBeanService {
    void confirmInstandBean();
}

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Qualifier("bean_singleton")
class HelloBeanSingletonImpl implements HelloBeanService {
    private final String id = UUID.randomUUID().toString();

    @Override
    public void confirmInstandBean() {
        System.out.println("Bean Singleton init !" + id);
    }
}

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Qualifier("bean_prototype")
class HelloBeanPrototypeImpl implements HelloBeanService {
    private final String id = UUID.randomUUID().toString();

    @Override
    public void confirmInstandBean() {

        System.out.println("Bean Prototype init !" + id);
    }
}
