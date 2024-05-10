package com.webdemo.demospringboot.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webdemo.demospringboot.model.Thanhvien;
import com.webdemo.demospringboot.model.ThietBi;
import com.webdemo.demospringboot.model.ThongTinSD;
import com.webdemo.demospringboot.repository.DatCho_User_Repository;
import com.webdemo.demospringboot.repository.ThietBiRepository;
import com.webdemo.demospringboot.service.DatCho_UserService;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
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
    HttpSession HttpSession;

    @Autowired
    private DatCho_UserService DatchoService;

    private String convertObjectToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    @PostMapping("/home/bookBevice")
    @ResponseBody
    public ResponseEntity<String> handleBookDeviceAction(@RequestBody Map<String, String> payload, HttpSession session) {
        try {
            List<ThietBi> cartDevice = (List<ThietBi>) session.getAttribute("cartDevice");
            String maTVString = (String) HttpSession.getAttribute("maTV");
            int maTV = Integer.parseInt(maTVString);
            //chuyên ngay gio
            String selectedDateTimeStr = payload.get("selectedDateTime");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
            LocalDateTime utcDateTime = LocalDateTime.parse(selectedDateTimeStr, formatter);
            ZonedDateTime utcZonedDateTime = utcDateTime.atZone(ZoneId.of("UTC"));
            ZonedDateTime vietnamZonedDateTime = utcZonedDateTime.withZoneSameInstant(ZoneId.of("Asia/Ho_Chi_Minh"));
            LocalDateTime vietnamLocalDateTime = vietnamZonedDateTime.toLocalDateTime();

            LocalDate selectedDate = vietnamLocalDateTime.toLocalDate();
            LocalDate today = LocalDate.now();
            //danh sach trang thai thiet bi
            List<Map<String, Object>> trangthaiDevice = new ArrayList<>();

            if (cartDevice != null) {
                for (ThietBi thietBi : cartDevice) {
                    int maTB = thietBi.getMaTB();
                    String tenTB = thietBi.getTenTB();
                    Map<String, Object> status = new HashMap<>();

                    if (selectedDate.equals(today)) {
                        boolean countCurrentBorrowed = DatchoService.checkIfThietBiAlreadyReserved(maTB);
                        if (countCurrentBorrowed) {
                            status.put("maTB", maTB);
                            status.put("trangthai", " đã có người đặt chỗ mượn.");
                            trangthaiDevice.add(status);
                        }

                        boolean currentReservations_current = DatchoService.checkIfThietBiAlreadyBorrowed(maTB);
                        if (currentReservations_current) {
                            status.put("maTB", maTB);
                            status.put("trangthai", " đã có người mượn.");
                            trangthaiDevice.add(status);
                        }
                    } else {
                        boolean currentReservations_not = DatchoService.checkIfThietBiAlreadyReservedOnDate(maTB, vietnamLocalDateTime);
                        if (currentReservations_not) {
                            status.put("maTB", maTB);
                            status.put("trangthai", " đã có người đặt chỗ mượn.");
                            trangthaiDevice.add(status);
                        }
                    }
                }
            }
            for (Map<String, Object> item : trangthaiDevice) {
                System.out.println("MaTB: " + item.get("maTB") + ", Trang thai: " + item.get("trangthai"));
            }
            if (trangthaiDevice.isEmpty()) {
                for (ThietBi thietBi : cartDevice) {
                    Thanhvien thanhvien = new Thanhvien();
                    thanhvien.setId(maTV);
                    ThongTinSD thongTinSD = new ThongTinSD();
                    thongTinSD.setMaTT((int) (DatchoService.countThongTinSD() + 1));
                    thongTinSD.setThietBi(thietBi);
                    thongTinSD.setThanhVien(thanhvien);
                    thongTinSD.setThoiGianDatCho(vietnamLocalDateTime);
                    DatchoService.saveThongTinSD(thongTinSD);
                }
                return ResponseEntity.ok("success");

            } else {
                String jsonTrangThaiDevice = convertObjectToJson(trangthaiDevice);
                // Trả về đối tượng JSON String trong ResponseEntity<String>
                return ResponseEntity.ok(jsonTrangThaiDevice);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi khi xử lý đặt chỗ thiết bị.");
        }
    }
}
