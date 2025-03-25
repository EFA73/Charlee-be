package com.efa73.charleecollector.interfaces.dto.request;

import com.efa73.charleecollector.domain.entity.CycleInfo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public record CycleInfoRequest(
        String mdn,
        String tid,
        String mid,
        String did,
        String pv,
        String oTime,
        String cCnt,
        List<CycleDataRequest> cList
) {

    public static CycleInfo createEntity(CycleInfoRequest cycleInfoRequest) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ccyyMMddHHmm");
        LocalDateTime oTime = LocalDateTime.parse(cycleInfoRequest.oTime, formatter);

        return CycleInfo.createCycleEntity(cycleInfoRequest.mdn, cycleInfoRequest.tid, cycleInfoRequest.mid,
                cycleInfoRequest.did,
                cycleInfoRequest.pv, oTime, EventType.CYCLE.getType());
    }
}
