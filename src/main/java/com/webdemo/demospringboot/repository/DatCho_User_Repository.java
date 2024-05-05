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
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DatCho_User_Repository extends JpaRepository<ThongTinSD, Integer> {

//    @Modifying
//    @Transactional
//    @Query("UPDATE ThongTinSD t SET t.thoiGianTra = CURRENT_TIMESTAMP WHERE t.thietBi.id = :maTB AND t.thanhVien.id = :maTV AND t.thoiGianTra IS NULL")
//    void updateThoiGianTraByMaTBAndMaTVAndThoiGianTraIsNull(@Param("maTB") int maTB, @Param("maTV") int maTV);

    @Query("SELECT COUNT(t) FROM ThongTinSD t")
    int countThongTinSD();
    // kiem tra xem thiet bi truyen vao co ng dat muon truoc do ch
    // thiet bi duoc dat cho nêu: thoigiandatcho khong null và bé hon 1h so vs thoigianhientai || và các thoigian kia phai null
    @Query("SELECT COUNT(t) FROM ThongTinSD t "
            + "WHERE t.thietBi.id = :maTB "
            + "AND t.thoiGianTra IS NULL "
            + "AND t.thoiGianMuon IS NULL "
            + "AND t.thoiGianVao IS NULL "
            + "AND t.thoiGianDatCho IS NOT NULL "
            + "AND TIMESTAMPDIFF(SECOND, t.thoiGianDatCho, CURRENT_TIMESTAMP) < 3600")
    int countCurrentReservationsByMaTB(@Param("maTB") int maTB);
    //kiem tra xem thiet bi truyen vao co ng dang muon khong 
    
     @Query("SELECT COUNT(t) FROM ThongTinSD t "
            + "WHERE t.thietBi.id = :maTB "
            + "AND t.thoiGianMuon IS NOT NULL "
//            + "AND t.thoiGianDatCho IS NOT NULL "
            + "AND t.thoiGianTra IS NULL "
            + "AND t.thoiGianVao IS NULL")
    int countCurrentBorrowedReservationsByMaTB(@Param("maTB") int maTB);
}
