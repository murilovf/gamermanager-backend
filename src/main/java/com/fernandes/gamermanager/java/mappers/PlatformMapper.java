package com.fernandes.gamermanager.java.mappers;

import com.fernandes.gamermanager.java.documents.PlatformDocument;
import proto.Platform;

public class PlatformMapper {
    public static PlatformDocument convertIgdbToDocument(Platform platform) {
        return PlatformDocument.builder()
                .externalId(platform.getId())
                .name(platform.getName())
                .abbreviation(platform.getAbbreviation())
                .build();
    }
}
