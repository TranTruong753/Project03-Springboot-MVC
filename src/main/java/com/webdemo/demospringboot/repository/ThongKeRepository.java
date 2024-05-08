/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.webdemo.demospringboot.repository;

import com.webdemo.demospringboot.model.Thanhvien;
import com.webdemo.demospringboot.model.ThongTinSD;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ACER
 */
@Repository
public interface ThongKeRepository extends JpaRepository<ThongTinSD, Integer>{
    @Query("SELECT COUNT(ttsd.thanhVien.id) " +
           "FROM ThongTinSD ttsd " +
           "WHERE DATE(ttsd.thoiGianVao) = DATE(:date)")
    Long countTvByDate(@Param("date") String date);
    
    @Query("SELECT COUNT(ttsd) " +
           "FROM ThongTinSD ttsd " +
           "WHERE ttsd.thoiGianTra IS NULL")
    Long countThietBiDangDuocMuonByDate(String date);
    
    @Query("SELECT COUNT(xl.MaXL) " +
           "FROM Xuly xl " +
           "INNER JOIN Thanhvien tv ON tv.id = xl.MaTV " +
           "WHERE xl.TrangThaiXL = 0 ")
    Long countViPhamDangXuLYByDate();
    
    @Query("SELECT tv.khoa, COUNT(tv.id) FROM Thanhvien tv GROUP BY tv.khoa")
    List<Object[]> getKhoaAndCountKhoa();
}


