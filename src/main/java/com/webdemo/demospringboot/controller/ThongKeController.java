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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class ThongKeController {
    private String getyear="2023";

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

        List<Object[]> rowCountByMonth = thongKeService.findRowCountByMonth(getyear);
        int[] Chartdata = new int[12];

        for (Object[] row : rowCountByMonth) {
            int month = ((Number) row[0]).intValue() - 1; // Giá trị tháng trong khoảng 0-11
            long count = (Long) row[1];
            Chartdata[month] = (int) count;
        }

        
        for ( int i = 0; i < Chartdata.length; i++) {
            System.out.println("Tháng " + (i + 1) + ": " + Chartdata[i]);
        }

        model.addAttribute("Chartdata", Chartdata);

       
        return "admin/index"; // Trả về view
    }

 @PostMapping(value = "/admin", consumes = "application/json")
    @ResponseBody
    public List<Object[]> handleTime(@RequestBody Map<String, String> requestData) {
        String formattedDate = requestData.get("time");
        List<Object[]> countSoLanThietBiDuocMuonTheoThoiGian = thongKeService.countSoLanThietBiDuocMuonTheoThoiGian(formattedDate);
        return countSoLanThietBiDuocMuonTheoThoiGian;
    }

    @PostMapping("/sendyear")
    
    public String handleYearSelection(@RequestParam String year) {
        System.out.println("Selected year: " + year);

        getyear = year;
        return "redirect:/admin";
        
    }
}
