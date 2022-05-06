package com.devian.gamerplacebot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bot")
public class BotController {

    @GetMapping("/clubs-map")
    public String clubsMap() {
        return "index";
    }
}
