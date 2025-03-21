package com.efa73.charleeemulator.core.domain;

import java.util.List;

/**
 * oTime: 발생시간 "ccyyMMddHHmm"
 * cCnt: 주기정보 개수
 * cList: 주기정보 리스트
 */
public class VehicleCycleInfo {
    private final VehicleInfo vehicleInfo;
    private final String oTime;
    private final String cCnt;
    private final List<CycleData> cList;

    public VehicleCycleInfo(VehicleInfo vehicleInfo, String oTime, String cCnt, List<CycleData> cList) {
        this.vehicleInfo = vehicleInfo;
        this.oTime = oTime;
        this.cCnt = cCnt;
        this.cList = cList;
    }
}
