package com.webdemo.demospringboot.service;

import com.webdemo.demospringboot.model.Thanhvien;


import java.util.List;

public interface LoginService {
    Thanhvien loginThanhVien(int maTV, String matKhau);

}