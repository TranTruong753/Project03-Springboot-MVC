package com.webdemo.demospringboot.repository;


import com.webdemo.demospringboot.model.Thanhvien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThanhVieniRepository extends JpaRepository<Thanhvien, Integer> {

}
