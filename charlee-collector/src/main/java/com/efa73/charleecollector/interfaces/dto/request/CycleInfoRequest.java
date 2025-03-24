package com.efa73.charleecollector.interfaces.dto.request;

import com.efa73.charleecollector.domain.entity.CycleInfo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public record CycleInfoRequest(
        String mdn,
        String tid,
        String mid,
        String pv,
        String did,
        String oTime,
        String cCnt,
        List<CycleDataRequest> cList
) {

    public static CycleInfo toEntity(CycleInfoRequest cycleInfoRequest) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ccyyMMddHHmm");

        return CycleInfo.builder()
                .mdn(cycleInfoRequest.mdn)
                .tid(cycleInfoRequest.tid)
                .mid(cycleInfoRequest.mid)
                .pv(cycleInfoRequest.pv)
                .did(cycleInfoRequest.did)
                .oTime(LocalDateTime.parse(cycleInfoRequest.oTime, formatter))
                .eventType(EventType.CYCLE.getType())
                .build();
    }
}