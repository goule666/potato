package com.goule666.potato.proxy;

import com.goule666.potato.Application;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Proxy;

/**
 * @author wenlongnie
 * @date 2020-12-13 17:54
 * @description
 **/
public class Test {
    public static void main(String[] args) {

//        ConfigProxy configProxy = new ConfigProxy();
//        LoginService loginService = (LoginService) Proxy.newProxyInstance(ConfigProxy.class.getClassLoader(), new Class[]{LoginService.class}, configProxy);
//        loginService.login("17682312262");
//
//        VerifyCodeService verifyCodeService = (VerifyCodeService) Proxy.newProxyInstance(ConfigProxy.class.getClassLoader(), new Class[]{VerifyCodeService.class}, configProxy);
//        verifyCodeService.sendVerifyCode("17682312262");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        System.out.println(context.getBean("login"));

    }
}
