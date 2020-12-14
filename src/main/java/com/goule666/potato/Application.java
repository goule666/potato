package com.goule666.potato;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author wenlongnie
 * @date 2020-12-14 19:33
 * @description
 **/
@SpringBootApplication
public class Application {
    @Autowired
    private ApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
