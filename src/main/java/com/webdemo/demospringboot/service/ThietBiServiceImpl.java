package com.webdemo.demospringboot.service;

import com.webdemo.demospringboot.model.ThietBi;

import com.webdemo.demospringboot.repository.ThietBiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThietBiServiceImpl implements ThietBiService {

    @Autowired
    public ThietBiRepository thietBiRepository;

    @Override
    public List<ThietBi> layDanhSachThietBi() {
        return thietBiRepository.findAll();
    }

    @Override
    public List<ThietBi> findAllThietBi() {
        return thietBiRepository.findAllThietBi();
    }

    @Override
    public ThietBi findByMaTB(int maTB) {
        return thietBiRepository.findByMaTB(maTB);
    }
}
