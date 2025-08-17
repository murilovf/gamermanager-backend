package com.fernandes.gamermanager.java.controller;

import com.api.igdb.exceptions.RequestException;
import com.fernandes.gamermanager.java.dto.FilterGames;
import com.fernandes.gamermanager.java.dto.GameDTO;
import com.fernandes.gamermanager.java.service.GameService;
import com.google.protobuf.InvalidProtocolBufferException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/games-igdb")
public class GamesIGDBController {
    @Autowired
    private GameService gameService;

    @GetMapping("")
    public ResponseEntity<List<GameDTO>> listGames(@ModelAttribute FilterGames filter){
        try {
            List<GameDTO> games = gameService.listGames(filter);
            return ResponseEntity.ok().body(games);
        } catch (RequestException e) {
            throw new RuntimeException(e);
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
    }
}
