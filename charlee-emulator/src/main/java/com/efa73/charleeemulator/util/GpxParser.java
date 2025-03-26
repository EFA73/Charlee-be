package com.efa73.charleeemulator.util;

import com.efa73.charleeemulator.core.domain.Point;
import com.efa73.charleeemulator.core.domain.Route;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * - GPX 파일을 파싱
 * - XML 파싱 예외 처리
 * - Route 객체 생성
 */
public class GpxParser {
    public Route parseFromGpx(final String path) {
        File file = new File(path);
        Route route = new Route();

        Document document = parseXmlFile(file);
        NodeList trkptList = document.getElementsByTagName("trkpt");

        for (int i = 0; i < trkptList.getLength(); i++) {
            Element trkpt = (Element) trkptList.item(i);
            double lat = Double.parseDouble(trkpt.getAttribute("lat"));
            double lon = Double.parseDouble(trkpt.getAttribute("lon"));

            System.out.println("위도: " + lat + ", 경도: " + lon);

            route.addPoint(new Point(lat, lon));
        }

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
