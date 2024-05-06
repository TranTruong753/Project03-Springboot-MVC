/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.webdemo.demospringboot.repository;

import com.webdemo.demospringboot.model.Thanhvien;
import com.webdemo.demospringboot.model.Xuly;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Administrator
 */
public interface XulyRepository extends JpaRepository<Xuly, Integer>{
    @Query("SELECT t FROM Xuly t WHERE t.MaTV = :maTV")
    List<Xuly> layDanhSachViPhamTheoID(@Param("maTV") int maTV);
}
