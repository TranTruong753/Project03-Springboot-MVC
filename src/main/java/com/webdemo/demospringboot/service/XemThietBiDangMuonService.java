/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webdemo.demospringboot.service;

import com.webdemo.demospringboot.model.ThongTinSD;
import com.webdemo.demospringboot.repository.XemThietBiDangMuonRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class XemThietBiDangMuonService  {
    
    @Autowired
    private XemThietBiDangMuonRepository repo ;
    
    public List<ThongTinSD> LayDanhSachDangMuon(int MaTV)
    {
       return repo.XemThietBiDangMuonRepository(MaTV);
    }
    
}
