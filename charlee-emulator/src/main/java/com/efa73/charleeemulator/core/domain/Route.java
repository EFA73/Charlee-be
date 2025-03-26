package com.efa73.charleeemulator.core.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 위도, 경도 좌표를 저장하는 객체
 * <p/>
 * 프로그램 실행 시 파일로부터 경로를 읽어들여 저장
 */

@Getter
public class Route {
    private List<Point> points = new ArrayList<>();

    public void addPoint(Point point) {
        points.add(point);
    }

    public Point getSinglePoint(int position) {
        return points.get(position);
    }
}
