package com.webdemo.demospringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {
    @RequestMapping("member")
    public String index() {return "member";}

    @RequestMapping("member/add_member")
    public String add_member() {return "add_member";}
}
