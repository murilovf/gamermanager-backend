package com.fernandes.gamermanager.java.service;

import com.api.igdb.exceptions.RequestException;
import com.fernandes.gamermanager.java.documents.PlatformDocument;
import com.fernandes.gamermanager.java.integrations.igdb.IGDBGamesService;
import com.fernandes.gamermanager.java.dto.FilterGames;
import com.fernandes.gamermanager.java.integrations.igdb.IGDBPlatformService;
import com.fernandes.gamermanager.java.mappers.PlatformMapper;
import com.fernandes.gamermanager.java.repositories.PlatformRepository;
import com.google.protobuf.InvalidProtocolBufferException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proto.Platform;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlatformService {

    @Autowired
    private IGDBPlatformService igdbPlatformService;

    @Autowired
    private PlatformRepository platformRepository;

    public void migratePlatforms() {
        platformRepository.deleteAll();

        List<Platform> platforms = igdbPlatformService.listPlatforms();
        List<PlatformDocument> listPlatformsDocuments =
                platforms.stream()
                        .map(platform -> PlatformMapper.convertIgdbToDocument(platform))
                        .collect(Collectors.toList());

        platformRepository.saveAll(listPlatformsDocuments);
    }


}
