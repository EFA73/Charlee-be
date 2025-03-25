package com.efa73.charleeemulator.core.dto.request;

import java.util.List;

/**
 * oTime: 발생시간 "ccyyMMddHHmm"
 * cCnt: 주기정보 개수
 * cList: 주기정보 리스트
 */
public record VehicleCycleInfoRequest(VehicleInfoRequest vehicleInfo,
                                      String oTime,
                                      String cCnt,
                                      List<CycleDataRequest> cList) {
}
