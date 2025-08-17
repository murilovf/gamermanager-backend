package com.fernandes.gamermanager.java.integrations.igdb;

import com.api.igdb.request.IGDBWrapper;
import com.api.igdb.utils.Endpoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proto.Genre;
import proto.GenreResult;

import java.util.ArrayList;
import java.util.List;

@Service
public class IGDBGenreService {
    private IGDBWrapper wrapper = IGDBWrapper.INSTANCE;

    private static final String FIELDS = "fields checksum,created_at,name,slug,updated_at,url; limit 100;";

    @Autowired
    private IGDBAuthService authService;

    public List<Genre> listGenres() {
        try {
            authService.generateCredentials(wrapper);

            byte[] bytes = wrapper.apiProtoRequest(Endpoints.GENRES, FIELDS);
            return GenreResult.parseFrom(bytes).getGenresList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    public Genre getGenreById(Long id) {
        try {
            authService.generateCredentials(wrapper);

            String query = getQueryGenreById(id);

            byte[] bytes = wrapper.apiProtoRequest(Endpoints.GENRES, query);
            List<Genre> genresList = GenreResult.parseFrom(bytes).getGenresList();
            return genresList.size() > 0 ? genresList.get(0) : null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private static String getQueryGenreById(Long id) {
        StringBuilder query = new StringBuilder();
        query.append("fields checksum,created_at,name,slug,updated_at,url;");
        if (id != null) {
            query.append("where id = " + id + "; ");
        }

        return query.toString();
    }
}
