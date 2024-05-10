/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.webdemo.demospringboot.service;

import com.webdemo.demospringboot.model.ThongTinSD;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

/**
 *
 * @author ACER
 */
@Service
public interface DatCho_UserService {

    boolean checkIfThietBiAlreadyBorrowed(int maTB);

    boolean checkIfThietBiAlreadyReserved(int maTB);

    boolean checkIfThietBiAlreadyReservedOnDate(int maTB, LocalDateTime selectedDate);

    int countThongTinSD();

    void saveThongTinSD(ThongTinSD thongTinSD);

}
