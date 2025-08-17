package com.fernandes.gamermanager.java.integrations.igdb;

import com.api.igdb.request.IGDBWrapper;
import com.api.igdb.request.TwitchAuthenticator;
import com.api.igdb.utils.TwitchToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class IGDBAuthService {

    @Value("${igdb.client.id}")
    public String CLIENT_ID;

    @Value("${igdb.client.secret}")
    private String CLIENT_SECRET;

    public String getToken() {
        try {
            TwitchAuthenticator tAuth = TwitchAuthenticator.INSTANCE;
            TwitchToken token = tAuth.requestTwitchToken(CLIENT_ID, CLIENT_SECRET);

            return token.getAccess_token();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public void generateCredentials(IGDBWrapper wrapper) {
        String accessToken = getToken();
        wrapper.setCredentials(CLIENT_ID, accessToken);
    }
}
