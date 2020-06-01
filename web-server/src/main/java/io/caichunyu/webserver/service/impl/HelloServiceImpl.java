package io.caichunyu.webserver.service.impl;

import org.springframework.stereotype.Component;

import io.caichunyu.webserver.service.HelloService;

/**
 * @author caichunyu <caichunyu@kuaishou.com>
 * Created on 2020-05-31
 */
@Component
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello() {
        return "Hello World!";
    }
}
