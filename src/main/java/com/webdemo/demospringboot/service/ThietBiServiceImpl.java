package com.webdemo.demospringboot.service;

import com.webdemo.demospringboot.model.ThietBi;

import com.webdemo.demospringboot.repository.ThietBiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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
    
    //------------------------------------
    public List<ThietBi> listAll(){
        return thietBiRepository.findAll();
    }
    public void save(ThietBi tb){
        thietBiRepository.save(tb);
    } 
    public void saveAll(List<ThietBi> thietbis) {
        thietBiRepository.saveAll(thietbis);
    }
    public ThietBi get(int matb){
        return thietBiRepository.findById(matb).get();
    }
    public void delete(int matb){
        thietBiRepository.deleteById(matb);
    }
    public Page<ThietBi> getAll(int pageNo) {
        Pageable pageable= PageRequest.of(pageNo-1, 2);
        return thietBiRepository.findAll(pageable);
    }
}
