package com.efa73.charleecollector.interfaces.dto.request;

import com.efa73.charleecollector.domain.entity.CycleInfoEntity;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public record CycleInfoDto(
        String mdn,
        String tid,
        String mid,
        String pv,
        String did,
        String oTime,
        String cCnt,
        List<CycleDataDto> cList
) {

    public static CycleInfoEntity toEntity(CycleInfoDto cycleInfoDto) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ccyyMMddHHmm");

        return CycleInfoEntity.builder()
                .mdn(cycleInfoDto.mdn)
                .tid(cycleInfoDto.tid)
                .mid(cycleInfoDto.mid)
                .pv(cycleInfoDto.pv)
                .did(cycleInfoDto.did)
                .oTime(LocalDateTime.parse(cycleInfoDto.oTime, formatter))
                .eventType(EventType.CYCLE.getType())
                .build();
    }
}