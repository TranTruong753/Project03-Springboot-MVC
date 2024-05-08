package com.webdemo.demospringboot.repository;

import com.webdemo.demospringboot.model.ThietBi;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ThietBiRepository extends JpaRepository<ThietBi, Integer> {

    @Query("SELECT m FROM ThietBi m ")
    List<ThietBi> findAllThietBi();

    ThietBi findByMaTB(int maTB);
//    Page<ThietBi> getAll(int pageNo);
}
