package com.webdemo.demospringboot.controller;

import com.webdemo.demospringboot.model.Thanhvien;
import com.webdemo.demospringboot.model.ThietBi;
import com.webdemo.demospringboot.repository.DatCho_User_Repository;
import com.webdemo.demospringboot.service.ThanhVienService;
import com.webdemo.demospringboot.service.ThietBiService;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    HttpSession HttpSession;
    @Autowired
    private ThietBiService thietBiService;
    @Autowired
    private ThanhVienService thanhvienservice;

    @GetMapping({"home", "/"})
    public String index(Model model, HttpSession session) {
        List<ThietBi> cartDevice = (List<ThietBi>) session.getAttribute("cartDevice");

        model.addAttribute("danhSachThietBi", thietBiService.findAllThietBi());
        return "ListDevice";
    }

    @PostMapping("/addToCart")
    public ResponseEntity<String> addToCart(@RequestParam int maTB, HttpSession session) {
        ThietBi thietBi = thietBiService.findByMaTB(maTB);
        if (thietBi == null) {
            return ResponseEntity.badRequest().body("Không tìm thấy thiết bị với mã " + maTB);
        }

        List<ThietBi> cartDevice = (List<ThietBi>) session.getAttribute("cartDevice");
        if (cartDevice == null) {
            cartDevice = new ArrayList<>();
            session.setAttribute("cartDevice", cartDevice);
        }

        // Kiểm tra nếu maTB đã tồn tại trong cartDevice
        boolean isAlreadyInCart = cartDevice.stream().anyMatch(item -> item.getMaTB() == maTB);
        if (isAlreadyInCart) {
            return ResponseEntity.ok("exist");
        }

        cartDevice.add(thietBi);
        session.setAttribute("cartDevice", cartDevice); // Cập nhật giỏ hàng trong session
        System.out.println(session.getAttribute("cartDevice"));

        return ResponseEntity.ok("success");
    }

    @PostMapping("/removeFromCart")
    public ResponseEntity<String> removeFromCart(@RequestParam int maTB, HttpSession session) {
        List<ThietBi> cartDevice = (List<ThietBi>) session.getAttribute("cartDevice");
        if (cartDevice != null) {
            // Tìm và xóa mục trong giỏ hàng với maTB nhận được
            cartDevice.removeIf(thietBi -> thietBi.getMaTB() == maTB);
            session.setAttribute("cartDevice", cartDevice); // Cập nhật lại giỏ hàng trong session
            int cartDeviceLength = cartDevice.size(); // Lấy độ dài danh sách cartDevice

            // Chuyển đổi int thành String
            String cartDeviceLengthString = String.valueOf(cartDeviceLength);

            return ResponseEntity.ok(cartDeviceLengthString);
        }
        return ResponseEntity.badRequest().body("Không tìm thấy giỏ hàng");
    }

    @GetMapping("/cartDevice")
    public String viewCart(Model model, HttpSession session) {
        String maTVString = (String) HttpSession.getAttribute("maTV");
        int maTV = Integer.parseInt(maTVString);
        Thanhvien userLogin = thanhvienservice.search(maTV);
        List<ThietBi> cartDevice = (List<ThietBi>) session.getAttribute("cartDevice");
        if (cartDevice != null) {
            model.addAttribute("cartDevice", cartDevice);
        }
//        System.out.println("thanhvien: "+userLogin);
        model.addAttribute("userLogin", userLogin);
        return "cartDevice";
    }

//    @GetMapping("/home/bookdevice")
//    public String showBookDevicePage(@RequestParam(value = "matb", required = false) String maTB, Model model) {
//        if (maTB == null || maTB.isEmpty()) {
//            // Xử lý khi không có tham số matb trong đường dẫn
//            model.addAttribute("tenTB", "");
//            model.addAttribute("trangthai", "");
//            model.addAttribute("maTB", "");
//        } else {
//            // Đưa mã thiết bị vào model để sử dụng trong bookdevice.html
//            String TrangThai = "Còn Trống";
//            int currentReservations = thanhvienservice.count_trangthai_datmuon_thietbi(Integer.parseInt(maTB));
//            if (currentReservations > 0) {
//                TrangThai = "Đã Được Đặt Mượn";
//            }
//            int countCurrentBorrowed = thanhvienservice.count_trangthai_muon_thietbi(Integer.parseInt(maTB));
//            if (countCurrentBorrowed > 0) {
//                TrangThai = "Đã Được Mượn";
//            }
//            ThietBi thietBi = thietBiService.findByMaTB(Integer.parseInt(maTB));
//            model.addAttribute("tenTB", thietBi.getTenTB());
//            model.addAttribute("trangthai", TrangThai);
//            model.addAttribute("maTB", maTB);
//        }
//        return "BookDevice";
//    }
    @RequestMapping("/list")
    public String user() {
        return "ListDevice";
    }
}
