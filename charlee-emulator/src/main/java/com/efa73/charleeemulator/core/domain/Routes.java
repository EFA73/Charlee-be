package com.efa73.charleeemulator.core.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class Routes {
    private final List<Route> routes;

    private Routes(List<Route> routes) {
        this.routes = List.copyOf(routes);
    }

    public static Routes of(List<Route> routes) {
        return new Routes(routes);
    }

    public Route getSingleRoute(int position) {
        return routes.get(position);
    }
}
