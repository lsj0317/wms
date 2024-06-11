package com.example.wms.entity.part;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pHistory")
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long phNo; // 자재기록 번호

    @Column(nullable = false)
    private Long pNo; // 자재 ID

    @Column(nullable = false)
    private int phQty; // 수량

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StockType pType; // 입고 또는 출고 타입 (IN, OUT)

    public enum StockType {
        IN, OUT
    }
}