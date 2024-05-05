package com.webdemo.demospringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.webdemo.demospringboot.model.Thanhvien;



@Repository
public interface RegisterRepository extends JpaRepository<Thanhvien, Integer>{
    

}