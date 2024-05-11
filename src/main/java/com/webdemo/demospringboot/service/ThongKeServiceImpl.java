/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webdemo.demospringboot.service;

import com.webdemo.demospringboot.repository.ThongKeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ThongKeServiceImpl implements  ThongKeService{
    @Autowired
    private ThongKeRepository thongKeRepository;

    @Override
    public Long countTvByDate(String date) {
        return thongKeRepository.countTvByDate(date);
    }
    
    @Override
    public Long countThietBiDangDuocMuonByDate(String date) {
        return thongKeRepository.countThietBiDangDuocMuonByDate(date);
    }
    
    @Override
    public Long countViPhamDangXuLYByDate( ) {
        return thongKeRepository.countViPhamDangXuLYByDate();
    }
    
    @Override
    public List<Object[]> getKhoaAndCountKhoa() {
        return thongKeRepository.getKhoaAndCountKhoa();
    }
    
    @Override
    public List<Object[]> countSoLanThietBiDuocMuon() {
        return thongKeRepository.countSoLanThietBiDuocMuon();
    }
    
    @Override
    public List<Object[]> countSoLanThietBiDuocMuonTheoThoiGian(String date) {
        return thongKeRepository.countSoLanThietBiDuocMuonTheoThoiGian(date);
    }
}
