package com.efa73.charleeemulator.core.dto.request;

/**
 * mdn: 차량 식별 key
 * tid: 터미널 아이디
 * mid: 제조사 아이디
 * pv: 패킷 버전
 * did: 디바이스 아이디
 */
public record VehicleInfoRequest(String mdn, String tid, String mid, String pv, String did) {
    public VehicleInfoRequest(String mdn) {
        this(mdn, "A001", "6", "5", "1");
    }
}
