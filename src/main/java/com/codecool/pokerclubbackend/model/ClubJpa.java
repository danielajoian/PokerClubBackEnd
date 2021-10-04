package com.codecool.pokerclubbackend.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
//@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clubs")
public class ClubJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String clubUsername;
    private String password;
    private String city;
    private String country;
    private String address;
    private String email;
    private String site;
    private Integer phoneNumber;

    @Column
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ClubJpa clubJpa = (ClubJpa) o;

        return Objects.equals(id, clubJpa.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
