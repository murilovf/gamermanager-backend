package com.fernandes.gamermanager.java.mappers;

import com.fernandes.gamermanager.java.dto.GameDTO;
import com.fernandes.gamermanager.java.utils.DateUtils;
import proto.Game;
import proto.Genre;

import java.util.Date;
import java.util.List;

public class GameMapper {

    public static GameDTO convertToGameEntity(Game game, List<Genre> genreListIgdb) {
        Genre genre = getFirstGenre(game, genreListIgdb);
        return GameDTO.builder()
                .id(game.getId())
                .name(game.getName())
                .genre(genre != null ? genre.getName() : null)
                .release(getFirstReleaseDate(game))
                .build();
    }

    private static Genre getFirstGenre(Game game, List<Genre> genreListIgdb) {
        if (game.getGenresCount() > 0) {
            return genreListIgdb.stream()
                    .filter(genre -> genre.getId() == game.getGenresList().getFirst().getId())
                    .findFirst()
                    .get();
        }
        return null;
    }

    private static Date getFirstReleaseDate(Game game) {
        if (game.getReleaseDatesCount() > 0) {
            return DateUtils.timestampProtoToDate(game.getFirstReleaseDate());
        }
        return null;
    }
}
