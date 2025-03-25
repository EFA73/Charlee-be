package com.efa73.charleeemulator.core.domain;

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
}