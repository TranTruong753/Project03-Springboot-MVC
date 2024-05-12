/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webdemo.demospringboot.controller;

import com.webdemo.demospringboot.model.Thanhvien;
import com.webdemo.demospringboot.service.ThanhVienService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Admin
 */
@Controller
public class ChangePasswordController {
    @Autowired
    ThanhVienService tvservice;   
    @Autowired
    private HttpSession httpSession;
//    @RequestMapping("changePassword")
//    public String forgetpassword() {
//        return "ChangePassword";
//    }

    @GetMapping({"", "/changePassword"})
    public String show(Model model) {
        String maTVString = (String) httpSession.getAttribute("maTV");
        int maTV = Integer.parseInt(maTVString);
       
        String password = tvservice.search(maTV).getPassword();
        model.addAttribute("user", maTV);
        model.addAttribute("password", password);
        return "ChangePassword";
    }

//    @RequestMapping("GetPassword")
//    public String getnewpass(@RequestParam(name="id") String id ,Model model,@RequestParam(name="newpass") String newpass ) {
//        
//        Thanhvien tv= tvservice.search(Integer.parseInt(id));
//        tv.setPassword(newpass);
//        tvservice.save(tv);
//        
//        model.addAttribute("user", id);
//        model.addAttribute("password", newpass);
//        model.addAttribute("message", "Đổi mật khẩu thành công!");
//        return "ChangePassword";
//    }
    
    @RequestMapping(value = "/change", method = RequestMethod.POST)
    public @ResponseBody String change(HttpServletRequest request) {
        String id = request.getParameter("id");
        String newpass = request.getParameter("code");
        int maTV = Integer.parseInt(id);
        Thanhvien tv= tvservice.search(Integer.parseInt(id));
        tv.setPassword(newpass);
        tvservice.save(tv);
        String mess = "Thay đổi mật khẩu thành công!";
        
        return mess;
            
    }
}
