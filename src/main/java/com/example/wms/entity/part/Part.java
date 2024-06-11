package com.example.wms.entity.part;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "wms_part")
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pNo; // 자재번호 pk

    @Column(nullable = false)
    private String pCode; // 자재코드

    @Column(nullable = false)
    private String pName; // 자재이름

    private int pQty; // 자재수량

    @Column(nullable = false)
    private String pSpec; // 자재규격

    @Column(nullable = false)
    private String pType; // 자재타입

    // 자재 입고 메서드
    public void addStock(int qty) {
        this.pQty += qty;
    }

    // 자재 출고 메서드
    public void removeStock(int qty) throws IllegalArgumentException {
        if (this.pQty < qty) {
            throw new IllegalArgumentException("재고 수량이 부족합니다.");
        }
        this.pQty -= qty;
    }
}