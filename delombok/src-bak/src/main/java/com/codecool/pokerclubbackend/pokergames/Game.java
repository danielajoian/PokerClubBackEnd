package com.codecool.pokerclubbackend.pokergames;

import java.util.Date;
import java.util.Objects;

public class Game {

    private long id;
    private String clubUsername;
    private String description;
    private Date beginDate;
    private boolean hasEnded;

    protected Game() {}

    public Game(long id, String clubUsername,
                String description, Date beginDate,
                boolean hasEnded) {
        this.id = id;
        this.clubUsername = clubUsername;
        this.description = description;
        this.beginDate = beginDate;
        this.hasEnded = hasEnded;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClubUsername() {
        return clubUsername;
    }

    public void setClubUsername(String clubUsername) {
        this.clubUsername = clubUsername;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public boolean isHasEnded() {
        return hasEnded;
    }

    public void setHasEnded(boolean hasEnded) {
        this.hasEnded = hasEnded;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return id == game.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
