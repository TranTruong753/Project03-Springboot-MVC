/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.webdemo.demospringboot.service;

import com.webdemo.demospringboot.model.ThongTinSD;
import java.util.List;

/**
 *
 * @author ACER
 */
public interface XemThietBiDatChoService {
    List<ThongTinSD> layDanhSachThietBiDatCho(int maTV);
}
