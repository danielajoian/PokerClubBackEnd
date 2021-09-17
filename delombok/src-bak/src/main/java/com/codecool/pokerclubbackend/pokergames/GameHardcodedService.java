package com.codecool.pokerclubbackend.pokergames;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GameHardcodedService {

    private static List<Game> games = new ArrayList<>();
    private static int idCounter = 0;

    static {
        games.add(new Game(++idCounter,
                "AllIn", "Free BuyIn",
                new Date(), false));
        games.add(new Game(++idCounter,
                "AllIn", "300 Knock Out",
                new Date(), false));
        games.add(new Game(++idCounter,
                "AllIn", "200 Freeze Out",
                new Date(), false));
        games.add(new Game(++idCounter,
                "AllIn", "Cash-Game 1/3",
                new Date(), false));
        games.add(new Game(++idCounter,
                "AllIn", "Cash-Game 5/10",
                new Date(), false));
    }

    public List<Game> findAll() {
        return games;
    }

    public Game save(Game game) {
        if (game.getId() == -1 || game.getId() == 0) {
            game.setId(++idCounter);
            games.add(game);
        } else {
            deleteById(game.getId());
            games.add(game);
        }
        return game;
    }

    public Game deleteById(long id) {
        Game game = findById(id);
        if (game == null) return null;
        if (games.remove(game)) {
            return game;
        }
        return null;
    }

    public Game findById(long id) {
        for (Game game: games) {
            if (game.getId() == id) {
                return game;
            }
        }
        return null;
    }

}
