/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webdemo.demospringboot.controller;

import com.webdemo.demospringboot.model.Thanhvien;
import com.webdemo.demospringboot.service.ThanhVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Admin
 */
@Controller
public class ChangePasswordController {
    @Autowired
    ThanhVienService tvservice;   
    
//    @RequestMapping("changePassword")
//    public String forgetpassword() {
//        return "ChangePassword";
//    }

    @RequestMapping("changePassword")
    public String show(@RequestParam(name="id") int id ,Model model ) {
        int maSv = tvservice.search(id).getId();
        String password = tvservice.search(id).getPassword();
        model.addAttribute("user", maSv);
        model.addAttribute("password", password);
        return "ChangePassword";
    }
    
    @RequestMapping("GetPassword")
    public String getnewpass(@RequestParam(name="id") String id ,Model model,@RequestParam(name="newpass") String newpass ) {
        
        Thanhvien tv= tvservice.search(Integer.parseInt(id));
        tv.setPassword(newpass);
        tvservice.save(tv);
        
        model.addAttribute("user", id);
        model.addAttribute("password", newpass);
        model.addAttribute("message", "Đổi mật khẩu thành công!");
        return "ChangePassword";
    }
}
