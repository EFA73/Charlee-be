package com.efa73.charleeemulator.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmulatorController {

    @GetMapping("/")
    public String emulator() {
        return "emulator";
    }
}
