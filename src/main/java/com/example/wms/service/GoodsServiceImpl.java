package com.example.wms.service;

import com.example.wms.dto.goods.GoodsDTO;
import com.example.wms.dto.pagedto.PageRequestDTO;
import com.example.wms.dto.pagedto.PageResponseDTO;
import com.example.wms.entity.goods.Goods;
import com.example.wms.repository.goods.GoodsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.apache.bcel.classfile.Code;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class GoodsServiceImpl implements GoodsService{

    private final ModelMapper modelMapper;  // DTO와 Entity 변환을 햐주는 객체

    private final GoodsRepository goodsRepository;  // Goods CRUD용

    @Override
    public Long register(GoodsDTO goodsDTO) {

        Goods goods = modelMapper.map(goodsDTO, Goods.class);
        // ModelMapper 에서는 map(source, destination) 메서드가 호출되면 source와 destination
        // 타입을 분석하여 매칭 전략 및 기타 설정값에 따라 일치하는 속성을 결정하여 매칭 항목에 대해 데이터를 매핑한다.

        Long gNo = goodsRepository.save(goods).getGNo();
        // save() JPA에서 없으면 Insert, 있으면 Update 진행

        return gNo;
    }

    @Override
    public GoodsDTO readOne(Long gno) {

        Optional<Goods> result = goodsRepository.findById(gno);

        Goods goods = result.orElseThrow();

        GoodsDTO  goodsDTO = modelMapper.map(goods, GoodsDTO.class);

        return goodsDTO;
    }

    @Override
    public void modify(GoodsDTO goodsDTO) {

        Optional<Goods> result = goodsRepository.findById(goodsDTO.getGNo());

        Goods goods = result.orElseThrow();

        goods.change(goodsDTO.getGCode(), goodsDTO.getGName(), goodsDTO.getGSpec());

        goodsRepository.save(goods);

    }

    @Override
    public void remove(Long gno) {

        goodsRepository.deleteById(gno);

    }

    @Override
    public PageResponseDTO<GoodsDTO> list(PageRequestDTO pageRequestDto) {

        String[] types = pageRequestDto.getTypes();
        String Lavel = pageRequestDto.getLavel();
        Pageable pageable = pageRequestDto.getPageable("gno");

        Page<Goods> result = goodsRepository.searchAll(types, Lavel, pageable);

        List<GoodsDTO> dtoList = result.getContent().stream()
                .map(board -> modelMapper.map(goodsRepository,GoodsDTO.class)).collect(Collectors.toList());


        return PageResponseDTO.<GoodsDTO>withAll()
                .pageRequestDTO(pageRequestDto)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();

    }
}
