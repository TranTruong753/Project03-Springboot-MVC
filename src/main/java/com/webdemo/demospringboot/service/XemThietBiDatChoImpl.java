/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webdemo.demospringboot.service;


import com.webdemo.demospringboot.model.ThongTinSD;
import com.webdemo.demospringboot.repository.XemThietBiDatChoRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

/**
 *
 * @author ACER
 */
@Service
public class XemThietBiDatChoImpl implements  XemThietBiDatChoService{
    @Autowired
    public XemThietBiDatChoRepository xemThietBiDatChoRepository;

    
    @Override
    public List<ThongTinSD> layDanhSachThietBiDatCho(int maTV) {
        // Call the repository method to fetch the list of ThongTinSD entities
        return xemThietBiDatChoRepository.findThietBiByMaTVAndThoiGianDatCho(maTV);
    }
    
    @Override
    public void themThongTinSD(ThongTinSD ttsd){
        xemThietBiDatChoRepository.save(ttsd);
    };
    
    @Override
    public List<ThongTinSD> getdsvaokhuhoctap(){
        return xemThietBiDatChoRepository.getdsvaokhuhoctap();
    };
}
