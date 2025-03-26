package com.efa73.charleeemulator.core.domain;

import org.junit.jupiter.api.Test;

class RouteTest {
    Route route = new Route();

    @Test
    void getPoints() {
        for (Point point : route.getPoints()) {
            System.out.println(point);
        }
    }
}
