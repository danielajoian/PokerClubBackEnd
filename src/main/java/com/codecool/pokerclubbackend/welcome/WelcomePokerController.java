package com.codecool.pokerclubbackend.welcome;

import com.codecool.pokerclubbackend.welcome.WelcomePokerBean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4000")
public class WelcomePokerController {

    @GetMapping(path = "/welcome")
    public String welcome() {
        return "Welcome Dani!";
    }

    @GetMapping(path = "/welcome-bean")
    public WelcomePokerBean welcomeBean() {
        return new WelcomePokerBean();
    }

    @GetMapping(path = "/welcome/{username}")
    public WelcomePokerBean welcomeUser(@PathVariable String username) {
        throw new RuntimeException("Something went wrong");
//        return new WelcomePokerBean(String.format("Hello %s", username));
    }
}
