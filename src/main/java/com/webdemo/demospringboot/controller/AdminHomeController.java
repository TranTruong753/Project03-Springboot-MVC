package com.webdemo.demospringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminHomeController {
    @RequestMapping("/admin")
    public String admin() {
        return "admin/index";
    }
}
