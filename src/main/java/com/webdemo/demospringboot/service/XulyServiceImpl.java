/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webdemo.demospringboot.service;

import com.webdemo.demospringboot.model.Xuly;
import com.webdemo.demospringboot.repository.XulyRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Administrator
 */
@Service
public class XulyServiceImpl implements XulyService{
    @Autowired
    public XulyRepository xulyRepository;

//    @Override
    public List<Xuly> layDanhSachViPhamTheoID(int maTV) {
        return xulyRepository.layDanhSachViPhamTheoID(maTV);
    }
    
}
