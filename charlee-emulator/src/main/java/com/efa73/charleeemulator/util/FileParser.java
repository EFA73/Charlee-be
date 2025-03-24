package com.efa73.charleeemulator.util;

import com.efa73.charleeemulator.core.domain.Point;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.core.io.ClassPathResource;

public class FileParser {
    public static List<Point> parseFromCsvFile(final String filePath) {
        List<String> rawFile = fromFile(filePath);

        List<Point> points = new ArrayList<>();
        for (String line : rawFile) {
            if (line.startsWith("구분")) {
                continue;
            }

            List<String> seperatedLine = FileParser.parseWithComma(line);
            double latitude = Double.parseDouble(seperatedLine.get(4));
            double longitude = Double.parseDouble(seperatedLine.get(5));

            points.add(new Point(latitude, longitude));
        }
        return points;
    }

    private static List<String> fromFile(final String filePath) {
        ClassPathResource resource = new ClassPathResource(filePath);

        try {
            return Files.readAllLines(Path.of(resource.getURI()));
        } catch (IOException e) {
            throw new RuntimeException("파일 읽어오기 실패");
        }
    }

    private static List<String> parseWithComma(String input) {
        return Arrays.asList(input.split(","));
    }
}

