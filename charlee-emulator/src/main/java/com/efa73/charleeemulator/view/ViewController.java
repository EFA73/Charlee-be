package com.efa73.charleeemulator.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/emulator")
    public String emulator() {
        return "emulator";
    }
}
