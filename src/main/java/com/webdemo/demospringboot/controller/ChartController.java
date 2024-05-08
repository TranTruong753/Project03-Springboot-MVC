package com.webdemo.demospringboot.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;

import com.webdemo.demospringboot.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ChartController {
    
    @Autowired
    private ChartService chartService;


//    @GetMapping({"", "/admin"})
//    public String chart(Model model) {
//        List<String> allYears = chartService.findAllYearsXuLy();
//        model.addAttribute("allYears", allYears);
//        return "admin/index";
//    }

    
   
        
        
        
    
    

    
}
