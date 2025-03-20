package com.efa73.charleecollector.interfaces.dto.request;

import com.efa73.charleecollector.domain.entity.CycleDataEntity;
import java.util.List;

public record CycleDataDto(
        String sec,
        String gcd,
        String lat,
        String lon,
        String ang,
        String spd,
        String sum,
        String bat
) {

    public static CycleDataEntity toEntity(CycleDataDto cycleDataDto) {
        return CycleDataEntity.builder()
                .sec(cycleDataDto.sec)
                .gcd(cycleDataDto.gcd)
                .lat(cycleDataDto.lat)
                .lon(cycleDataDto.lon)
                .ang(cycleDataDto.ang)
                .spd(cycleDataDto.spd)
                .sum(cycleDataDto.sum)
                .bat(cycleDataDto.bat)
                .build();
    }

    public static List<CycleDataEntity> toEntityList(List<CycleDataDto> cycleDataList) {

        return cycleDataList.stream().map(entity -> {
            return CycleDataDto.toEntity(entity);
        }).toList();
    }
}
