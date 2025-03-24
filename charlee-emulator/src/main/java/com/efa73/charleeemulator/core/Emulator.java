package com.efa73.charleeemulator.core;

import com.efa73.charleeemulator.core.dto.CycleData;
import com.efa73.charleeemulator.core.dto.VehicleCycleInfo;
import com.efa73.charleeemulator.core.dto.VehicleEventInfo;
import com.efa73.charleeemulator.core.dto.VehicleInfo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Emulator {
    // 주기 정보만 저장하는 버퍼
    private Queue<CycleData> buffer = new LinkedList<>();

    private final VehicleInfo vehicleInfo;
    private VehicleCycleInfo vehicleCycleInfo;
    private String carOnTime;
    private String carOffTime;

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdHHmmss");
    private final DateTimeFormatter oTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");

    public Emulator(VehicleInfo vehicleInfo) {
        this.vehicleInfo = vehicleInfo; //로직 추후 수정 가능 ex) this.vehicleInfo = new vehicleInfo(mdn)
    }

    // 차량 시동 ON 정보 생성 및 전송
    public VehicleEventInfo sendCarStart() {
        carOnTime = LocalDateTime.now().format(dateTimeFormatter);
        System.out.println("시동 ON: " + carOnTime);
        return VehicleEventInfo.createCarOnEvent(vehicleInfo, vehicleCycleInfo, carOnTime);
    }

    //1초마다 주기 정보 생성 및 버퍼에 추가
    public void addCycleData(String sec, String gcd, String lat, String lon, String ang, String spd) {
        CycleData data = new CycleData(sec, gcd, lat, lon, ang, spd);
        buffer.add(data);

    }

    // 주기 값을 설정하고, 버퍼에 담긴 데이터를 전송
    public void sendCycleInfo(int count) {
        if (buffer.size() != count) {
            throw new RuntimeException("버퍼에 담긴 데이터의 개수가 설정값과 다름");
        }

        String oTime = LocalDateTime.now().format(oTimeFormatter);

        vehicleCycleInfo = new VehicleCycleInfo(vehicleInfo, oTime, String.valueOf(buffer.size()), new ArrayList<>(buffer));
        // 버퍼에 담긴 데이터를 전송하는 로직 추가

        buffer.clear();
    }

    // 차량 시동 OFF 정보 생성 및 전송
    public VehicleEventInfo sendCarStop() {
        carOffTime = LocalDateTime.now().format(dateTimeFormatter);
        System.out.println("시동 OFF: " + carOffTime);
        return VehicleEventInfo.createCarOffEvent(vehicleInfo, vehicleCycleInfo, carOffTime);
    }

    public Queue<CycleData> getBuffer() {
        return buffer;
    }
}
