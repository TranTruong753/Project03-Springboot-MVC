package com.webdemo.demospringboot.service;

import com.webdemo.demospringboot.model.ThietBi;
import com.webdemo.demospringboot.model.Thanhvien;
import com.webdemo.demospringboot.repository.ChartRepository;
import com.webdemo.demospringboot.repository.LoginRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChartServiceImpl implements  ChartService{

    @Autowired
    public ChartRepository chartRepository;

    @Override
    public List<String> findAllYearsXuLy() {
//        return chartRepository.findAllYearsXuLy();
       List<String> test = new ArrayList<>();
        return test;
    }
}
