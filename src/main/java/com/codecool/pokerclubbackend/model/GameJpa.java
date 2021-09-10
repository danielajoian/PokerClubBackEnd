package com.codecool.pokerclubbackend.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
//@NoArgsConstructor
@AllArgsConstructor
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
}
