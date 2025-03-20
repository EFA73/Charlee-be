package com.efa73.charleecollector.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "cycle_data")
public class CycleDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sec;

    private String gcd; // GPS 상태

    private String lat; // GPS 위도

    private String lon; // GPS 경도

    private String ang; // 방향

    private String spd; // 속도

    private String sum; // 누적 주행 거리

    private String bat; // 배터리 전압

    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    private CycleInfoEntity cycleInfo;
}