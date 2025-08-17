package com.fernandes.gamermanager.java.documents;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "platforms")
@Getter
@Setter
@Builder
public class PlatformDocument {
    @Id
    private String id;
    private Long externalId;
    private String name;
    private String abbreviation;
}
