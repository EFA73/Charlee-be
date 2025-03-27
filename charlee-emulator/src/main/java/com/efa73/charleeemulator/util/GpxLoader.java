package com.efa73.charleeemulator.util;

import com.efa73.charleeemulator.core.domain.Route;
import com.efa73.charleeemulator.core.service.RouteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

/**
 * - CommandLineRunner 적용
 * - 서버 시작 시, GPX 파일 파싱 -> RouteService에 등록
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class GpxLoader implements CommandLineRunner {
    private final RouteService routeService;
    private final ResourceLoader resourceLoader;

    @Override
    public void run(String... args) throws Exception {
        Resource resource = resourceLoader.getResource("classpath:강남역_신분당선.gpx");
        String gpxPath = resource.getFile().getAbsolutePath();

        GpxParser parser = new GpxParser();
        Route route = parser.parseFromGpx(gpxPath);

        routeService.addRoute(route);
        log.info("GPX 파일 파싱 및 메모리 저장 완료");
    }
}

