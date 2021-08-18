package com.codecool.pokerclubbackend.welcome;

public class WelcomePokerBean {
    private String message;

    public WelcomePokerBean(String message) {
        this.message = message;
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
}
