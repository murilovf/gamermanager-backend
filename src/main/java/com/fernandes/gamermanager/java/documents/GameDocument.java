package com.fernandes.gamermanager.java.documents;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "games")
@Getter
@Setter
@Builder
public class GameDocument {
    @Id
    private String id;
    private Long externalId;
    private String name;
    private String description;
    //    private List<String> platforms;
    private String genre;
    private Date release;

}
