package com.efa73.charleecollector.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "cycle_info")
public class CycleInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mdn; // 차량 번호

    private String tid; // 터미널 아이디

    private String mid; // 제조사 아이디

    private String did; // 디바이스 아이디

    private String pv; // 패킷 버전

    private LocalDateTime oTime; // ccyyMMddHHmm

    private LocalDateTime onTime; // ccyyMMddHHmmss

    private LocalDateTime offTime; // ccyyMMddHHmmss

    private String eventType;
}
