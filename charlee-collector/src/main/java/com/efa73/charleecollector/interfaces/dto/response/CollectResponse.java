package com.efa73.charleecollector.interfaces.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class CollectResponse {

    private String rstCd;

    private String rstMsg;

    private String mdn;
}
