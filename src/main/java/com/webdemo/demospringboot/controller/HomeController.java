package com.webdemo.demospringboot.controller;

import com.webdemo.demospringboot.model.ThietBi;
import com.webdemo.demospringboot.repository.DatCho_User_Repository;
import com.webdemo.demospringboot.service.ThietBiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private ThietBiService thietBiService;
    @Autowired
    private DatCho_User_Repository datcho;

    @RequestMapping("home")
    public String index(Model model) {
        model.addAttribute("danhSachThietBi", thietBiService.findAllThietBi());
        return "index";
    }

    @GetMapping("/home/bookdevice")
    public String showBookDevicePage(@RequestParam(value = "matb", required = false) String maTB, Model model) {
        if (maTB == null || maTB.isEmpty()) {
            // Xử lý khi không có tham số matb trong đường dẫn
            model.addAttribute("tenTB", "");
            model.addAttribute("trangthai", "");
            model.addAttribute("maTB", "");
        } else {
            // Đưa mã thiết bị vào model để sử dụng trong bookdevice.html
            String TrangThai = "Còn Trống";
            int currentReservations = datcho.count_trangthai_datmuon_thietbi(Integer.parseInt(maTB));
            if (currentReservations > 0) {
                TrangThai = "Đã Được Đặt Mượn";
            }
            int countCurrentBorrowed = datcho.count_trangthai_muon_thietbi(Integer.parseInt(maTB));
            if (countCurrentBorrowed > 0) {
                TrangThai = "Đã Được Mượn";
            }
            ThietBi thietBi = thietBiService.findByMaTB(Integer.parseInt(maTB));
            model.addAttribute("tenTB", thietBi.getTenTB());
            model.addAttribute("trangthai", TrangThai);
            model.addAttribute("maTB", maTB);
        }
        return "BookDevice";
    }

    @RequestMapping("/user")
    public String user() {
        return "user";
    }

}
