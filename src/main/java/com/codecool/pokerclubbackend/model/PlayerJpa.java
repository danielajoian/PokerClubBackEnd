package com.codecool.pokerclubbackend.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "players")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
//@NoArgsConstructor
@AllArgsConstructor
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
}