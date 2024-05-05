package com.webdemo.demospringboot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.webdemo.demospringboot.model.Thanhvien;
import com.webdemo.demospringboot.service.RegisterService;

@Controller
public class RegisterController {
    private RegisterService registerService;
    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @RequestMapping("register")
    public String index() {
        return "register";
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@ModelAttribute("user") Thanhvien user) {
        if (registerService.existsById(user.getId())) {
            // ID already exists, handle the error or return an appropriate response
            return ResponseEntity.status(HttpStatus.IM_USED).build();
        }
        registerService.save(user);
        return ResponseEntity.ok().build();
    }
}
