package com.codecool.pokerclubbackend.model;

import lombok.*;
import org.hibernate.Hibernate;
import javax.persistence.*;
import java.util.Objects;
//@NoArgsConstructor
@Entity
@Table(name = "players")
public class PlayerJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String email;
    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PlayerJpa playerJpa = (PlayerJpa) o;
        return Objects.equals(id, playerJpa.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    //<editor-fold defaultstate="collapsed" desc="delombok">
    @SuppressWarnings("all")
    public static class PlayerJpaBuilder {
        @SuppressWarnings("all")
        private Long id;
        @SuppressWarnings("all")
        private String username;
        @SuppressWarnings("all")
        private String email;
        @SuppressWarnings("all")
        private String password;

        @SuppressWarnings("all")
        PlayerJpaBuilder() {
        }

        @SuppressWarnings("all")
        public PlayerJpa.PlayerJpaBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        @SuppressWarnings("all")
        public PlayerJpa.PlayerJpaBuilder username(final String username) {
            this.username = username;
            return this;
        }

        @SuppressWarnings("all")
        public PlayerJpa.PlayerJpaBuilder email(final String email) {
            this.email = email;
            return this;
        }

        @SuppressWarnings("all")
        public PlayerJpa.PlayerJpaBuilder password(final String password) {
            this.password = password;
            return this;
        }

        @SuppressWarnings("all")
        public PlayerJpa build() {
            return new PlayerJpa(this.id, this.username, this.email, this.password);
        }

        @Override
        @SuppressWarnings("all")
        public String toString() {
            return "PlayerJpa.PlayerJpaBuilder(id=" + this.id + ", username=" + this.username + ", email=" + this.email + ", password=" + this.password + ")";
        }
    }

    @SuppressWarnings("all")
    public static PlayerJpa.PlayerJpaBuilder builder() {
        return new PlayerJpa.PlayerJpaBuilder();
    }

    @SuppressWarnings("all")
    public Long getId() {
        return this.id;
    }

    @SuppressWarnings("all")
    public String getUsername() {
        return this.username;
    }

    @SuppressWarnings("all")
    public String getEmail() {
        return this.email;
    }

    @SuppressWarnings("all")
    public String getPassword() {
        return this.password;
    }

    @SuppressWarnings("all")
    public void setId(final Long id) {
        this.id = id;
    }

    @SuppressWarnings("all")
    public void setUsername(final String username) {
        this.username = username;
    }

    @SuppressWarnings("all")
    public void setEmail(final String email) {
        this.email = email;
    }

    @SuppressWarnings("all")
    public void setPassword(final String password) {
        this.password = password;
    }

    @Override
    @SuppressWarnings("all")
    public String toString() {
        return "PlayerJpa(id=" + this.getId() + ", username=" + this.getUsername() + ", email=" + this.getEmail() + ", password=" + this.getPassword() + ")";
    }

    @SuppressWarnings("all")
    public PlayerJpa() {
    }

    @SuppressWarnings("all")
    public PlayerJpa(final Long id, final String username, final String email, final String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }
    //</editor-fold>
}
