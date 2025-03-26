package com.efa73.charleeemulator.core.client;

import com.efa73.charleeemulator.core.dto.request.VehicleCycleInfoRequest;
import com.efa73.charleeemulator.core.dto.request.VehicleEventInfoRequest;
import com.efa73.charleeemulator.core.dto.response.CollectorResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class CollectorClient {

    @Value("${collector.url}")
    private String baseUrl;

    private final RestClient restClient;

    public CollectorClient() {
        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public CollectorResponse postCycleCollect(VehicleCycleInfoRequest body) {
        return restClient.post()
                .uri("/cycle/collect")
                .body(body)
                .retrieve()
                .body(CollectorResponse.class);
    }

    public CollectorResponse postEventCollect(VehicleEventInfoRequest body) {
        return restClient.post()
                .uri("/event/collect")
                .body(body)
                .retrieve()
                .body(CollectorResponse.class);
    }
}
