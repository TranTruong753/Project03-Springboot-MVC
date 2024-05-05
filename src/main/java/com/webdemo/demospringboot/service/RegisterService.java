package com.webdemo.demospringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webdemo.demospringboot.model.Thanhvien;
import com.webdemo.demospringboot.repository.RegisterRepository;


@Service
public class RegisterService {
    @Autowired
    private RegisterRepository registerRepository;

    public void save(Thanhvien thanhvien) {
       
        registerRepository.save(thanhvien);
    }
    public boolean existsById(Integer id) {
        return registerRepository.existsById(id);
    }
}
