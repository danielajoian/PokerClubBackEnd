package com.codecool.pokerclubbackend.welcome;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WelcomePokerBean {
    private String message;
    private List<String> welcomeMessages = new ArrayList<String>(){{
            add("It's a real joy to see you here!");
            add("Welcome! We've been expecting you!");
            add("Nice to see you here!");
            add("Hope you'll have a blast!");
            add("Finally! You made it till here!");
            add("We're gonna have so much fun!");
            add("You are in the right place to be!");
            add("Enjoy!");
    }};

    public WelcomePokerBean() {
        this.message = getRandomMessage();
    }

    public String getRandomMessage() {
        Collections.shuffle(welcomeMessages);
        return this.message = welcomeMessages.get(0);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String
    toString() {
        return "PokerBean{" +
                "message='" + message + '\'' +
                '}';
    }

    public List<String> getWelcomeMessages() {
        return welcomeMessages;
    }

    public void setWelcomeMessages(List<String> welcomeMessages) {
        this.welcomeMessages = welcomeMessages;
    }
}
