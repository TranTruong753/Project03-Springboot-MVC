/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webdemo.demospringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.webdemo.demospringboot.model.Thanhvien;
import com.webdemo.demospringboot.model.Xuly;
import com.webdemo.demospringboot.service.ThanhVienService;
import com.webdemo.demospringboot.service.XulyService;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("admin/transgress")
public class AdminTransgressController {

    @Autowired
    private XulyService xulyService;
    
    @Autowired
    private ThanhVienService thanhVienService;

    
    @RequestMapping
    public String index(Model model) {
        List<Xuly> XulyList = xulyService.layDanhSachXuLi();
        model.addAttribute("danhSachXuLi", XulyList);
        return "admin/transgress";
    }
    
    @RequestMapping("add_transgress")
    public String add_transgress(Model model) {
        List<Thanhvien> ThanhvienList = thanhVienService.GetAll();
        model.addAttribute("danhSachThanhVien", ThanhvienList);
        List<Xuly> XulyList = xulyService.layDanhSachXuLi();
        int maxMaXL = XulyList.stream()
            .mapToInt(Xuly::getMaXL)
            .max()
            .orElse(0);
        model.addAttribute("maxId", maxMaXL+1);
        return "admin/add_transgress";
    }
    
    @RequestMapping("edit_transgress")
    public String edit_transgress(@RequestParam("id") int id, Model model) {
        List<Thanhvien> ThanhvienList = thanhVienService.GetAll();
        model.addAttribute("danhSachThanhVien", ThanhvienList);
        List<Xuly> XulyList = xulyService.layDanhSachXuLi();
        Xuly xuly = new Xuly();
        xuly = XulyList.stream()
            .filter(x -> x.getMaXL() == id)
            .findFirst()
            .orElse(null);
        model.addAttribute("xuly", xuly);
        System.out.println(xuly.getMaXL());
        return "admin/edit_transgress";
    }

    @PostMapping("/add_transgress")
    public String add(
        @RequestParam("maXL") String maXL, 
        @RequestParam("maTV") String maTV, 
        @RequestParam("hinhThucXuLy") String hinhThucXuLy, 
        @RequestParam("soTien") String soTien, 
        @RequestParam("ngayXL") String ngayXL, 
        @RequestParam("trangThaiXL") String trangThaiXL) {
        System.out.println(maXL + " " + maTV + " " + hinhThucXuLy + " " + soTien + " " + ngayXL + " " + trangThaiXL);
        Xuly newXuly = new Xuly();
        newXuly.setMaXL(Integer.parseInt(maXL));
        newXuly.setMaTV(Integer.parseInt(maTV));
        newXuly.setHinhThucXL(hinhThucXuLy);
        if (soTien.isEmpty()) {
            newXuly.setSoTien(null);
        } else {
            newXuly.setSoTien(Integer.parseInt(soTien));
        }
        newXuly.setNgayXL(ngayXL);
        newXuly.setTrangThaiXL(Integer.parseInt(trangThaiXL));   
        // Logging
        
        xulyService.save(newXuly);
        return "redirect:/admin/transgress";
    }
    
    @PostMapping("/edit_transgress")
    public String edit(
        @RequestParam("maXL") String maXL, 
        @RequestParam("maTV") String maTV, 
        @RequestParam("hinhThucXuLy") String hinhThucXuLy, 
        @RequestParam("soTien") String soTien, 
        @RequestParam("ngayXL") String ngayXL, 
        @RequestParam("trangThaiXL") String trangThaiXL) {
        System.out.println(maXL + " " + maTV + " " + hinhThucXuLy + " " + soTien + " " + ngayXL + " " + trangThaiXL);
        Xuly newXuly = new Xuly();
        newXuly.setMaXL(Integer.parseInt(maXL));
        newXuly.setMaTV(Integer.parseInt(maTV));
        newXuly.setHinhThucXL(hinhThucXuLy);
        if (soTien.isEmpty()) {
            newXuly.setSoTien(null);
        } else {
            newXuly.setSoTien(Integer.parseInt(soTien));
        }
        newXuly.setNgayXL(ngayXL);
        newXuly.setTrangThaiXL(Integer.parseInt(trangThaiXL));   
        // Logging
        
        xulyService.save(newXuly);
        return "redirect:/admin/transgress";
    }

    @RequestMapping("/delete_transgress")
    public String delete_transgress(@RequestParam("id") int id) {
        xulyService.delete(id);
        return "redirect:/admin/transgress";
    }
}
