package com.webdemo.demospringboot.controller;

import com.webdemo.demospringboot.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.webdemo.demospringboot.model.Thanhvien;
import org.springframework.ui.ModelMap;


@Controller
public class LoginController {
    @RequestMapping("login")
    public String index() {
        return "login";
    }

    @Autowired
    private LoginService loginService;
    @PostMapping("/login")
    public String login(ModelMap model, @RequestParam String maThanhVien, @RequestParam String matKhau) {
        try {
            int maThanhVienInt = Integer.parseInt(maThanhVien);
            if (maThanhVienInt < Integer.MIN_VALUE || maThanhVienInt > Integer.MAX_VALUE) {
                model.addAttribute("message", "Đăng nhập thất bại do Mã Thành Viên hoặc Mật Khẩu sai");
                return "login";
            }
    
            Thanhvien user = (Thanhvien) loginService.loginThanhVien(maThanhVienInt, matKhau);
            if (user != null) {
                model.addAttribute("username", maThanhVien);
                model.remove("message");
                return "redirect:/home";
            } else {
                model.addAttribute("message", "Đăng nhập thất bại do Mã Thành Viên hoặc Mật Khẩu sai");
                return "login";
            }
        } catch (NumberFormatException e) {
            model.addAttribute("message", "Đăng nhập thất bại do Mã Thành Viên hoặc Mật Khẩu sai");
            return "login";
        }
    }
}
