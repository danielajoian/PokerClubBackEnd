package com.codecool.pokerclubbackend.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//swagger annotations
@ApiModel(description = "Details about players")

@Entity
@Table(name = "players")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Slf4j
public class PlayerJpa {

    //swagger annotations
    @ApiModelProperty(notes = "Unique player Id")
    @Id
    @Column(name="id", unique=true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ApiModelProperty(notes = "Unique private game Id that the player has sign-in to")
    private Long privateGameId;

    @ApiModelProperty(notes = "Unique name for the player")
    @Column(name="username", unique=true)
    private String username;

    @ApiModelProperty(notes = "The city of the player")
    private String city;

    @ApiModelProperty(notes = "Unique email for the player")
    @Column(name="email", unique=true)
    private String email;

    @ApiModelProperty(notes = "Password for the player")
    private String password;

    @Column
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

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
