package com.example.wms.dto.bom;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true) // 빌더를 생성할 때 기존 객체의 값을 유지하면서 일부 필드 값을 변경할 수 있도록 함
public class BomDTO {

    private Long bNo; // bom id pk

    @NotEmpty
    private Long pNo; // 자재 id

    @NotEmpty
    private int bQty; // 필요수량

    @NotEmpty
    private Long gNo; // 상품 id

    @Size(max = 250)
    private String bNote; // 비고(특이사항 기록) : null 허용


}
