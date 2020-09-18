package com.study.walkingweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class WelcomeController {

    @GetMapping("/welcome")
    public void welcome(){
        System.out.println("=====访问成功");
    }

}
