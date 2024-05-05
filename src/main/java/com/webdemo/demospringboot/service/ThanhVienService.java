package com.webdemo.demospringboot.service;



import java.util.List;
import com.webdemo.demospringboot.model.Thanhvien;
import com.webdemo.demospringboot.repository.ThanhVieniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ThanhVienService {
    @Autowired
    public ThanhVieniRepository repo;
    public List<Thanhvien> GetAll(){
        return repo.findAll();
    }
    public void save(Thanhvien tv){
        repo.save(tv);
    }
    public Thanhvien search(int matv){
        return repo.findById(matv).get();
    }

}
