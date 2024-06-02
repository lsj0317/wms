package com.example.wms.repository.part;

import com.example.wms.entity.part.PartStockHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartStockHistoryRepository extends JpaRepository<PartStockHistory, Long> {
}
