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
@Table(name = "private_games")
public class PrivateGameJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String clubUsername;
    private String title;

    @Column(length = 5000)
    private String details;

    private Date beginDate;
    private boolean hasEnded;

    private String secretCode;

    private boolean notPrivate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PrivateGameJpa privateGameJpa = (PrivateGameJpa) o;

        return Objects.equals(id, privateGameJpa.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
