package com.fernandes.gamermanager.java.service;

import com.api.igdb.exceptions.RequestException;
import com.fernandes.gamermanager.java.documents.GameDocument;
import com.fernandes.gamermanager.java.dto.FilterGames;
import com.fernandes.gamermanager.java.dto.GameDTO;
import com.fernandes.gamermanager.java.mappers.GameMapper;
import com.fernandes.gamermanager.java.repositories.GameRepository;
import com.fernandes.gamermanager.java.integrations.igdb.IGDBGamesService;
import com.fernandes.gamermanager.java.integrations.igdb.IGDBGenreService;
import com.fernandes.gamermanager.java.utils.DateUtils;
import com.google.protobuf.InvalidProtocolBufferException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proto.Game;
import proto.Genre;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameService {

    @Autowired
    private IGDBGamesService igdbGamesService;
    @Autowired
    private IGDBGenreService igdbGenreService;
    @Autowired
    private GameRepository gameRepository;

    public List<GameDTO> listGames(FilterGames filter) throws RequestException, InvalidProtocolBufferException {
        List<Game> gamesListIgdb = igdbGamesService.listGames(filter);
        List<Genre> genreListIgdb = igdbGenreService.listGenres();

        return gamesListIgdb.stream()
                .map(game -> GameMapper.convertToGameEntity(game, genreListIgdb))
                .collect(Collectors.toList());
    }

    private Genre getFirstGenre(Game game, List<Genre> genreListIgdb) {
        if (game.getGenresCount() > 0) {
            return genreListIgdb.stream()
                    .filter(genre -> genre.getId() == game.getGenresList().getFirst().getId())
                    .findFirst()
                    .get();
        }
        return null;
    }

    private Date getFirstReleaseDate(Game game) {
        if (game.getReleaseDatesCount() > 0) {
            return DateUtils.timestampProtoToDate(game.getFirstReleaseDate());
        }
        return null;
    }

    public GameDocument save(GameDocument gameDocument) {
        return gameRepository.save(gameDocument);
    }

    public List<GameDocument> saveAll(List<GameDocument> gamesList) {
        return gameRepository.saveAll(gamesList);
    }

    public List<GameDocument> listAll() {
        return gameRepository.findAll();
    }

    public GameDocument findById(String id) {
        Optional<GameDocument> optionalGame = gameRepository.findById(id);
        return optionalGame.isPresent() ? optionalGame.get() : null;
    }

    public void deleteById(String id) {
        gameRepository.deleteById(id);
    }


}
