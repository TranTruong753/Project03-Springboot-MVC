package com.webdemo.demospringboot.controller;

import com.webdemo.demospringboot.service.ThietBiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThietBiController {

    @Autowired
    private ThietBiService thietBiService;

    @GetMapping("/thietbi")
    public String index(Model model) {
        model.addAttribute("danhSachThietBi", thietBiService.layDanhSachThietBi());
        return "ThietBi/index";
    }
}
