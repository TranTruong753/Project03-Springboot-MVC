package com.webdemo.demospringboot.service;

import com.webdemo.demospringboot.model.ThietBi;

import java.util.List;

public interface ThietBiService {

    List<ThietBi> layDanhSachThietBi();

    List<ThietBi> findAllThietBi();
    ThietBi findByMaTB(int maTB);
}
