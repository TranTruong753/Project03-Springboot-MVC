/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webdemo.demospringboot.controller;

import com.webdemo.demospringboot.model.Email;
import com.webdemo.demospringboot.service.ThanhVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.webdemo.demospringboot.model.Thanhvien;
import java.util.List;
/**
 *
 * @author Admin
 */
@Controller
public class ForgetPasswordController {
    @Autowired
    ThanhVienService tvservice;    
    @RequestMapping("forgetpassword")
    public String forgetpassword() {
        return "ForgetPassword";
    }
    
    @RequestMapping("forgetpass")
    public String getCode(@RequestParam(name="id") String id ,Model model,@RequestParam(name="email") String email ) {
        List<Thanhvien> listtv=tvservice.GetAll();
        Thanhvien TV=new Thanhvien();
        boolean flag=false;
        for (Thanhvien tv : listtv) {
            String ID=""+tv.getId();
            if(ID.equals(id)){
                TV=tv;
                 flag=true;
                 break;
            }
        }
        
        if(!flag){
            model.addAttribute("message", "Mã Thành Viên Không Tồn tại");
        }
        else if(!TV.getEmail().equals(email)){
            model.addAttribute("message", "Email không hợp lệ");
        }
        else {
            Email e = new Email();
            model.addAttribute("code", e.SentEmai(email));
            model.addAttribute("message", "Sent success");
        }
        model.addAttribute("mssv", id);
        model.addAttribute("Email", email);
        return "ForgetPassword";
    }
    
    @RequestMapping("getpass")
    public String getpass(@RequestParam(name="id") String id ,Model model ) {
        
        model.addAttribute("id", id);
        
        return "GetPass";
    }
    
    @RequestMapping("getnewpass")
    public String getnewpass(@RequestParam(name="id") String id ,Model model,@RequestParam(name="newpass") String newpass ) {
        
        Thanhvien tv= tvservice.search(Integer.parseInt(id));
        tv.setPassword(newpass);
        tvservice.save(tv);
        return "login";
    }
}
