/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webdemo.demospringboot.controller;


import com.webdemo.demospringboot.service.ThongKeService;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ThongKeController {
    @Autowired
    private final ThongKeService thongKeService;
    public ThongKeController(ThongKeService thongKeService) {
        this.thongKeService = thongKeService;
    }
    @GetMapping("/admin")
    public String handleRequest(Model model) {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedTime = currentTime.format(formatter);
        Long countTV = thongKeService.countTvByDate(formattedTime);
        model.addAttribute("countTV", countTV);
        
        Long countThietBiDangDuocMuon = thongKeService.countThietBiDangDuocMuonByDate(formattedTime);
        model.addAttribute("countThietBiDangDuocMuon", countThietBiDangDuocMuon);
        
        Long countViPhamDangXuLY = thongKeService.countViPhamDangXuLYByDate();
        model.addAttribute("countViPhamDangXuLY", countViPhamDangXuLY);
        
        List<Object[]> khoaAndCount = thongKeService.getKhoaAndCountKhoa();
        model.addAttribute("khoaAndCount", khoaAndCount);

        List<Object[]> countSoLanThietBiDuocMuon = thongKeService.countSoLanThietBiDuocMuon();
        model.addAttribute("TBmuonCount", countSoLanThietBiDuocMuon);
       
        return "admin/index"; // Trả về view
    }

 @PostMapping(value = "/admin", consumes = "application/json")
    @ResponseBody
    public List<Object[]> handleTime(@RequestBody Map<String, String> requestData) {
        String formattedDate = requestData.get("time");
        List<Object[]> countSoLanThietBiDuocMuonTheoThoiGian = thongKeService.countSoLanThietBiDuocMuonTheoThoiGian(formattedDate);
        return countSoLanThietBiDuocMuonTheoThoiGian;
    }
}
