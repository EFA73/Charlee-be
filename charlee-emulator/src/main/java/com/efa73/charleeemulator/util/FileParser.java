package com.efa73.charleeemulator.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class FileParser {
    public static List<String> parseFromFile(final String filePath) {
        try {
            return Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
          throw new RuntimeException("파일 읽어오기 실패");
        }
    }

    public static List<String> parseWithComma(String input) {
        return Arrays.asList(input.split(","));
    }
}

