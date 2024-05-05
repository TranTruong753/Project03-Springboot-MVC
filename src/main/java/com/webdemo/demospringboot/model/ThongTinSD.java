package com.webdemo.demospringboot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "thongtinsd")
public class ThongTinSD {

    @Id
    private int MaTT;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaTV", referencedColumnName = "MaTV")

    private Thanhvien thanhVien;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaTB", referencedColumnName = "MaTB")
    private ThietBi thietBi;

    @Column(name = "TG_Vao")
    private LocalDateTime thoiGianVao;

    @Column(name = "TGDatCho")
    private LocalDateTime thoiGianDatCho;

    @Column(name = "TG_Muon")

    private LocalDateTime thoiGianMuon; // Có thể sử dụng kiểu dữ liệu phù hợp cho thời gian mượn


    @Column(name = "TG_Tra")
    private LocalDateTime thoiGianTra;

    // Constructors, getters, and setters
    public ThongTinSD() {
    }

    public ThongTinSD(Thanhvien thanhVien, ThietBi thietBi, LocalDateTime thoiGianVao, LocalDateTime thoiGianMuon, LocalDateTime thoiGianTra, LocalDateTime thoiGianDatCho) {
        this.thanhVien = thanhVien;
        this.thietBi = thietBi;
        this.thoiGianVao = thoiGianVao;
        this.thoiGianMuon = thoiGianMuon;
        this.thoiGianTra = thoiGianTra;
        this.thoiGianDatCho = thoiGianDatCho;
    }

    public int getMaTT() {
        return MaTT;
    }

    public void setMaTT(int MaTT) {
        this.MaTT = MaTT;
    }

    public Thanhvien getThanhVien() {
        return thanhVien;
    }

    public void setThanhVien(Thanhvien thanhVien) {
        this.thanhVien = thanhVien;
    }

    public ThietBi getThietBi() {
        return thietBi;
    }

    public void setThietBi(ThietBi thietBi) {
        this.thietBi = thietBi;
    }

    public LocalDateTime getThoiGianVao() {
        return thoiGianVao;
    }

    public void setThoiGianVao(LocalDateTime thoiGianVao) {
        this.thoiGianVao = thoiGianVao;
    }

    public LocalDateTime getThoiGianMuon() {
        return thoiGianMuon;
    }

    public void setThoiGianMuon(LocalDateTime thoiGianMuon) {
        this.thoiGianMuon = thoiGianMuon;
    }

    public LocalDateTime getThoiGianTra() {
        return thoiGianTra;
    }

    public void setThoiGianTra(LocalDateTime thoiGianTra) {
        this.thoiGianTra = thoiGianTra;
    }

    public LocalDateTime getThoiGianDatCho() {
        return thoiGianDatCho;
    }

    public void setThoiGianDatCho(LocalDateTime thoiGianDatCho) {
        this.thoiGianDatCho = thoiGianDatCho;
    }
}
