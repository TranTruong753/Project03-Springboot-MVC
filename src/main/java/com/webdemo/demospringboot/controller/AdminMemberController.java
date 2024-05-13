package com.webdemo.demospringboot.controller;

import com.webdemo.demospringboot.model.Thanhvien;
import com.webdemo.demospringboot.model.ThietBi;
import com.webdemo.demospringboot.model.ThongTinSD;
import com.webdemo.demospringboot.model.Xuly;
import com.webdemo.demospringboot.service.MuonTraThietBiService;
import com.webdemo.demospringboot.service.ThanhVienService;
import com.webdemo.demospringboot.service.ThietBiService;
import com.webdemo.demospringboot.service.ThietBiServiceImpl;

import jakarta.servlet.http.HttpServletRequest;

import com.webdemo.demospringboot.service.XemThietBiDatChoService;
import com.webdemo.demospringboot.service.XulyService;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin/member")
public class AdminMemberController {

    @Autowired
    private ThietBiServiceImpl thietBiService;
    @Autowired
    private XulyService xulyService;
    @Autowired
    private ThanhVienService tvService;
    @Autowired
    private XemThietBiDatChoService xemtbservice;
    @Autowired
    private MuonTraThietBiService MuonTraThietBiService;
    @Autowired
    private ThanhVienService ThanhVienService;

    @GetMapping
    public String index(Model model, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
        Page<Thanhvien> listthanhvien = tvService.getAll(pageNo);
        model.addAttribute("listthanhvien", listthanhvien);
        model.addAttribute("totalPage", listthanhvien.getTotalPages());
        model.addAttribute("currentlPage", pageNo);
        return "admin/member";
    }
//  mượn trả thiết bị

    @GetMapping("borrow_device")
    public String borrow_device(Model model) {
        List<ThongTinSD> listThongTinSD = MuonTraThietBiService.findAllThongTinSD();
        model.addAttribute("listMuonTra", listThongTinSD);
        return "admin/borrow_device";
    }

//////////////////////////////
    @GetMapping("check_in")
    public String check_in(Model model) {
        List<ThongTinSD> listThongTinSD = xemtbservice.getdsvaokhuhoctap();
        model.addAttribute("listThongTinSD", listThongTinSD);
        ThongTinSD ttsd = new ThongTinSD();
        model.addAttribute("ThongTinSD", ttsd);
        return "admin/check_in";
    }
//    Xác nhận mượn

    @RequestMapping(value = "check_in/save", method = RequestMethod.POST)
    public String save_check_in(@ModelAttribute("ThongTinSD") ThongTinSD ttsd, Model model) {
        List<Thanhvien> listthanhvien = tvService.GetAll();
        boolean flag = false;
        int dem = 0;
        List<ThongTinSD> listThongTinSD = xemtbservice.getdsvaokhuhoctap();
        for (ThongTinSD tv : listThongTinSD) {
            if (tv.getMaTT() > dem) {
                dem = tv.getMaTT();
            }
        }
        dem++;
        for (Thanhvien tv : listthanhvien) {
            String matv = "" + tv.getId();
            String ttsdmatv = "" + ttsd.getThanhVien().getId();
            if (matv.equals(ttsdmatv)) {
                flag = true;
                //break;
            }
        }
        if (flag) {
            boolean flag1 = true;
            List<Xuly> XulyList = xulyService.layDanhSachViPhamTheoID(ttsd.getThanhVien().getId());
            for (Xuly xl : XulyList) {
                if (xl.getTrangThaiXL() == 0) {
                    flag1 = false;
                    break;
                }
            }
            if (flag1) {
                LocalDateTime currentDateTime = LocalDateTime.now();
                ttsd.setThoiGianVao(currentDateTime);
                ttsd.setMaTT(dem);
                xemtbservice.themThongTinSD(ttsd);

                //model.addAttribute("thanhvien", thanhvien);
                model.addAttribute("message", "Thêm thành công");
            } else {
                model.addAttribute("ThongTinSD", ttsd);
                model.addAttribute("message", "Thành viên đang bị xử lý");
            }

            //return check_in( model);
        } else {
            model.addAttribute("ThongTinSD", ttsd);
            model.addAttribute("message", "Không phải thành viên");
            //return check_in( model);
        }
//        LocalDateTime currentDateTime = LocalDateTime.now();
//        ttsd.setThoiGianVao(currentDateTime);
//        ttsd.setMaTT(3);
//        xemtbservice.themThongTinSD(ttsd);
        listThongTinSD = xemtbservice.getdsvaokhuhoctap();
        model.addAttribute("listThongTinSD", listThongTinSD);
        return "admin/check_in";

    }

