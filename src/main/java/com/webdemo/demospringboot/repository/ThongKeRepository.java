/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.webdemo.demospringboot.repository;

import com.webdemo.demospringboot.model.Thanhvien;
import com.webdemo.demospringboot.model.ThongTinSD;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

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
           "WHERE ttsd.thoiGianMuon IS NOT NULL AND"
            + " ttsd.thoiGianTra IS NULL")
    Long countThietBiDangDuocMuonByDate();
    
    @Query("SELECT COUNT(xl.MaXL) " +
           "FROM Xuly xl " +
           "INNER JOIN Thanhvien tv ON tv.id = xl.MaTV " +
           "WHERE xl.TrangThaiXL = 0 ")
    Long countViPhamDangXuLYByDate();
    
    @Query("SELECT tv.khoa, COUNT(tv.id) FROM Thanhvien tv GROUP BY tv.khoa")
    List<Object[]> getKhoaAndCountKhoa();
    
    @Query("SELECT tv.khoa, COUNT(tv.id) " +
            "FROM Thanhvien tv " +
            "INNER JOIN ThongTinSD ttsd ON ttsd.thanhVien.id = tv.id " +
            "WHERE " +
            "( DATE_FORMAT(ttsd.thoiGianVao, '%Y-%m-%d') = :date " +
            "OR DATE_FORMAT(ttsd.thoiGianVao, '%Y-%m') = :date " +
            "OR DATE_FORMAT(ttsd.thoiGianVao, '%Y') = :date ) " +
            "GROUP BY tv.khoa")
    List <Object[]> getKhoa_and_cout_Khoa_ByDate(@Param("date") String date);
    

    @Query ("SELECT tv.nganh, COUNT(tv.id)  " +
                     "FROM Thanhvien tv " +
                     "GROUP BY tv.nganh")
    List <Object[]> getNganh_and_cout_Nganh();
    

    @Query("SELECT tv.nganh, COUNT(tv.id) " +
            "FROM Thanhvien tv " +
            "INNER JOIN ThongTinSD ttsd ON ttsd.thanhVien.id = tv.id " +
            "WHERE " +
            "( DATE_FORMAT(ttsd.thoiGianVao, '%Y-%m-%d') = :date " +
            "OR DATE_FORMAT(ttsd.thoiGianVao, '%Y-%m') = :date " +
            "OR DATE_FORMAT(ttsd.thoiGianVao, '%Y') = :date ) " +
            "GROUP BY tv.nganh")
    List <Object[]> getNganh_and_cout_Nganh_ByDate(@Param("date") String date);

      

    @Query("SELECT xl.HinhThucXL, COUNT(xl.MaXL) " +
           "FROM Xuly xl " +
           "WHERE xl.TrangThaiXL = 1 " +
           "GROUP BY xl.HinhThucXL")   
    List <Object[]> getHinhThucXL_and_cout_HinhThucXL();

       @Query("SELECT xl.HinhThucXL, COUNT(xl.MaXL) " +
              "FROM Xuly xl " +
              "WHERE xl.TrangThaiXL = 1  AND " +
              "( DATE_FORMAT(xl.NgayXL, '%Y-%m-%d') = :date " +
              "OR DATE_FORMAT(xl.NgayXL, '%Y-%m') = :date " +
              "OR DATE_FORMAT(xl.NgayXL, '%Y') = :date ) " +
              "GROUP BY xl.HinhThucXL")
    List <Object[]> getHinhThucXL_and_cout_HinhThucXL_ByDate(@Param("date") String date);

    
    @Query("SELECT tb.tenTB, COUNT(ttsd.thietBi.maTB) AS soluong " +
           "FROM ThongTinSD ttsd " +
           "JOIN ttsd.thietBi tb " +
           "WHERE ttsd.thoiGianMuon IS NOT NULL AND ttsd.thoiGianTra IS NOT NULL " +
           "GROUP BY tb.tenTB")
    List<Object[]> countSoLanThietBiDuocMuon();

    @Query("SELECT tb.tenTB, " +
       "CASE " +
       "    WHEN DATE_FORMAT(ttsd.thoiGianMuon, '%Y-%m-%d') = :date THEN COUNT(ttsd.thietBi.maTB) " +
       "    WHEN DATE_FORMAT(ttsd.thoiGianMuon, '%Y-%m') = :date THEN COUNT(ttsd.thietBi.maTB) " +
       "    WHEN DATE_FORMAT(ttsd.thoiGianMuon, '%Y') = :date THEN COUNT(ttsd.thietBi.maTB) " +
       "    ELSE 0 " +
       "END AS soluong " +
       "FROM ThongTinSD ttsd " +
       "JOIN ttsd.thietBi tb " +
       "WHERE ttsd.thoiGianTra IS NOT NULL AND " +
       "(DATE_FORMAT(ttsd.thoiGianMuon, '%Y-%m-%d') = :date " +
       "OR DATE_FORMAT(ttsd.thoiGianMuon, '%Y-%m') = :date " +
       "OR DATE_FORMAT(ttsd.thoiGianMuon, '%Y') = :date) " +
       "GROUP BY tb.tenTB")
    List<Object[]> countSoLanThietBiDuocMuonTheoThoiGian(@Param("date") String date);

    @Query("SELECT DISTINCT YEAR(x.NgayXL) FROM Xuly x WHERE TrangThaiXL = 1")
    List<String> findAllYearsXuLy();

   
    @Query("SELECT MONTH(x.NgayXL), COUNT(x) FROM Xuly x WHERE YEAR(x.NgayXL) = :year AND TrangThaiXL = 1  GROUP BY MONTH(x.NgayXL)")
    List<Object[]> findRowCountByMonth(@Param("year") String year);

    
    
    @Query("SELECT COALESCE(SUM(SoTien), 0) FROM Xuly WHERE SoTien IS NOT NULL AND TrangThaiXL = 1")
    int sumTienBoiThuong();
}


