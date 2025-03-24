package com.efa73.charleecollector.interfaces.dto.request;

import com.efa73.charleecollector.domain.entity.CycleData;
import com.efa73.charleecollector.domain.entity.CycleInfo;
import java.util.List;

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

    public static CycleData toEntity(CycleDataRequest cycleDataRequest, CycleInfo cycleInfo) {
        return CycleData.builder()
                .sec(cycleDataRequest.sec)
                .gcd(cycleDataRequest.gcd)
                .lat(cycleDataRequest.lat)
                .lon(cycleDataRequest.lon)
                .ang(cycleDataRequest.ang)
                .spd(cycleDataRequest.spd)
                .sum(cycleDataRequest.sum)
                .bat(cycleDataRequest.bat)
                .cycleInfo(cycleInfo)
                .build();
    }

    public static List<CycleData> toEntityList(List<CycleDataRequest> cycleDataList, CycleInfo cycleInfo) {

        return cycleDataList.stream().map(cycleDataRequest -> {
            return CycleDataRequest.toEntity(cycleDataRequest, cycleInfo);
        }).toList();
    }
}
