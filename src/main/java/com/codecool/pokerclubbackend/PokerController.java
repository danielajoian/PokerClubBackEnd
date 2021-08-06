package com.codecool.pokerclubbackend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PokerController {

    @GetMapping(path = "/poker")
    public String poker() {
        return "Poker Club!";
    }
}
