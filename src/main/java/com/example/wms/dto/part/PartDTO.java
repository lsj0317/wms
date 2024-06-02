package com.example.wms.dto.part;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class PartDTO {

    private Long pNo; // 번호
    private String pCode; // 자재코드
    private String pName; // 자재이름
    private int pQty; // 재고수량
    private int pInput; // 입고된 수량 (입력한 수량)
    private int pOutput; // 출고된 수량 (뺀수량)
    private String pSpec; // 제품 규격
    private String pType; // 자재 분류

}
