package com.example.wms.repository.search;

import com.example.wms.entity.goods.Goods;
import com.example.wms.entity.goods.QGoods;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class GoodsSearchImpl extends QuerydslRepositorySupport implements GoodsSearch {

    public GoodsSearchImpl() {

        super(Goods.class);
    }

    @Override
    public Page<Goods> search1(Pageable pageable) {

        QGoods goods = QGoods.goods;

        JPAQuery<Goods> query = (JPAQuery<Goods>) from(goods);


        return null;
    }

    @Override
    public Page<Goods> searchAll(String[] Code, String Gname, Pageable pageable) {
        return null;
    }
}
