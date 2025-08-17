package com.fernandes.gamermanager.java.integrations.igdb;

import com.api.igdb.request.IGDBWrapper;
import com.api.igdb.utils.Endpoints;
import com.fernandes.gamermanager.java.dto.FilterGames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proto.Game;
import proto.GameResult;

import java.util.ArrayList;
import java.util.List;

@Service
public class IGDBGamesService {

    private IGDBWrapper wrapper = IGDBWrapper.INSTANCE;

    @Autowired
    private IGDBAuthService authService;

    public List<Game> listGames(FilterGames filter) {
        try {
            authService.generateCredentials(wrapper);

            String query = getQuery(filter);

            byte[] bytes = wrapper.apiProtoRequest(Endpoints.GAMES, query);
            return GameResult.parseFrom(bytes).getGamesList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }

    }

    private static String getQuery(FilterGames filter) {
        StringBuilder query = new StringBuilder();
        query.append("fields id,name,category,genres,platforms,release_dates,summary,url;");
        query.append("search \" " + filter.getName() + " \"; ");

        return query.toString();
    }
}
