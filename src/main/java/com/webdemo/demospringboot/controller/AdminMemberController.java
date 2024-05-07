package com.webdemo.demospringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/member")
public class AdminMemberController {
    @GetMapping
    public String index() {
        return "admin/member";
    }

    @GetMapping("check_in")
    public String check_in() {
        return "admin/check_in";
    }

    @GetMapping("add_member")
    public String add_member() {
        return "admin/add_member";
    }
    
    @GetMapping("edit_member")
    public String edit_member() {
        return "admin/edit_member";
    }

    @GetMapping("borrow_device")
    public String borrow_device() {
        return "admin/borrow_device";
    }

}
