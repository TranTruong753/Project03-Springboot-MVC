/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webdemo.demospringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/device")
public class AdminDeviceController {
    @GetMapping
    public String index() {
        return "admin/device";
    }
    
    @GetMapping("add_device")
    public String add_member() {
        return "admin/add_device";
    }
    
    @GetMapping("edit_device")
    public String edit_member() {
        return "admin/edit_device";
    }
}
