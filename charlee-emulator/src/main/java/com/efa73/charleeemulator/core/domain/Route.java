package com.efa73.charleeemulator.core.domain;

import com.efa73.charleeemulator.util.FileParser;
import lombok.Getter;

import java.util.List;

/**
 * 위도, 경도 좌표를 저장하는 객체
 * <p/>
 * 프로그램 실행 시 파일로부터 경로를 읽어들여 저장
 */

@Getter
public class Route {
    private final List<Point> points;

    public Route() {
        points = FileParser.parseFromCsvFile("location-data.csv");
    }

    public Point getSinglePoint(int position) {
        return points.get(position);
    }
}