    @GetMapping("add_member")
    public String add_member(Model model) {
        Thanhvien tv = new Thanhvien();
        model.addAttribute("thanhvien", tv);
        return "admin/add_member";
    }

    @RequestMapping(value = "/saveMember", method = RequestMethod.POST)
    public String add_member(@ModelAttribute("thanhvien") Thanhvien thanhvien, Model model, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
        List<Thanhvien> listthanhvien = tvService.GetAll();
        boolean flag = false;
        for (Thanhvien tv : listthanhvien) {

            if (tv.getId() == thanhvien.getId()) {
                flag = true;
                break;
            }
        }
        if (flag) {
            model.addAttribute("thanhvien", thanhvien);
            model.addAttribute("message", "Mã thành viên bị trùng");
            return "admin/add_member";
        } else {
            model.addAttribute("message", "Thêm thành công");
            tvService.save(thanhvien);
            return index(model, pageNo);
        }
    }

    @RequestMapping("edit_member/{id}")
    public ModelAndView show_edit_device(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("admin/edit_member");
        Thanhvien tv = tvService.search(id);
        mav.addObject("thanhvien", tv);
        return mav;
    }

    @RequestMapping(value = "/save_edit", method = RequestMethod.POST)
    public String save_edit_device(@ModelAttribute("thanhvien") Thanhvien thanhvien, Model model, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
        model.addAttribute("message", "Upadte thành công");
        tvService.save(thanhvien);
        return index(model, pageNo);

    }

    @RequestMapping("delete_member/{id}")
    public String delete(@PathVariable(name = "id") int id, Model model, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
        try {
            tvService.delete(id);
            model.addAttribute("message", "Xóa thành công");

        } catch (Exception e) {
            model.addAttribute("message", "Xung đột cơ sở dữ liệu, không thể xóa");
        }
        return index(model, pageNo);

    }

