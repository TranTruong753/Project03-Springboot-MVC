/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.webdemo.demospringboot.repository;

import com.webdemo.demospringboot.model.ThongTinSD;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author DELL
 */
@Repository
public interface MuonTraRepository extends JpaRepository<ThongTinSD, Integer>{
    @Query("SELECT ttsd FROM ThongTinSD ttsd " +
           "INNER JOIN ttsd.thietBi tb " +
           "INNER JOIN ttsd.thanhVien tv " +
           "WHERE  ttsd.thoiGianMuon IS NOT NULL" +
            "")
    List<ThongTinSD> findAllThongTinSD();
    
    @Query("SELECT t FROM ThongTinSD t  WHERE t.thoiGianTra IS NULL AND t.thoiGianMuon IS NOT NULL")
    List<ThongTinSD> findTTSDDangMuon();

    @Query("SELECT t FROM ThongTinSD t "
            + "where t.thoiGianTra IS NULL "
            + "AND t.thoiGianMuon IS NULL "
            + "AND t.thoiGianVao IS NULL "
            + "AND t.thoiGianDatCho IS NOT NULL "
            + "AND DATE(t.thoiGianDatCho) = CURRENT_DATE()"
            + "AND t.thoiGianDatCho > DATEADD(HOUR, -1, CURRENT_TIMESTAMP)")
    List<ThongTinSD> findDatCho();
}
