package com.example.wms.entity.goods;

import com.example.wms.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "wms_Goods")
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Goods extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gNo;   // 상품번호 pk

    @Column(nullable = false)
    private Long bNo;   // BOM번호

    @Column(nullable = false)
    private String gCode;   // 상품코드

    @Column(nullable = false)
    private String gName;   // 삼품명

    private int gQty;   // 상품수량

    @Column(nullable = false)
    private String gSpec;   // 상품규격



}
