package com.fernandes.gamermanager.java.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilterGames {
    private String name;
    private String releaseYear;
    private String genre;
    private String platform;
}
