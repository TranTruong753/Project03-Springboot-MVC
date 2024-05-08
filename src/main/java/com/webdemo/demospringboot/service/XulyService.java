/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.webdemo.demospringboot.service;

import com.webdemo.demospringboot.model.Xuly;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface XulyService {
    List<Xuly> layDanhSachViPhamTheoID(int maTV);
    List<Xuly> layDanhSachXuLi();
    void save(Xuly xuly);
    void delete(int id);
}
