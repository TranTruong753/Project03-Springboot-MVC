package com.webdemo.demospringboot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "thanhvien")
public class Thanhvien {
    @Id
    @Column(name = "MaTV", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "HoTen", nullable = false, length = 100)
    private String hoTen;

    @Size(max = 100)
    @Column(name = "Khoa", length = 100)
    private String khoa;

    @Size(max = 100)
    @Column(name = "Nganh", length = 100)
    private String nganh;

    @Size(max = 15)
    @Column(name = "SDT", length = 15)
    private String sdt;

    @Size(max = 25)
    @Column(name = "email", length = 25)
    private String email;

    @Size(max = 10)
    @Column(name = "password", length = 10)
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getKhoa() {
        return khoa;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }

    public String getNganh() {
        return nganh;
    }

    public void setNganh(String nganh) {
        this.nganh = nganh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}