package com.example.wms.dto.part;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class GoodsDTO {

    private Long gNo; // 상품번호

    private Long bNo; // BOM번호

    private String gCode; // 상품코드

    private String gName;   // 상품명

    private int gQty; // 재고수량

    private int gInput; // 입고된 수량 (입력한 수량)

    private int gOutput; // 출고된 수량 (뺀수량)

    private String gSpec; // 상품 규격

}

