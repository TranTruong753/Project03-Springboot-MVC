/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webdemo.demospringboot.service;

import com.webdemo.demospringboot.model.ThongTinSD;
import com.webdemo.demospringboot.repository.DatCho_User_Repository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ACER
 */
@Service
public class DatCho_UserServiceImpl implements DatCho_UserService {

    @Autowired
    private DatCho_User_Repository datChoUserRepository;

    @Override
    public boolean checkIfThietBiAlreadyBorrowed(int maTB) {
        int countCurrentBorrowed = datChoUserRepository.count_trangthai_muon_thietbi(maTB);
        return countCurrentBorrowed > 0;
    }

    @Override
    public boolean checkIfThietBiAlreadyReserved(int maTB) {
        int currentReservations = datChoUserRepository.count_trangthai_datmuon_thietbi(maTB);
        return currentReservations > 0;
    }

    @Override
    public boolean checkIfThietBiAlreadyReservedOnDate(int maTB, LocalDateTime selectedDate) {
        int currentReservations = datChoUserRepository.count_datechosen_not_curent(maTB, selectedDate);
        return currentReservations > 0;
    }

    @Override
    public int countThongTinSD() {
        return datChoUserRepository.countThongTinSD();
    }

    @Override
    public void saveThongTinSD(ThongTinSD thongTinSD) {
        datChoUserRepository.save(thongTinSD);
    }
}
