package com.efa73.charleecollector.interfaces.dto.request;

import com.efa73.charleecollector.domain.entity.CycleInfo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record EventInfoRequest(
        String mdn,
        String tid,
        String mid,
        String pv,
        String did,
        String onTime,  // ccyyMMddHHmmss
        String offTime, // ccyyMMddHHmmss
        String gcd,
        String lat,
        String lon,
        String ang,
        String spd,
        String sum
) {

    public static CycleInfo toEntity(EventInfoRequest eventInfoRequest) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ccyyMMddHHmmss");

        if(eventInfoRequest.onTime != null) {

            String time = eventInfoRequest.onTime();
            return CycleInfo.builder()
                    .mdn(eventInfoRequest.mdn)
                    .tid(eventInfoRequest.tid)
                    .mid(eventInfoRequest.mid)
                    .pv(eventInfoRequest.pv)
                    .did(eventInfoRequest.did)
                    .onTime(LocalDateTime.parse(time, formatter))
                    .eventType(EventType.ON.getType())
                    .build();
        } else {

            String time = eventInfoRequest.offTime();
            return CycleInfo.builder()
                    .mdn(eventInfoRequest.mdn)
                    .tid(eventInfoRequest.tid)
                    .mid(eventInfoRequest.mid)
                    .pv(eventInfoRequest.pv)
                    .did(eventInfoRequest.did)
                    .offTime(LocalDateTime.parse(time, formatter))
                    .eventType(EventType.OFF.getType())
                    .build();
        }
    }
}
