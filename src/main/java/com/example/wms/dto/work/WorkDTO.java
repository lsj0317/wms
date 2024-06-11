package com.example.wms.dto.work;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true) // 빌더를 생성할 때 기존 객체의 값을 유지하면서 일부 필드 값을 변경할 수 있도록 함
public class WorkDTO {

    private Long wNo; // 생산 id pk

    @NotEmpty
    private Long gNo; // 상품 id

    @NotEmpty
    private int wQty; // 생산수량

    private LocalDateTime wRegDate; // 생산일자

    private LocalDateTime wModDate; // 기록수정일자

}
