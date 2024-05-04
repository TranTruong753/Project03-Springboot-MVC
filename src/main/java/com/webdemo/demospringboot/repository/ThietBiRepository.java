package com.webdemo.demospringboot.repository;

import com.webdemo.demospringboot.model.ThietBi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThietBiRepository extends JpaRepository<ThietBi, Integer> {

}
