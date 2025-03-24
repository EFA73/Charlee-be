package com.efa73.charleecollector.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(
        name = "cycle_info",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_cycleinfo_mdn_otime", columnNames = {"mdn","o_time"}),
                @UniqueConstraint(name = "uq_cycleinfo_mdn_ontime", columnNames = {"mdn","on_time"}),
                @UniqueConstraint(name = "uq_cycleinfo_mdn_offtime", columnNames = {"mdn","off_time"})
        }
)
@Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class CycleInfo {

    public static final int DATA_LIST_LENGTH = 60;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String mdn;

    @Column(nullable = false, length = 100)
    private String tid;

    @Column(nullable = false, length = 100)
    private String mid;

    @Column(nullable = false, length = 100)
    private String did;

    @Column(nullable = false, length = 10)
    private String pv;

    @Column(name = "o_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime oTime;

    @Column(name = "on_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime onTime;

    @Column(name = "off_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime offTime;

    @Column(nullable = false, length = 10)
    private String eventType;
}
