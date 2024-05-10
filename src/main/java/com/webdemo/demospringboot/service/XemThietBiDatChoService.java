/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.webdemo.demospringboot.service;

import com.webdemo.demospringboot.model.ThongTinSD;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author ACER
 */
public interface XemThietBiDatChoService {
    List<ThongTinSD> layDanhSachThietBiDatCho(int maTV);
    public void themThongTinSD(ThongTinSD ttsd);
    List<ThongTinSD> getdsvaokhuhoctap();
}
