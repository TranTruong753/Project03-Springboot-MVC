package com.webdemo.demospringboot.controller;

import com.webdemo.demospringboot.model.Thanhvien;
import com.webdemo.demospringboot.model.ThietBi;
import com.webdemo.demospringboot.model.ThongTinSD;
import com.webdemo.demospringboot.repository.DatCho_User_Repository;
import com.webdemo.demospringboot.repository.ThietBiRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Device_User_Controller {

    @Autowired
    private DatCho_User_Repository DatChoRepository;

    @PostMapping("/home/bookdevice")
    @ResponseBody
    public ResponseEntity<String> handleBookDeviceAction(
            @RequestParam("matb") String maTB,
            @RequestBody Map<String, String> payload) {
        try {
            int maThietBi = Integer.parseInt(maTB.trim());
            String selectedDateTime = payload.get("selectedDateTime");
            int currentReservations = DatChoRepository.countCurrentReservationsByMaTB(maThietBi);
            if (currentReservations > 0) {
                return ResponseEntity.ok("Thiết bị đã có người đặt chỗ mượn.");
            }
            int countCurrentBorrowed = DatChoRepository.countCurrentBorrowedReservationsByMaTB(maThietBi);
            if (countCurrentBorrowed > 0) {
                return ResponseEntity.ok("Thiết bị đã có người mượn.");
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime thoiGianDatCho = LocalDateTime.parse(selectedDateTime, formatter);

            ThietBi thietBi = new ThietBi();
            thietBi.setMaTB(maThietBi);
            Thanhvien thanhvien = new Thanhvien();
            thanhvien.setId(1120150184);
            // Lưu thông tin đặt chỗ mới
            ThongTinSD thongTinSD = new ThongTinSD();
            int countThongTinSD = DatChoRepository.countThongTinSD();
            thongTinSD.setMaTT((int) (countThongTinSD + 1));
            thongTinSD.setThietBi(thietBi);
            thongTinSD.setThanhVien(thanhvien);
            thongTinSD.setThoiGianDatCho(thoiGianDatCho);
            DatChoRepository.save(thongTinSD);
            return ResponseEntity.ok("Đã đặt chỗ thiết bị thành công");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi khi xử lý đặt chỗ thiết bị.");
        }
    }
}
