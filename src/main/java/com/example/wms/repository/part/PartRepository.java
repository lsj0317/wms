package com.example.wms.repository.part;

import com.example.wms.entity.part.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartRepository extends JpaRepository<Part, Long> {
}
