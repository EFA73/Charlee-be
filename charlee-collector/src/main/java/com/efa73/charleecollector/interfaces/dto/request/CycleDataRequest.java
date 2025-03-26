package com.efa73.charleecollector.interfaces.dto.request;

import com.efa73.charleecollector.domain.entity.CycleData;
import com.efa73.charleecollector.domain.entity.CycleInfo;

public record CycleDataRequest(
        String sec,
        String gcd,
        String lat,
        String lon,
        String ang,
        String spd,
        String sum,
        String bat
) {

    public static CycleData createEntity(CycleDataRequest cycleDataRequest, CycleInfo cycleInfo) {
        return CycleData.createEntity(cycleDataRequest.sec, cycleDataRequest.gcd, cycleDataRequest.lat,
                cycleDataRequest.lon,
                cycleDataRequest.ang, cycleDataRequest.spd, cycleDataRequest.sum, cycleDataRequest.bat, cycleInfo);
    }
}
