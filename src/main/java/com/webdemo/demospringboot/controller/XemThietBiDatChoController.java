/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webdemo.demospringboot.controller;

import com.webdemo.demospringboot.model.ThietBi;
import com.webdemo.demospringboot.model.ThongTinSD;


import com.webdemo.demospringboot.repository.XemThietBiDatChoRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.webdemo.demospringboot.service.XemThietBiDatChoService;
/**
 *
 * @author ACER
 */
@Controller

public class XemThietBiDatChoController {

    @Autowired
    private XemThietBiDatChoService xemThietBiDatChoService;
    //vd: http://localhost:8080/checkDeviceBooked?maTV=1123330257
    @GetMapping("/checkDeviceBooked")
    public String xemAllThietBiDaDatCho(Model model, @RequestParam("maTV") int maTV) {
        List<ThongTinSD> thongTinSDList = xemThietBiDatChoService.layDanhSachThietBiDatCho(maTV);
        model.addAttribute("thongTinSDList", thongTinSDList);
        return "xemThietBiDatCho";
    }
}
