package com.efa73.charleecollector.interfaces.controller;

import com.efa73.charleecollector.domain.entity.CycleData;
import com.efa73.charleecollector.domain.entity.CycleInfo;
import com.efa73.charleecollector.domain.service.CollectService;
import com.efa73.charleecollector.interfaces.dto.request.CycleDataRequest;
import com.efa73.charleecollector.interfaces.dto.request.CycleInfoRequest;
import com.efa73.charleecollector.interfaces.dto.request.EventInfoRequest;
import com.efa73.charleecollector.interfaces.dto.response.CollectResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/collector")
@RequiredArgsConstructor
public class CollectController {

    private final CollectService collectService;

    @PostMapping("/cycle/collect")
    public CollectResponse collectCycle(@RequestBody CycleInfoRequest cycleInfoRequest) {

        CycleInfo cycleInfo = CycleInfoRequest.toEntity(cycleInfoRequest);
        List<CycleData> cycleDataList = CycleDataRequest.toEntityList(cycleInfoRequest.cList(), cycleInfo);

        String mdn = collectService.collectCycle(cycleInfo, cycleDataList);

        return CollectResponse.builder()
                .rstCd(String.valueOf(HttpStatus.OK.value()))
                .rstMsg("")
                .mdn(mdn)
                .build();
    }

    @PostMapping("/event/collect")
    public CollectResponse collectEvent(@RequestBody EventInfoRequest eventInfoRequest) {

        CycleInfo eventInfo = EventInfoRequest.toEntity(eventInfoRequest);

        String mdn = collectService.collectEvent(eventInfo);

        return CollectResponse.builder()
                .rstCd(String.valueOf(HttpStatus.OK.value()))
                .rstMsg("")
                .mdn(mdn)
                .build();
    }
}
