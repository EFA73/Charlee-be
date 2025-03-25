package com.efa73.charleeemulator.core.controller;

import com.efa73.charleeemulator.core.service.EmulatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class EmulatorController {
    private final EmulatorService emulatorService;

    @PostMapping("/start")
    public void startEmulator() {
        emulatorService.start();
    }

    @PostMapping("/stop")
    public void stopEmulator() {
        emulatorService.stop();
    }
}
