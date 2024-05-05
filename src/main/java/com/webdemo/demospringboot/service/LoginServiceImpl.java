package com.webdemo.demospringboot.service;

import com.webdemo.demospringboot.model.ThietBi;
import com.webdemo.demospringboot.model.Thanhvien;
import com.webdemo.demospringboot.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements  LoginService{

    @Autowired
    public LoginRepository loginRepository;

    @Override
    public Thanhvien loginThanhVien(int maTV, String matKhau) {
        return loginRepository.loginThanhVien(maTV, matKhau);
    }
}
