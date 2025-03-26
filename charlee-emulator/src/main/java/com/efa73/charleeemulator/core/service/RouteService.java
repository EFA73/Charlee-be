package com.efa73.charleeemulator.core.service;

import com.efa73.charleeemulator.core.domain.Route;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 메모리 내에서 Route 리스트 관리
 */
@Service
public class RouteService {
    private final List<Route> routes = new ArrayList<>();

    public void addRoute(Route route) {
        routes.add(route);
    }

    public List<Route> getRoutes() {
        return routes;
    }
}
