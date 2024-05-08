package com.webdemo.demospringboot.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.ui.Model;

import com.webdemo.demospringboot.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ChartController {
    
//    @Autowired
//    private ChartService chartService;
//
//
//     @PostMapping("/admin")
//     public String handleYearSelection(@RequestParam String year, Model model) {
//         // Process the selected year
//         // Call the appropriate methods in the chartService
//         // Return the response or update the model as needed
//
//    //     System.out.println("Selected year: " + year);
//
//         List<Object[]> rowCountByMonth = chartService.findRowCountByMonth(year);
//         for (Object[] row : rowCountByMonth) {
//             System.out.println("Month: " + row[0] + ", Count: " + row[1]);
//         }
//
//         model.addAttribute("rowCountByMonth", rowCountByMonth);
//         int[] data = new int[12];
//
//         // Lặp qua kết quả trả về từ cơ sở dữ liệu và đặt giá trị vào mảng data
//         for (Object[] row : rowCountByMonth) {
//             int month = ((Number) row[0]).intValue() - 1; // Giá trị tháng trong khoảng 0-11
//             long count = (Long) row[1];
//             data[month] = (int) count;
//         }
//         // Đặt giá trị mảng data vào model để truyền cho view
//         model.addAttribute("data", data);
//
//
//         return "admin/index";
//     }

   
   
    
   
        
        
        
    
    

    
}
