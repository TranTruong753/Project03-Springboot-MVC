package com.webdemo.demospringboot.service;

import com.webdemo.demospringboot.model.ThietBi;

import java.util.List;
import org.springframework.data.domain.Page;

public interface ThietBiService {

    List<ThietBi> layDanhSachThietBi();

    List<ThietBi> findAllThietBi();
    ThietBi findByMaTB(int maTB);
    Page<ThietBi> getAll(int pageNo);
    public void save(ThietBi tb);
    public void delete(int matb);
    public void saveAll(List<ThietBi> thietbis);
    
}
