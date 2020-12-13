package com.goule666.potato.proxy;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Locale;

/**
 * @author wenlongnie
 * @date 2020-12-13 17:52
 * @description 代理配置类
 **/
public class ConfigProxy implements InvocationHandler {


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method != null) {
            String className = method.getDeclaringClass().getName();
            String methodName = method.getName();
            String version = null;
            TestAnnotation testAnnotation = method.getDeclaringClass().getAnnotation(TestAnnotation.class);
            if (testAnnotation != null) {
                version = testAnnotation.serviceName();
            } else {
                version = className;
            }


            StringBuilder argsStr = new StringBuilder();
            if (args != null) {
                for (Object arg : args) {
                    argsStr.append(arg);
                }
            }
            TransportDTO transportDTO = new TransportDTO();
            transportDTO.setClassName(className);
            transportDTO.setMethodName(methodName);
            transportDTO.setArgs(argsStr.toString());
            transportDTO.setVersion(version);

            System.out.println("远程调用参数:" + JSON.toJSONString(transportDTO));

        }
        return null;
    }
}
