package com.example.wms.repository.work;

import com.example.wms.entity.work.Work;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkRepository extends JpaRepository<Work, Long> {

    List<Work> findBygNo(Long gNo);
    // 한 상품의 생산내역을 조회하는 메서드

}
