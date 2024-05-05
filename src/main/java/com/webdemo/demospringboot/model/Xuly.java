/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webdemo.demospringboot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "xuly")
public class Xuly {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int MaXL;
   
    @Column
    private int MaTV;
    
    @Column
    private String HinhThucXL;

    @Column
    private Integer SoTien;
    
    @Column
    private Timestamp NgayXL;
    
    @Column
    private int TrangThaiXL;

    public Xuly() {
    }

    public Xuly(int MaXL, int MaTV, String HinhThucXL, Integer SoTien, Timestamp NgayXL, int TrangThaiXL) {
        this.MaXL = MaXL;
        this.MaTV = MaTV;
        this.HinhThucXL = HinhThucXL;
        this.SoTien = SoTien;
        this.NgayXL = NgayXL;
        this.TrangThaiXL = TrangThaiXL;
    }

    public int getMaXL() {
        return MaXL;
    }

    public void setMaXL(int MaXL) {
        this.MaXL = MaXL;
    }

    public int getMaTV() {
        return MaTV;
    }

    public void setMaTV(int MaTV) {
        this.MaTV = MaTV;
    }

    public String getHinhThucXL() {
        return HinhThucXL;
    }

    public void setHinhThucXL(String HinhThucXL) {
        this.HinhThucXL = HinhThucXL;
    }

    public Integer getSoTien() {
        return SoTien;
    }

    public void setSoTien(Integer SoTien) {
        this.SoTien = SoTien;
    }

    public Timestamp getNgayXL() {
        return NgayXL;
    }

    public void setNgayXL(Timestamp NgayXL) {
        this.NgayXL = NgayXL;
    }

    public int getTrangThaiXL() {
        return TrangThaiXL;
    }

    public void setTrangThaiXL(int TrangThaiXL) {
        this.TrangThaiXL = TrangThaiXL;
    }
    
}
