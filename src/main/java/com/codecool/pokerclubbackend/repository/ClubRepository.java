package com.codecool.pokerclubbackend.repository;

import com.codecool.pokerclubbackend.model.ClubJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClubRepository extends JpaRepository<ClubJpa, Long> {

    Optional<ClubJpa> findByClubUsername(String clubUsername);

    List<ClubJpa> findByCity(String city);

    @Transactional
    @Modifying
    @Query("update ClubJpa club set club.imageLink = :imageLink where club.id = :clubId")
    void setClubImageLink(@Param("clubId") Long id,
                          @Param("imageLink") String imageLink);

}

