/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webdemo.demospringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/transgress")
public class AdminTransgressController {
    @GetMapping
    public String index() {
        return "admin/transgress";
    }
    
    @GetMapping("add_transgress")
    public String add_transgress() {
        return "admin/add_transgress";
    }
    
    @GetMapping("edit_transgress")
    public String edit_transgress() {
        return "admin/edit_transgress";
    }
}
