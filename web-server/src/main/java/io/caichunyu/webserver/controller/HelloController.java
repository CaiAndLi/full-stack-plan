package io.caichunyu.webserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.caichunyu.webserver.service.HelloService;

/**
 * @author caichunyu <caichunyu@kuaishou.com>
 * Created on 2020-05-31
 */
@Controller
@RequestMapping("/")
public class HelloController {
    @Autowired
    private HelloService helloService;

    @RequestMapping("/hello")
    @ResponseBody
    public String helloWorld() {
        return helloService.sayHello();
    }
}
