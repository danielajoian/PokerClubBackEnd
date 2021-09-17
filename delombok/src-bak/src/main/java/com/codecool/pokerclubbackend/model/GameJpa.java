package com.codecool.pokerclubbackend.model;

import lombok.*;
import org.hibernate.Hibernate;
import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
//@NoArgsConstructor
@Entity
@Table(name = "games")
public class GameJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String clubUsername;
    private String title;
    @Column(length = 5000)
    private String details;
    private Date beginDate;
    private boolean hasEnded;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        GameJpa gameJpa = (GameJpa) o;
        return Objects.equals(id, gameJpa.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    //<editor-fold defaultstate="collapsed" desc="delombok">
    @SuppressWarnings("all")
    public static class GameJpaBuilder {
        @SuppressWarnings("all")
        private long id;
        @SuppressWarnings("all")
        private String clubUsername;
        @SuppressWarnings("all")
        private String title;
        @SuppressWarnings("all")
        private String details;
        @SuppressWarnings("all")
        private Date beginDate;
        @SuppressWarnings("all")
        private boolean hasEnded;

        @SuppressWarnings("all")
        GameJpaBuilder() {
        }

        @SuppressWarnings("all")
        public GameJpa.GameJpaBuilder id(final long id) {
            this.id = id;
            return this;
        }

        @SuppressWarnings("all")
        public GameJpa.GameJpaBuilder clubUsername(final String clubUsername) {
            this.clubUsername = clubUsername;
            return this;
        }

        @SuppressWarnings("all")
        public GameJpa.GameJpaBuilder title(final String title) {
            this.title = title;
            return this;
        }

        @SuppressWarnings("all")
        public GameJpa.GameJpaBuilder details(final String details) {
            this.details = details;
            return this;
        }

        @SuppressWarnings("all")
        public GameJpa.GameJpaBuilder beginDate(final Date beginDate) {
            this.beginDate = beginDate;
            return this;
        }

        @SuppressWarnings("all")
        public GameJpa.GameJpaBuilder hasEnded(final boolean hasEnded) {
            this.hasEnded = hasEnded;
            return this;
        }

        @SuppressWarnings("all")
        public GameJpa build() {
            return new GameJpa(this.id, this.clubUsername, this.title, this.details, this.beginDate, this.hasEnded);
        }

        @Override
        @SuppressWarnings("all")
        public String toString() {
            return "GameJpa.GameJpaBuilder(id=" + this.id + ", clubUsername=" + this.clubUsername + ", title=" + this.title + ", details=" + this.details + ", beginDate=" + this.beginDate + ", hasEnded=" + this.hasEnded + ")";
        }
    }

    @SuppressWarnings("all")
    public static GameJpa.GameJpaBuilder builder() {
        return new GameJpa.GameJpaBuilder();
    }

    @SuppressWarnings("all")
    public long getId() {
        return this.id;
    }

    @SuppressWarnings("all")
    public String getClubUsername() {
        return this.clubUsername;
    }

    @SuppressWarnings("all")
    public String getTitle() {
        return this.title;
    }

    @SuppressWarnings("all")
    public String getDetails() {
        return this.details;
    }

    @SuppressWarnings("all")
    public Date getBeginDate() {
        return this.beginDate;
    }

    @SuppressWarnings("all")
    public boolean isHasEnded() {
        return this.hasEnded;
    }

    @SuppressWarnings("all")
    public void setId(final long id) {
        this.id = id;
    }

    @SuppressWarnings("all")
    public void setClubUsername(final String clubUsername) {
        this.clubUsername = clubUsername;
    }

    @SuppressWarnings("all")
    public void setTitle(final String title) {
        this.title = title;
    }

    @SuppressWarnings("all")
    public void setDetails(final String details) {
        this.details = details;
    }

    @SuppressWarnings("all")
    public void setBeginDate(final Date beginDate) {
        this.beginDate = beginDate;
    }

    @SuppressWarnings("all")
    public void setHasEnded(final boolean hasEnded) {
        this.hasEnded = hasEnded;
    }

    @Override
    @SuppressWarnings("all")
    public String toString() {
        return "GameJpa(id=" + this.getId() + ", clubUsername=" + this.getClubUsername() + ", title=" + this.getTitle() + ", details=" + this.getDetails() + ", beginDate=" + this.getBeginDate() + ", hasEnded=" + this.isHasEnded() + ")";
    }

    @SuppressWarnings("all")
    public GameJpa() {
    }

    @SuppressWarnings("all")
    public GameJpa(final long id, final String clubUsername, final String title, final String details, final Date beginDate, final boolean hasEnded) {
        this.id = id;
        this.clubUsername = clubUsername;
        this.title = title;
        this.details = details;
        this.beginDate = beginDate;
        this.hasEnded = hasEnded;
    }
    //</editor-fold>
}
