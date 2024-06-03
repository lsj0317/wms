package com.example.wms.repository.bom;

import com.example.wms.entity.bom.Bom;
import com.example.wms.entity.part.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BomRepository extends JpaRepository<Bom, Long> {
}
