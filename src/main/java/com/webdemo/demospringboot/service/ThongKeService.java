/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.webdemo.demospringboot.service;

import java.util.List;



public interface ThongKeService {
    Long countTvByDate(String date);
    Long countThietBiDangDuocMuonByDate();
    Long countViPhamDangXuLYByDate();
    List<Object[]> getKhoaAndCountKhoa();
    List <Object[]> getKhoa_and_cout_Khoa_ByDate(String date);
    
    List <Object[]> getNganh_and_cout_Nganh();
    List <Object[]> getNganh_and_cout_Nganh_ByDate(String date);
    
    List<Object[]> countSoLanThietBiDuocMuon();
    List<Object[]> countSoLanThietBiDuocMuonTheoThoiGian(String date);
    List<String> findAllYearsXuLy();

    List<Object[]> findRowCountByMonth(String year);

    int sumTienBoiThuong();

    List<Object[]> getHinhThucXL_and_cout_HinhThucXL();

    List<Object[]> getHinhThucXL_and_cout_HinhThucXL_ByDate(String date);


}
