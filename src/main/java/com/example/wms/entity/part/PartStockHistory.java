package com.example.wms.entity.part;

import com.example.wms.entity.part.Part;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "wms_part_stock_history")
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartStockHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 기록 번호

    @ManyToOne // fk(바꿔야함)
    @JoinColumn(name = "part_id", nullable = false)
    private Part part; // 관련된 자재

    @Column(nullable = false)
    private int qty; // 수량

    @Column(nullable = false)
    private LocalDateTime dateTime; // 이벤트 발생 일시

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StockType type; // 입고 또는 출고 타입 (IN, OUT)

    public enum StockType {
        IN, OUT
    }
}