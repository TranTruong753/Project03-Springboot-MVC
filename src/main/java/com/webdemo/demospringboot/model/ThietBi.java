package com.webdemo.demospringboot.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "thietbi")
public class ThietBi {
    @Id
    @Column(name = "MaTB")
    private int maTB;

    @Column(name = "TenTB", nullable = true)
    private String tenTB;

    @Column(name = "MoTaTB", columnDefinition = "text")
    private String moTaTB;

    // constructors, getters, and setters

    public ThietBi() {
    }

    public ThietBi(int maTB, String tenTB, String moTaTB) {
        this.maTB = maTB;
        this.tenTB = tenTB;
        this.moTaTB = moTaTB;
    }

    public int getMaTB() {
        return maTB;
    }

    public void setMaTB(int maTB) {
        this.maTB = maTB;
    }

    public String getTenTB() {
        return tenTB;
    }

    public void setTenTB(String tenTB) {
        this.tenTB = tenTB;
    }

    public String getMoTaTB() {
        return moTaTB;
    }

    public void setMoTaTB(String moTaTB) {
        this.moTaTB = moTaTB;
    }
}