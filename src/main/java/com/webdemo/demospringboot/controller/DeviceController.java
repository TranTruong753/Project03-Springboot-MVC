package com.webdemo.demospringboot.controller;

import com.webdemo.demospringboot.model.ThietBi;
import com.webdemo.demospringboot.service.ThietBiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    private ThietBiRepository repo;



    @GetMapping({"", "/"})
    public String index(Model model)
    {
        List<ThietBi> danhSachThietBi = repo.findAll();
        model.addAttribute("danhSachThietBi", danhSachThietBi);
        return "device/index";
    }



    @RequestMapping("device/add_device")
    public String add_device() {
        return "add_device";
    }
    
    @RequestMapping("list_device")
    public String list()
    {
        return "user";
    }
}
