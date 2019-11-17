package com.example.springbootdemo;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试web接口
 *
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(String name){
        System.out.println(name);
        return "hello"+name;
    }

}
