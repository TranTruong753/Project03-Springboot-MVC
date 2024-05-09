/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webdemo.demospringboot.controller;

import com.webdemo.demospringboot.model.ThietBi;
import com.webdemo.demospringboot.service.ThietBiService;
import com.webdemo.demospringboot.service.ThietBiServiceImpl;
import java.util.ArrayList;
import java.util.List;
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
import org.apache.poi.ss.usermodel.*;

@Controller
@RequestMapping("admin/device")
public class AdminDeviceController {
    @Autowired
    private ThietBiService thietBiService;
    
    @GetMapping
    public String index(Model model,@RequestParam(name="pageNo",defaultValue = "1") Integer pageNo)
    {   
        Page<ThietBi> listthietbi=thietBiService.getAll(pageNo);
        model.addAttribute("listthietbi", listthietbi);
        model.addAttribute("totalPage", listthietbi.getTotalPages());
        model.addAttribute("currentlPage", pageNo);
        return "admin/device";
    }
    
    @GetMapping("add_device")
    public String add_device(Model model) {
        ThietBi tb= new ThietBi();
        model.addAttribute("thietbi", tb);
        return "admin/add_device";
    }
    
     @RequestMapping(value="/save",method = RequestMethod.POST)
    public String add_device(@ModelAttribute("thietbi") ThietBi thietbi ,Model model,@RequestParam(name="pageNo",defaultValue = "1") Integer pageNo) {
        List<ThietBi> listthietbi=thietBiService.layDanhSachThietBi();
        boolean flag=false;
        for (ThietBi tb : listthietbi) {
            
            if(tb.getMaTB()==thietbi.getMaTB()){
                 flag=true;
                 break;
            }
        }
        if(flag){
            model.addAttribute("thietbi", thietbi);
            model.addAttribute("message", "Mã thiệt bị trùng");
            return "admin/add_device";
        }
        else{
            thietBiService.save(thietbi);
            return index( model,pageNo);
        }
    }
        @RequestMapping("edit_device/{id}")
    public ModelAndView show_edit_device(@PathVariable(name="id") int id) {
        ModelAndView mav= new ModelAndView("admin/edit_device");
        ThietBi tb=thietBiService.findByMaTB(id);
        mav.addObject("thietbi", tb);
        return mav;
    }
    
    @RequestMapping(value="/save_edit",method = RequestMethod.POST)
    public String save_edit_device(@ModelAttribute("thietbi") ThietBi thietbi ,Model model,@RequestParam(name="pageNo",defaultValue = "1") Integer pageNo) {
        
            thietBiService.save(thietbi);
            return index( model,pageNo);
        
    }
    @RequestMapping("delete/{id}")
    public String delete(@PathVariable(name="id") int id ,Model model,@RequestParam(name="pageNo",defaultValue = "1") Integer pageNo ) {
        try {
            thietBiService.delete(id);
            model.addAttribute("message", "Xóa thành công");
        } catch (Exception e) {
            model.addAttribute("message", "Xung đột cơ sở dữ liệu, không thể xóa");
        }
        return index( model,pageNo);
    }
    
    @RequestMapping(value = "/upload_excel", method = RequestMethod.POST)
    public String uploadExcel(@RequestParam("file") MultipartFile file, Model model,@RequestParam(name="pageNo",defaultValue = "1") Integer pageNo) {
        if (!file.isEmpty()) {
            try {
                List<ThietBi> thietbiList = new ArrayList<>();

                // Tạo một Workbook từ file Excel
                Workbook workbook = WorkbookFactory.create(file.getInputStream());

                // Lấy sheet đầu tiên
                Sheet sheet = workbook.getSheetAt(0);

                // Lặp qua từng dòng trong sheet và đọc dữ liệu
                for (Row row : sheet) {
                    // Bỏ qua dòng tiêu đề
                    if (row.getRowNum() == 0) continue;

                    ThietBi thietbi = new ThietBi();
                    int matb = (int) row.getCell(0).getNumericCellValue();
                    thietbi.setMaTB(matb);
                    thietbi.setTenTB(row.getCell(1).getStringCellValue());
                    thietbi.setMoTaTB(row.getCell(2).getStringCellValue());

                    thietbiList.add(thietbi);
                }

                // Lưu danh sách thiết bị vào cơ sở dữ liệu
                thietBiService.saveAll(thietbiList);

                model.addAttribute("message", "Import file Excel thành công.");
            } catch (Exception e) {
                model.addAttribute("message", "Đã xảy ra lỗi khi import file Excel: " + e.getMessage());
            }
        } else {
            model.addAttribute("message", "Vui lòng chọn file Excel để import.");
        }
        return index( model,pageNo);
    }
    
    @GetMapping("edit_device")
    public String edit_device() {
        return "admin/edit_device";
    }
}
