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
@RequestMapping("/api/collector")
@RequiredArgsConstructor
public class CollectController {

    private final CollectService collectService;

    @PostMapping("/cycle/collect")
    public CollectResponse collectCycle(@RequestBody CycleInfoRequest cycleInfoRequest) {

        CycleInfo cycleInfo = CycleInfoRequest.createEntity(cycleInfoRequest);

        List<CycleData> cycleDataList = cycleInfoRequest.cList().stream().map(request -> {
            return CycleDataRequest.createEntity(request, cycleInfo);
        }).toList();

        String mdn = collectService.collectCycle(cycleInfo, cycleDataList);

        return new CollectResponse(String.valueOf(HttpStatus.OK), "", mdn);
    }

    @PostMapping("/event/collect")
    public CollectResponse collectEvent(@RequestBody EventInfoRequest eventInfoRequest) {

        CycleInfo eventInfo = EventInfoRequest.createEntity(eventInfoRequest);

        String mdn = collectService.collectEvent(eventInfo);

        return new CollectResponse(String.valueOf(HttpStatus.OK), "", mdn);
    }
}
