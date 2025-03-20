package com.efa73.charleeemulator.core.domain;

/**
 * sec: 발생시간 '초'
 * gcd: GPS 상태
 * lat: GPS 위도
 * lon: GPS 경도
 * ang: 방향
 * spd: 속도
 * sum: 누적 주행 거리
 * bat: 배터리 전압
 */
public class CycleData {
    private final String sec;
    private final String gcd;
    private final String lat;
    private final String lon;
    private final String ang;
    private final String spd;
    private String sum;
    private final String bat = "100";

    public CycleData(String sec, String gcd, String lat, String lon, String ang, String spd) {
        this.sec = sec;
        this.gcd = gcd;
        this.lat = lat;
        this.lon = lon;
        this.ang = ang;
        this.spd = spd;
    }

    public void calculateSum(CycleData prevCycleData) {
        // 이전의 누적 값에 현재까지 이동거리 더하면 됨
    }
}
