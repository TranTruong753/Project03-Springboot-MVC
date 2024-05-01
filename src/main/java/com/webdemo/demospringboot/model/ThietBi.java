package com.webdemo.demospringboot.model;

import jakarta.persistence.*;

@Entity
@Table(name = "thietbi")
public class ThietBi {
    @Id
    private int MaTB; // Use Long instead of int for primary key

    private String TenTB;

    @Column(columnDefinition = "TEXT")
    private String MoTaTB;
    public int getMaTB() {
        return MaTB;
    }

    public void setMaTB(int maTB) {
        MaTB = maTB;
    }

    public String getTenTB() {
        return TenTB;
    }

    public void setTenTB(String tenTB) {
        TenTB = tenTB;
    }

    public String getMoTaTB() {
        return MoTaTB;
    }

    public void setMoTaTB(String moTaTB) {
        MoTaTB = moTaTB;
    }


}


