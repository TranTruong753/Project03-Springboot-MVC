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
public interface XemThietBiDangMuonRepository extends JpaRepository<ThongTinSD, Integer> {
    
//    @Query("SELECT concat(t.thietBi.maTB,',',t.thietBi.tenTB,',',t.thoiGianMuon) ten FROM ThongTinSD t, ThietBi tb WHERE t.thanhVien.id = :userId AND t.thoiGianTra IS NULL AND t.thoiGianMuon IS NOT NULL")
//    @Query("SELECT t FROM ThongTinSD t WHERE t.thanhVien.id = :userId")
//      @Query("SELECT concat(\"*Mã Thiết Bị: \",t.thietBi.maTB,'\n Tên Thiết Bị:',t.thietBi.tenTB,'\n Thời gian mượn:',t.thoiGianMuon,'\n_____________________\n')   ten  FROM ThongTinSD  t, ThietBi  tb  WHERE t.thanhVien.id = :userId  AND t.thoiGianTra IS NULL AND t.thoiGianMuon IS NOT NULL AND t.thietBi.maTB = tb.maTB")
    @Query("SELECT t FROM ThongTinSD t  WHERE t.thanhVien.id = :userId AND t.thoiGianTra IS NULL AND t.thoiGianMuon IS NOT NULL")
    List<ThongTinSD> XemThietBiDangMuonRepository(@Param("userId") int userId);
}
