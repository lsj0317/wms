package com.example.wms.repository.bom;

import com.example.wms.entity.bom.Bom;
import com.example.wms.entity.part.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BomRepository extends JpaRepository<Bom, Long> {

    List<Bom> findBygNo(Long gNo);
    // 매개값으로 받은 상품 id에 해당하는 부품(bom)정보를 반환
    // 상품의 부품들을 조회할 때에 사용

    @Query("select b.gNo from Bom b where b.pNo = :pNo")
    List<Long> searchGnoByPno(Long pNo);
    // 매개값으로 받은 부품 id에 해당하는 상품 id를 반환
    // 부품이 어떤상품들에 쓰였는지 조회할 때에 사용

}
