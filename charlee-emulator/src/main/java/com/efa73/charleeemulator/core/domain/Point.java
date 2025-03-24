package com.efa73.charleeemulator.core.domain;

public class Point {
    private final double lat;
    private final double lon;

    public Point(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "lat: " + lat + ", lon: " + lon;
    }
}
