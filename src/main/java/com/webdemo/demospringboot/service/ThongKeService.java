/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.webdemo.demospringboot.service;

import java.util.List;



public interface ThongKeService {
    Long countTvByDate(String date);
    Long countThietBiDangDuocMuonByDate(String date);
    Long countViPhamDangXuLYByDate();
    List<Object[]> getKhoaAndCountKhoa();
    List<Object[]> countSoLanThietBiDuocMuon();
    List<Object[]> countSoLanThietBiDuocMuonTheoThoiGian(String date);
    List<String> findAllYearsXuLy();

    List<Object[]> findRowCountByMonth(String year);

}
