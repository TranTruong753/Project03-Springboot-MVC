/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webdemo.demospringboot.controller;

import com.webdemo.demospringboot.model.Xuly;
import com.webdemo.demospringboot.service.XulyService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
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
    
    @Autowired
    private HttpSession httpSession;
    
    @GetMapping("/xuly")
    public String index(Model model) {
        String maTVString = (String) httpSession.getAttribute("maTV");
        int maTV = Integer.parseInt(maTVString);
        List<Xuly> XulyList = xulyService.layDanhSachViPhamTheoID(maTV);
        model.addAttribute("matv",maTV);
        model.addAttribute("danhSachViPham", XulyList);
        return "violate";
    }
}
