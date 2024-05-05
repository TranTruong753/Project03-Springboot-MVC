/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webdemo.demospringboot.controller;

import com.webdemo.demospringboot.service.XulyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Administrator
 */
@Controller
public class XulyController {
    @Autowired
    private XulyService xulyService;

    @GetMapping("/xuly")
    public String index(Model model) {
        model.addAttribute("danhSachViPham", xulyService.layDanhSachViPham());
        return "violate";
    }
}
