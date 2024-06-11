
package com.example.wms.repository.goods;

import com.example.wms.entity.goods.Goods;
import com.example.wms.entity.part.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsRepository extends JpaRepository<Goods, Long> {

}
