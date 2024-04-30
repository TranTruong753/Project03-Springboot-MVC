package com.webdemo.demospringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DeviceController {
    @RequestMapping("device")
    public String index()
    {
        return "device";
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
