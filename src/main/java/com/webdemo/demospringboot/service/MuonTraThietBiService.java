/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webdemo.demospringboot.service;

import com.webdemo.demospringboot.model.ThietBi;
import com.webdemo.demospringboot.model.ThongTinSD;
import com.webdemo.demospringboot.repository.MuonTraRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class MuonTraThietBiService {
    @Autowired
    MuonTraRepository muonTraRepository;
    
    public List<ThongTinSD> findAllThongTinSD()
    {
        return muonTraRepository.findAllThongTinSD();
    }
    
    public List<ThongTinSD> findAll()
    {
        return muonTraRepository.findAll();
    }
    
    public List<ThongTinSD> findTTSDDangMuon()
    {
        return muonTraRepository.findTTSDDangMuon();
    }
    
    public void save(ThongTinSD ttsd){
        muonTraRepository.save(ttsd);
    } 
    
    
}
