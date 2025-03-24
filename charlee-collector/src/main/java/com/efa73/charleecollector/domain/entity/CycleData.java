package com. efa73.charleecollector. domain. entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cycle_data")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CycleData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 5)
    private String sec;

    @Column(nullable = false)
    private String gcd;

    @Column(nullable = false)
    private String lat;

    @Column(nullable = false)
    private String lon;

    @Column(nullable = false)
    private String ang;

    @Column(nullable = false)
    private String spd;

    @Column(nullable = false)
    private String sum;

    @Column(nullable = false)
    private String bat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cycle_info_id")
    private CycleInfo cycleInfo;


}