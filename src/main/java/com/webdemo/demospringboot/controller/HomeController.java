package com.webdemo.demospringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
 
    @RequestMapping("/home")
    public String index() {
        return "index";
    }
    
     @RequestMapping("/user")
    public String user() {
        return "user";
    }
    
   
}
