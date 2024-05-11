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
        
        Long countThietBiDangDuocMuon = thongKeService.countThietBiDangDuocMuonByDate();
        model.addAttribute("countThietBiDangDuocMuon", countThietBiDangDuocMuon);
        
        Long countViPhamDangXuLY = thongKeService.countViPhamDangXuLYByDate();
        model.addAttribute("countViPhamDangXuLY", countViPhamDangXuLY);
        
        // int sumTienBoiThuong = thongKeService.sumTienBoiThuong();
        // model.addAttribute("sumTienBoiThuong", sumTienBoiThuong);
        
        List<Object[]> khoaAndCount = thongKeService.getKhoaAndCountKhoa();
        model.addAttribute("khoaAndCount", khoaAndCount);

        List<Object[]> nganhAndCount = thongKeService.getNganh_and_cout_Nganh();
        model.addAttribute("nganhAndCount", nganhAndCount);
        
        List<Object[]> countSoLanThietBiDuocMuon = thongKeService.countSoLanThietBiDuocMuon();
        model.addAttribute("TBmuonCount", countSoLanThietBiDuocMuon);

        List<Object[]> countVPDaXuLy = thongKeService.getHinhThucXL_and_cout_HinhThucXL();
        model.addAttribute("VPDaXuLyCount", countVPDaXuLy);

        

        // model.addAttribute("Chartdata", Chartdata);

       
        return "admin/index"; // Trả về view
    }

    @PostMapping(value = "/admin", consumes = "application/json")
    @ResponseBody
    public List<Object[]> handleAdminRequest(@RequestBody Map<String, String> requestData) {
        String action = requestData.get("action");
        String formattedDate = requestData.get("time");
        if ("countTVKhoaTheoThoiGian".equals(action)) {
            // Xử lý yêu cầu countTVKhoaTheoThoiGian
            List<Object[]> countTVKhoaTheoThoiGian = thongKeService.getKhoa_and_cout_Khoa_ByDate(formattedDate);
            return countTVKhoaTheoThoiGian;
        } else if ("countSoLanThietBiDuocMuonTheoThoiGian".equals(action)) {
            // Xử lý yêu cầu countSoLanThietBiDuocMuonTheoThoiGian
            List<Object[]> countSoLanThietBiDuocMuonTheoThoiGian = thongKeService.countSoLanThietBiDuocMuonTheoThoiGian(formattedDate);
            return countSoLanThietBiDuocMuonTheoThoiGian;
        }
        else if("countTVNganhTheoThoiGian".equals(action)){
            List<Object[]> countTVNganhTheoThoiGian = thongKeService.getNganh_and_cout_Nganh_ByDate(formattedDate);
            return countTVNganhTheoThoiGian;
        }
        else if("countVPDaXuLyTheoThoiGian".equals(action)){
            List<Object[]> countVPDaXuLyTheoThoiGian = thongKeService.getHinhThucXL_and_cout_HinhThucXL_ByDate(formattedDate);
            return countVPDaXuLyTheoThoiGian;

        }
        else {
            // Xử lý trường hợp không hỗ trợ
            return Collections.emptyList(); // hoặc trả về một giá trị mặc định khác tùy ý
        }
    }
    @PostMapping(value = "/adminyear", consumes = "application/json")
    @ResponseBody
    public int[] handleAdmin(@RequestBody Map<String, String> requestData) {
        String action = requestData.get("action");
        String formattedDate = requestData.get("time");
        if ("countVPDaXuLyTheoNam".equals(action)) {
                   
                    String Time = formattedDate.substring(0, 4);
                    List<Object[]> rowCountByMonth = thongKeService.findRowCountByMonth(Time);
                int[] Chartdata = new int[12];

                for (Object[] row : rowCountByMonth) {
                    int month = ((Number) row[0]).intValue() - 1; // Giá trị tháng trong khoảng 0-11
                    long count = (Long) row[1];
                    Chartdata[month] = (int) count;
                }

                
                for ( int i = 0; i < Chartdata.length; i++) {
                    System.out.println("Tháng " + (i + 1) + ": " + Chartdata[i]);
                }

            return Chartdata;
            
        }
        else {
            // Xử lý trường hợp không hỗ trợ
            return null; // hoặc trả về một giá trị mặc định khác tùy ý
        }
        
    }
    
    
}
