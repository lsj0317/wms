package com.example.wms.entity.work;

import com.example.wms.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "wms_work")
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Work extends BaseEntity {
    // Work : 생산
    // BaseEntity 사용예정

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wNo; // 생산 id pk

    @Column(nullable = false)
    private Long gNo; // 상품 id

    @Column(nullable = false)
    private int wQty; // 생산수량

    public void changeWork(Long gNo, int wQty){
        this.gNo = gNo;
        this.wQty = wQty;
    }

}
