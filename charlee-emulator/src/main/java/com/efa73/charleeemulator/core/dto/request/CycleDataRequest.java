package com.efa73.charleeemulator.core.dto.request;

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
public record CycleDataRequest(String sec, String gcd, String lat, String lon, String ang, String spd) {
}
