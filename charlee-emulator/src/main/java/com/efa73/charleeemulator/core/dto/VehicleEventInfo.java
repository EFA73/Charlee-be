package com.efa73.charleeemulator.core.dto;

/**
 * onTime: 차량 시동 ON 시간 "ccyyMMddHHmmss"
 * offTime: 차량 시동 OFF 시간 "ccyyMMddHHmmss"
 */

public class VehicleEventInfo {
    private final VehicleInfo vehicleInfo;
    private final VehicleCycleInfo vehicleCycleInfo;
    private final String onTime;
    private final String offTime;

    public VehicleEventInfo(VehicleInfo vehicleInfo, VehicleCycleInfo vehicleCycleInfo, String onTime, String offTime) {
        this.vehicleInfo = vehicleInfo;
        this.vehicleCycleInfo = vehicleCycleInfo;
        this.onTime = onTime;
        this.offTime = offTime;
    }

    // 시동 ON 이벤트 생성 (offTime은 빈 문자열)
    public static VehicleEventInfo createCarOnEvent(VehicleInfo vehicleInfo, VehicleCycleInfo vehicleCycleInfo, String onTime) {
        return new VehicleEventInfo(vehicleInfo, vehicleCycleInfo, onTime, "");
    }

    // 시동 OFF 이벤트 생성 (onTime은 빈 문자열)
    public static VehicleEventInfo createCarOffEvent(VehicleInfo vehicleInfo, VehicleCycleInfo vehicleCycleInfo, String offTime) {
        return new VehicleEventInfo(vehicleInfo, vehicleCycleInfo, "", offTime);
    }
}
