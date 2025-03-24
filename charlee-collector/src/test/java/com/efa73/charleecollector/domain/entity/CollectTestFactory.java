package com.efa73.charleecollector.domain.entity;

import com.efa73.charleecollector.interfaces.dto.request.EventType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan
public class CollectTestFactory {

    public CycleInfo createCycleInfo(String mdn) {
        return CycleInfo.builder()
                .mdn(mdn)
                .tid("default-tid")
                .mid("default-mid")
                .did("default-did")
                .pv("default-pv")
                .oTime(LocalDateTime.now())
                .eventType(EventType.CYCLE.getType())
                .build();
    }

    public CycleInfo createEventInfo(String mdn) {
        return CycleInfo.builder()
                .mdn(mdn)
                .tid("default-tid")
                .mid("default-mid")
                .did("default-did")
                .pv("default-pv")
                .onTime(LocalDateTime.now())
                .eventType(EventType.ON.getType())
                .build();
    }

    public List<CycleData> createCycleData(String mdn, CycleInfo cycleInfo) {
            List<CycleData> cycleDataList = new ArrayList<>();

            for(int i = 0; i < CycleInfo.DATA_LIST_LENGTH; i++) {
                var data = CycleData.builder()
                        .sec(String.valueOf(i))
                        .gcd("A")
                        .lat("37.1234")
                        .lon("127.5678")
                        .ang("45")
                        .spd("60")
                        .sum("100")
                        .bat("80")
                        .cycleInfo(cycleInfo)
                        .build();
                cycleDataList.add(data);
            }
            return cycleDataList;
    }
}
