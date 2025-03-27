package com.efa73.charleeemulator.util;

import com.efa73.charleeemulator.core.domain.Point;
import com.efa73.charleeemulator.core.domain.Route;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * - GPX 파일을 파싱
 * - XML 파싱 예외 처리
 * - Route 객체 생성
 */
@Component
@Slf4j
public class GpxParser {
    public Route parseFromGpx(final String path) {
        File file = new File(path);

        List<Point> points = new ArrayList<>();

        Document document = parseXmlFile(file);
        NodeList trkptList = document.getElementsByTagName("trkpt");

        for (int i = 0; i < trkptList.getLength(); i++) {
            Element trkpt = (Element) trkptList.item(i);
            double lat = Double.parseDouble(trkpt.getAttribute("lat"));
            double lon = Double.parseDouble(trkpt.getAttribute("lon"));

            log.info("위도: " + lat + ", 경도: " + lon);

            points.add(new Point(lat, lon));
        }

        Route route = Route.of(points);
        return route;
    }

    private Document parseXmlFile(File file) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            document.getDocumentElement().normalize();
            return document;
        } catch (Exception e) {
            throw new RuntimeException("XML 파싱 중 오류 발생", e);
        }
    }
}
