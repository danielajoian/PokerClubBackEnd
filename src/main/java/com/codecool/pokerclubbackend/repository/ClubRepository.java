package com.codecool.pokerclubbackend.repository;

import com.codecool.pokerclubbackend.model.ClubJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClubRepository extends JpaRepository<ClubJpa, Long> {

    Optional<ClubJpa> findByClubUsername(String clubUsername);

    List<ClubJpa> findByCity(String city);
}

