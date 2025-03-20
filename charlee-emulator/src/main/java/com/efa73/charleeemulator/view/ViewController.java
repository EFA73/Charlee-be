package com.efa73.charleeemulator.view;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ViewController {

    @GetMapping("/emulator")
    public String emulator() {
        return "emulator";
    }
}
