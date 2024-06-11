package com.example.wms.dto.goods;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
public class GoodsDTO {

    private Long gNo; // 상품번호

    private Long bNo; // BOM번호

    @NotEmpty
    @Size(min = 10, max = 20)
    private String gCode; // 상품코드

    @NotEmpty
    @Size(max = 50)
    private String gName;   // 상품명

    private int gQty; // 재고수량

    private int gInput; // 입고된 수량 (입력한 수량)

    private int gOutput; // 출고된 수량 (뺀수량)

    @NotEmpty
    @Size(max = 1000)
    private String gSpec; // 상품 규격

    private LocalDateTime regDate;

    private LocalDateTime modDate;

}

