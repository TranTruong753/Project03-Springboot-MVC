package com.webdemo.demospringboot.service;

import com.webdemo.demospringboot.model.ThietBi;
import com.webdemo.demospringboot.model.Thanhvien;
import com.webdemo.demospringboot.repository.ChartRepository;
import com.webdemo.demospringboot.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChartServiceImpl implements  ChartService{

    // @Autowired
    // public ChartRepository chartRepository;

    // @Override
    // public List<String> findAllYearsXuLy() {
    //     return chartRepository.findAllYearsXuLy();
    // }

    // @Override
    // public List<Object[]> findRowCountByMonth(String year) {
    //     return chartRepository.findRowCountByMonth(year);
    // }
}
