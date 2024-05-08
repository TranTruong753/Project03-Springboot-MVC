package com.webdemo.demospringboot.repository;
import java.util.List;
import com.webdemo.demospringboot.model.ThietBi;
import com.webdemo.demospringboot.model.Xuly;
import com.webdemo.demospringboot.model.Thanhvien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChartRepository extends JpaRepository<Xuly, Integer> {
    @Query("SELECT DISTINCT YEAR(x.NgayXL) FROM Xuly x WHERE TrangThaiXL = 1")
    List<String> findAllYearsXuLy();
   
    

}
