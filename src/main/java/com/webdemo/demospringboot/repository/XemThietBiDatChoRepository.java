/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.webdemo.demospringboot.repository;

import com.webdemo.demospringboot.model.ThietBi;
import com.webdemo.demospringboot.model.ThongTinSD;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface XemThietBiDatChoRepository extends JpaRepository<ThongTinSD, Integer> {

    @Query("SELECT ttsd.thietBi.maTB, tb.tenTB, ttsd.thoiGianDatCho " +
           "FROM ThongTinSD ttsd " +
           "JOIN ttsd.thietBi tb " +
           
           "WHERE ttsd.thanhVien.id = :maTV " + 
           "AND ttsd.thoiGianDatCho IS NOT NULL ")
//           "AND TIMESTAMPDIFF(SECOND, ttsd.thoiGianDatCho, CURRENT_TIMESTAMP) < 3600")
    List<Object[]> findThietBiByMaTVAndThoiGianDatCho(@Param("maTV") int maTV);
    
//    @Transactional
//    @Modifying   
//    @Query("INSERT INTO ThongTinSD (thanhVien.id, thoiGianVao) " +
//       "VALUES (:userId,:tgvao)")
//    public void themThongTinSD(@Param("userId") int userId,@Param("tgvao") LocalDateTime tgvao);
    
    @Query("SELECT ttsd FROM ThongTinSD ttsd " +
           
           "INNER JOIN ttsd.thanhVien tv " +
          
           "WHERE ttsd.thoiGianVao IS NOT NULL ")
//           "AND TIMESTAMPDIFF(SECOND, ttsd.thoiGianDatCho, CURRENT_TIMESTAMP) < 3600")
    List<ThongTinSD> getdsvaokhuhoctap();
}

