package com.webdemo.demospringboot.service;

import com.webdemo.demospringboot.model.ThietBi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThietBiRepository extends JpaRepository<ThietBi, Integer> {


}
