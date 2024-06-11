package com.example.wms.entity.part;

import com.example.wms.repository.part.PartHistoryRepository;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "wms_part")
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Part extends BaseEntity{

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

}