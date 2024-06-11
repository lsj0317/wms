package com.example.wms.service;

import com.example.wms.dto.goods.GoodsDTO;
import com.example.wms.dto.pagedto.PageRequestDTO;
import com.example.wms.dto.pagedto.PageResponseDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

public interface GoodsService {

    Long register(GoodsDTO goodsDTO);

    GoodsDTO readOne(Long gno);

    void modify(GoodsDTO goodsDTO);

    void remove(Long gno);

    PageResponseDTO<GoodsDTO> list(PageRequestDTO pageRequestDto);

}
