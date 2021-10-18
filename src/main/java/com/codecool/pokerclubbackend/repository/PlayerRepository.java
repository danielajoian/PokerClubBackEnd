package com.codecool.pokerclubbackend.repository;

import com.codecool.pokerclubbackend.model.PlayerJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerJpa, Long> {

    Optional<PlayerJpa> findByUsername(String username);

    List<PlayerJpa> findByPrivateGameId(Long privateGameId);

    @Transactional
    @Modifying
    @Query("update PlayerJpa player set player.imageLink = :imageLink where player.id = :playerId")
    void setPlayerImageLink(@Param("playerId") Long id,
                            @Param("imageLink") String imageLink);
}
