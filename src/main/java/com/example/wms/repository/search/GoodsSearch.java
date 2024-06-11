package com.example.wms.repository.search;

import com.example.wms.entity.goods.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GoodsSearch {

    Page<Goods> search1(Pageable pageable);

    Page<Goods> searchAll(String[] Code, String Lavel, Pageable pageable);
}
