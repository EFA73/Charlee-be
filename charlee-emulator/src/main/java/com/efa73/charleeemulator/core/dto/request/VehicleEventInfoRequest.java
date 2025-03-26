package com.efa73.charleeemulator.core.dto.request;

/**
 * onTime: 차량 시동 ON 시간 "ccyyMMddHHmmss"
 * offTime: 차량 시동 OFF 시간 "ccyyMMddHHmmss"
 */

public record VehicleEventInfoRequest(VehicleInfoRequest vehicleInfo,
                                      CycleDataRequest cycleData,
                                      String onTime,
                                      String offTime) {
    public static VehicleEventInfoRequest createCarOnEvent(VehicleInfoRequest vehicleInfo, CycleDataRequest cycleData, String onTime) {
        return new VehicleEventInfoRequest(vehicleInfo, cycleData, onTime, "");
    }

    public static VehicleEventInfoRequest createCarOffEvent(VehicleInfoRequest vehicleInfo, CycleDataRequest cycleData, String offTime) {
        return new VehicleEventInfoRequest(vehicleInfo, cycleData, "", offTime);
    }
}
