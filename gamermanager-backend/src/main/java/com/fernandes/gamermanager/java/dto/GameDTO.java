package com.fernandes.gamermanager.java.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GameDTO {
    private Long id;
    private String name;
    private String description;
//    private List<String> platforms;
    private String genre;
    private Date release;
}
