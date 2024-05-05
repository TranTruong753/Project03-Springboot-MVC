package com.webdemo.demospringboot.repository;

import com.webdemo.demospringboot.model.ThietBi;
import com.webdemo.demospringboot.model.Thanhvien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Thanhvien, Integer> {
    @Query("SELECT t FROM Thanhvien t WHERE t.id = :maTV AND t.password = :matKhau")
    Thanhvien loginThanhVien(Integer maTV, String matKhau);

    @Query("SELECT t.hoTen FROM Thanhvien t WHERE t.id = :maTV")
    String get_Hoten(Integer maTV);

    @Query("SELECT t.email FROM Thanhvien t WHERE t.id = :maTV")
    String get_Email(Integer maTV);

    

}
