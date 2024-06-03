package com.example.wms.entity.bom;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "wms_bom")
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bom {
    // Bom : 자재명세서

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bNo; // bom id pk

    @Column(nullable = false)
    private Long pNo; // 자재 id

    @Column(nullable = false)
    private int bQty; // 필요수량

    @Column(nullable = false)
    private Long gNo; // 상품 id

    @Column(length = 500)
    private String bNote; // 비고(특이사항 기록) : null 허용

}
