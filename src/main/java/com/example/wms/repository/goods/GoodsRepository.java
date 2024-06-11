package com.example.wms.repository.goods;

import com.example.wms.entity.goods.Goods;
import com.example.wms.repository.search.GoodsSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GoodsRepository extends JpaRepository<Goods, Long>, GoodsSearch {

    @Query("SELECT g FROM Goods g WHERE g.gCode IN :types AND g.gName LIKE %:Lavel%")
    Page<Goods> findByTypeAndLavel(@Param("types") List<String> types, @Param("Lavel") String Lavel, Pageable pageable);

}
