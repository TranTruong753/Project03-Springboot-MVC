package com.webdemo.demospringboot.repository;
import java.util.List;
import com.webdemo.demospringboot.model.ThietBi;
import com.webdemo.demospringboot.model.Xuly;
import com.webdemo.demospringboot.model.Thanhvien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ChartRepository extends JpaRepository<Xuly, Integer> {



//     @Query("SELECT DISTINCT YEAR(x.NgayXL) FROM Xuly x WHERE TrangThaiXL = 1")
//     List<String> findAllYearsXuLy();
//
//    
//     @Query("SELECT MONTH(x.NgayXL), COUNT(x) FROM Xuly x WHERE YEAR(x.NgayXL) = :year GROUP BY MONTH(x.NgayXL)")
//     List<Object[]> findRowCountByMonth(@Param("year") String year);


   
    

}
