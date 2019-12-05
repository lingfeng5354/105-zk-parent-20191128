package com.aaa.applicationcontext;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Description :
 * @Author      : cat
 * @exception   :
 * @CreateDate  : 2019/11/21
 * @Version     : 1.0
 */
@Component
public class GetApplicationContext implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        GetApplicationContext.applicationContext=applicationContext;

    }
    public static <T> T getBean(Class<T> tClass){
        T bean = applicationContext.getBean(tClass);
        return bean;
    }
    public static <T> T getName(String name){
        T bean = ((T) applicationContext.getBean(name));
        return bean;
    }
}