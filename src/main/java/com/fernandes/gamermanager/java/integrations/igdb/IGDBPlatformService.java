package com.fernandes.gamermanager.java.integrations.igdb;

import com.api.igdb.request.IGDBWrapper;
import com.api.igdb.utils.Endpoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proto.Platform;
import proto.PlatformResult;

import java.util.ArrayList;
import java.util.List;

@Service
public class IGDBPlatformService {
    private IGDBWrapper wrapper = IGDBWrapper.INSTANCE;

    private static final String FIELDS = "fields id,abbreviation,name; limit 500;";

    @Autowired
    private IGDBAuthService authService;

    public List<Platform> listPlatforms() {
        try {
            authService.generateCredentials(wrapper);

            byte[] bytes = wrapper.apiProtoRequest(Endpoints.PLATFORMS, FIELDS);
            return PlatformResult.parseFrom(bytes).getPlatformsList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }
}
