package com.webdemo.demospringboot.controller;

import com.webdemo.demospringboot.model.Thanhvien;
import com.webdemo.demospringboot.model.ThietBi;
import com.webdemo.demospringboot.service.ThanhVienService;
import com.webdemo.demospringboot.service.ThietBiService;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin/member")
public class AdminMemberController {
    @Autowired
    private ThanhVienService tvService;
    
    @GetMapping
    public String index(Model model,@RequestParam(name="pageNo",defaultValue = "1") Integer pageNo) {
        Page<Thanhvien> listthanhvien=tvService.getAll(pageNo);
        model.addAttribute("listthanhvien", listthanhvien);
        model.addAttribute("totalPage", listthanhvien.getTotalPages());
        model.addAttribute("currentlPage", pageNo);
        return "admin/member";
    }

    @GetMapping("check_in")
    public String check_in() {
        return "admin/check_in";
    }

    @GetMapping("add_member")
    public String add_member(Model model) {
        Thanhvien tv= new Thanhvien();
        model.addAttribute("thanhvien", tv);
        return "admin/add_member";
    }
    @RequestMapping(value="/saveMember",method = RequestMethod.POST)
    public String add_member(@ModelAttribute("thanhvien") Thanhvien thanhvien ,Model model,@RequestParam(name="pageNo",defaultValue = "1") Integer pageNo) {
        List<Thanhvien> listthanhvien=tvService.GetAll();
        boolean flag=false;
        for (Thanhvien tv : listthanhvien) {
            
            if(tv.getId()==thanhvien.getId()){
                 flag=true;
                 break;
            }
        }
        if(flag){
            model.addAttribute("thanhvien", thanhvien);
            model.addAttribute("message", "Mã thành viên bị trùng");
            return "admin/add_member";
        }
        else{
            tvService.save(thanhvien);
            return index( model,pageNo);
        }
    }
    
    
        @RequestMapping("edit_member/{id}")
    public ModelAndView show_edit_device(@PathVariable(name="id") int id) {
        ModelAndView mav= new ModelAndView("admin/edit_member");
        Thanhvien tv=tvService.search(id);
        mav.addObject("thanhvien", tv);
        return mav;
    }
    
    @RequestMapping(value="/save_edit",method = RequestMethod.POST)
    public String save_edit_device(@ModelAttribute("thanhvien") Thanhvien thanhvien ,Model model,@RequestParam(name="pageNo",defaultValue = "1") Integer pageNo) {
        
            tvService.save(thanhvien);
            return index( model,pageNo);
        
    }
    @RequestMapping("delete_member/{id}")
    public String delete(@PathVariable(name="id") int id ,Model model,@RequestParam(name="pageNo",defaultValue = "1") Integer pageNo ) {
        try {
            tvService.delete(id);
            model.addAttribute("message", "Xóa thành công");
            
            
        } catch (Exception e) {
            model.addAttribute("message", "thành viên đang bị vi phạm,phải xử lý trước khi xóa");
        }
        return index( model,pageNo);
        
        
    }
    @RequestMapping(value = "/upload_excel", method = RequestMethod.POST)
    public String uploadExcel(@RequestParam("file") MultipartFile file, Model model,@RequestParam(name="pageNo",defaultValue = "1") Integer pageNo) {
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
                    if (row.getRowNum() == 0) continue;

                    Thanhvien thanhvien = new Thanhvien();
                    int matv = Integer.parseInt(row.getCell(0).getStringCellValue());
                    thanhvien.setId(matv);
                    thanhvien.setHoTen(row.getCell(1).getStringCellValue());
                    thanhvien.setKhoa(row.getCell(2).getStringCellValue());
                    thanhvien.setNganh(row.getCell(3).getStringCellValue());
                    thanhvien.setSdt(row.getCell(4).getStringCellValue());
                    thanhvien.setEmail(row.getCell(5).getStringCellValue());
                    thanhvien.setPassword(row.getCell(6).getStringCellValue());

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
        return index( model,pageNo);
    }

    @GetMapping("borrow_device")
    public String borrow_device() {
        return "admin/borrow_device";
    }

}
