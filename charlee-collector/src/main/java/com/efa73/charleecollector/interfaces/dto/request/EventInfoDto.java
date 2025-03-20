package com.efa73.charleecollector.interfaces.dto.request;

import com.efa73.charleecollector.domain.entity.CycleInfoEntity;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record EventInfoDto(
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

    public static CycleInfoEntity toEntity(EventInfoDto eventInfoDto) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ccyyMMddHHmmss");

        if(eventInfoDto.onTime != null) {

            String time = eventInfoDto.onTime();
            return CycleInfoEntity.builder()
                    .mdn(eventInfoDto.mdn)
                    .tid(eventInfoDto.tid)
                    .mid(eventInfoDto.mid)
                    .pv(eventInfoDto.pv)
                    .did(eventInfoDto.did)
                    .onTime(LocalDateTime.parse(time, formatter))
                    .eventType(EventType.ON.getType())
                    .build();
        } else {

            String time = eventInfoDto.offTime();
            return CycleInfoEntity.builder()
                    .mdn(eventInfoDto.mdn)
                    .tid(eventInfoDto.tid)
                    .mid(eventInfoDto.mid)
                    .pv(eventInfoDto.pv)
                    .did(eventInfoDto.did)
                    .offTime(LocalDateTime.parse(time, formatter))
                    .eventType(EventType.OFF.getType())
                    .build();
        }
    }
}