    @RequestMapping(value = "/upload_excel", method = RequestMethod.POST)
    public String uploadExcel(@RequestParam("file") MultipartFile file, Model model, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
        if (!file.isEmpty()) {
            try {
                List<Thanhvien> listthanhvien = new ArrayList<>();

                // Tạo một Workbook từ file Excel
                Workbook workbook = WorkbookFactory.create(file.getInputStream());

                // Lấy sheet đầu tiên
                Sheet sheet = workbook.getSheetAt(0);

                // Lặp qua từng dòng trong sheet và đọc dữ liệu
                for (Row row : sheet) {
                    // Bỏ qua dòng tiêu đề
                    if (row.getRowNum() == 0) {
                        continue;
                    }

                    Thanhvien thanhvien = new Thanhvien();
                    int matv = (int) row.getCell(0).getNumericCellValue();
                    thanhvien.setId(matv);
                    thanhvien.setHoTen(row.getCell(1).getStringCellValue());
                    thanhvien.setKhoa(row.getCell(2).getStringCellValue());
                    thanhvien.setNganh(row.getCell(3).getStringCellValue());
                    thanhvien.setSdt(row.getCell(4).getStringCellValue());
                    int pass = (int) row.getCell(5).getNumericCellValue();
                    thanhvien.setPassword(pass+"");
                    thanhvien.setEmail(row.getCell(6).getStringCellValue());
                    

                    listthanhvien.add(thanhvien);
                }

                // Lưu danh sách thiết bị vào cơ sở dữ liệu
                tvService.saveAll(listthanhvien);

                model.addAttribute("message", "Import file Excel thành công.");
            } catch (Exception e) {
                model.addAttribute("message", "Đã xảy ra lỗi khi import file Excel: " + e.getMessage());
            }
        } else {
            model.addAttribute("message", "Vui lòng chọn file Excel để import.");
        }
        return index(model, pageNo);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    String delete(HttpServletRequest request) {
        String id = request.getParameter("id");
        String mess = ":";
        List<Thanhvien> listthanhvien = tvService.GetAll();

        for (Thanhvien tv : listthanhvien) {
            String maTv = String.valueOf(tv.getId());

            // Kiểm tra xem kí tự thứ 3 và 4 của maTv có chứa id hay không
            if (maTv.length() >= 4 && maTv.substring(2, 4).equals(id)) {
                try {
                    tvService.delete(tv.getId());
                } catch (Exception e) {
                    mess = mess + " " + tv.getId();
                }
            }
        }
        return mess;
    }

    @RequestMapping(value = "/save_borrow_device", method = RequestMethod.POST)
    public @ResponseBody
    String save_borrow_device(HttpServletRequest request) {
        String mess = "";
        String MaTV = request.getParameter("MaTV").toString().trim();
        String MaTB = request.getParameter("MaTB").toString().trim();
        System.out.println("MaTV: " + MaTV);
        System.out.println("MaTB: " + MaTB);
        boolean checkTTSDNotContaint = true;
        boolean checkTVContaint = false;
        boolean checkTBContaint = false;
        List<ThongTinSD> listThongTinSDAll = MuonTraThietBiService.findAll();
        List<ThongTinSD> listThongTinSD = MuonTraThietBiService.findTTSDDangMuon();
        List<ThietBi> listThietBi = thietBiService.layDanhSachThietBi();
        List<Thanhvien> listThanhVien = ThanhVienService.GetAll();

        System.out.println("Danh Sách Thành Viên");
        for (Thanhvien thanhvien : listThanhVien) {
            System.out.println("MaTV: " + MaTV);
            System.out.println("Mã Thành Viên: " + thanhvien.getId());
            System.out.println("equal: " + MaTV.equals(thanhvien.getId().toString()));
            if (MaTV.equals(thanhvien.getId() + "")) {
                System.out.println("Dô THành Viên");
                checkTVContaint = true;
                break;
            }
        }
        System.out.println("Danh Sách Thiết bị");
        for (ThietBi thietBi : listThietBi) {

            System.out.println("Mã Thiết Bị: " + thietBi.getMaTB());
            System.out.println("MaTB: " + MaTB);
            System.out.println("equal: " + MaTV.equals(thietBi.getMaTB() + ""));
            if (MaTB.equals(thietBi.getMaTB() + "")) {
                System.out.println("Dô Thiết bị");
                checkTBContaint = true;
                break;
            }
        }
        if (checkTVContaint == true && checkTBContaint == true) {
//            System.out.println("______________________________________________________________________________________________________________");
//            System.out.println(MaTV+"-"+MaTB+"hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
            for (ThongTinSD thongTinSD : listThongTinSD) {
//                System.out.println(thongTinSD.getThietBi().getMaTB());
                if (MaTB.equals(thongTinSD.getThietBi().getMaTB() + "") == true) {
                    checkTTSDNotContaint = false;
                    mess = "Thiết bị đang được mượn!";
                    break;
                }
            }
            if (checkTTSDNotContaint == true) {
                ThongTinSD ttsd = new ThongTinSD();
                LocalDateTime currentDateTime = LocalDateTime.now();
                ttsd.setThietBi(thietBiService.get(Integer.parseInt(MaTB)));
                ttsd.setThanhVien(ThanhVienService.search(Integer.parseInt(MaTV)));
                ttsd.setMaTT(listThongTinSDAll.get(listThongTinSDAll.size() - 1).getMaTT() + 1);
                ttsd.setThoiGianMuon(currentDateTime);
                mess = ":";
                MuonTraThietBiService.save(ttsd);

            }
        } else {
            if (checkTVContaint == false) {
                mess += "Thành viên không tồn tại\n";
            }
            if (checkTBContaint == false) {
                mess += "Thiết bị không tồn tại\n";
            }
        }

        return mess;

    }

//    save_pay_device
    @RequestMapping(value = "/save_pay_device", method = RequestMethod.POST)
    public @ResponseBody
    String save_pay_device(HttpServletRequest request) {
        String mess = "";
        String MaTB = request.getParameter("MaTB").toString().trim();
        boolean checkTTSDContaint = false;
        List<ThongTinSD> listThongTinSD = MuonTraThietBiService.findTTSDDangMuon();

        ThongTinSD current = null;
        for (ThongTinSD thongTinSD : listThongTinSD) {
                System.out.println(thongTinSD.getThietBi().getMaTB());

            if (MaTB.equals(thongTinSD.getThietBi().getMaTB() + "") == true) {
                checkTTSDContaint = true;
                current = thongTinSD;
                break;
            }
        }
        if (checkTTSDContaint == true) {
            LocalDateTime currentDateTime = LocalDateTime.now();
            current.setThoiGianTra(currentDateTime);
            MuonTraThietBiService.save(current);
            mess = ":";
        } else {
            if (checkTTSDContaint == false) {
                mess += "Thiết bị chưa được mượn\n";
            }

        }
        return mess;

    }
}
