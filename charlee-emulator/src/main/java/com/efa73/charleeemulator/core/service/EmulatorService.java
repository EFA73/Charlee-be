package com.efa73.charleeemulator.core.service;

import com.efa73.charleeemulator.core.client.CollectorClient;
import com.efa73.charleeemulator.core.dto.request.CycleDataRequest;
import com.efa73.charleeemulator.core.dto.request.VehicleCycleInfoRequest;
import com.efa73.charleeemulator.core.dto.request.VehicleEventInfoRequest;
import com.efa73.charleeemulator.core.dto.request.VehicleInfoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmulatorService {

    private final CollectorClient collectorClient;

    public void start() {
        sendCarOnEvent();
        scheduleCycleData();
    }

    public void stop() {
        sendCarOffEvent();
    }

    private void sendCarOnEvent() {
        VehicleEventInfoRequest vehicleEventInfoRequest = VehicleEventInfoRequest.createCarOnEvent(
                new VehicleInfoRequest("1"),
                new CycleDataRequest("1", "1", "1", "1", "1", "1"),
                "20210901092000"
        );
        collectorClient.postEventCollect(vehicleEventInfoRequest);
    }

    private void sendCarOffEvent() {
        VehicleEventInfoRequest vehicleEventInfoRequest = VehicleEventInfoRequest.createCarOffEvent(
                new VehicleInfoRequest("1"),
                new CycleDataRequest("1", "1", "1", "1", "1", "1"),
                "20210901092000"
        );
        collectorClient.postEventCollect(vehicleEventInfoRequest);
    }

    private void scheduleCycleData() {
        // Todo: 추후 버퍼에 담아 60, 120, 180초 단위로 전송하는 기능 필요
        List<CycleDataRequest> cList = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            cList.add(
                    new CycleDataRequest(String.valueOf(i), String.valueOf(i), String.valueOf(i), String.valueOf(i), String.valueOf(i), String.valueOf(i))
            );
        }

        VehicleCycleInfoRequest vehicleCycleInfoRequest = new VehicleCycleInfoRequest(
                new VehicleInfoRequest("1"),
                "20210901092000",
                String.valueOf(cList.size()),
                cList
        );
    }

}
