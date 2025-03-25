package com.efa73.charleecollector.interfaces.dto.request;

import com.efa73.charleecollector.domain.entity.CycleInfo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record EventInfoRequest(
        String mdn,
        String tid,
        String mid,
        String did,
        String pv,
        String onTime,  // ccyyMMddHHmmss
        String offTime, // ccyyMMddHHmmss
        String gcd,
        String lat,
        String lon,
        String ang,
        String spd,
        String sum
) {

    public static CycleInfo createEntity(EventInfoRequest eventInfoRequest) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ccyyMMddHHmmss");

        if (eventInfoRequest.onTime != null) {
            LocalDateTime onTime = LocalDateTime.parse(eventInfoRequest.onTime, formatter);
            return CycleInfo.createEventEntity(eventInfoRequest.mdn, eventInfoRequest.tid, eventInfoRequest.mid,
                    eventInfoRequest.did, eventInfoRequest.pv, onTime, null, EventType.ON.getType());

        } else {
            LocalDateTime offTime = LocalDateTime.parse(eventInfoRequest.offTime, formatter);
            return CycleInfo.createEventEntity(eventInfoRequest.mdn, eventInfoRequest.tid, eventInfoRequest.mid,
                    eventInfoRequest.did, eventInfoRequest.pv, null, offTime, EventType.ON.getType());
        }
    }
}
