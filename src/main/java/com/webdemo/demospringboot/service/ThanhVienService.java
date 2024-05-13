package com.webdemo.demospringboot.service;



import java.util.List;
import com.webdemo.demospringboot.model.Thanhvien;
import com.webdemo.demospringboot.model.ThietBi;
import com.webdemo.demospringboot.repository.ThanhVieniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Page<Thanhvien> getAll(int pageNo) {
        Pageable pageable= PageRequest.of(pageNo-1, 5);
        return repo.findAll(pageable);
    }
    public void delete(int matv){
        repo.deleteById(matv);
    }
    public void saveAll(List<Thanhvien> tvs) {
        repo.saveAll(tvs);
    }
    
  

}
