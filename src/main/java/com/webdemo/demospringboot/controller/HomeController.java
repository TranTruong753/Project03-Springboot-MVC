package com.webdemo.demospringboot.controller;

import com.webdemo.demospringboot.service.ThietBiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    private ThietBiService thietBiService;

    @RequestMapping("home")
    public String index(Model model) {
        model.addAttribute("danhSachThietBi", thietBiService.layDanhSachThietBi());
        return "index";
    }

    @RequestMapping("/user")
    public String user() {
        return "user";
    }
    
   
}
