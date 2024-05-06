/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webdemo.demospringboot.controller;

import com.webdemo.demospringboot.model.ThongTinSD;
import com.webdemo.demospringboot.service.XemThietBiDangMuonService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author DELL
 */
@Controller
public class XemThietBiDangMuonController {

    @Autowired
    private XemThietBiDangMuonService service;

    @Autowired
    HttpSession HttpSession;

    @GetMapping({"", "/ThietBiDangMuon"})
    public String show(Model model) {
        String maTVString = (String) HttpSession.getAttribute("maTV");
        int maTV = Integer.parseInt(maTVString);
        System.out.println(maTV);
        List<ThongTinSD> ListDanhSachDangMuon = service.LayDanhSachDangMuon(maTV);
        for (ThongTinSD ttsd : ListDanhSachDangMuon) {
            System.out.println(ttsd.getMaTT());
            System.out.println(ttsd.getThietBi().getTenTB());

        }
        model.addAttribute("ListDanhSachDangMuon", ListDanhSachDangMuon);
        return "ThietBiDangMuon";
    }

}
