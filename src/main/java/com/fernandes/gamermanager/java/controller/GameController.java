package com.fernandes.gamermanager.java.controller;

import com.fernandes.gamermanager.java.documents.GameDocument;
import com.fernandes.gamermanager.java.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("")
    public ResponseEntity<List<GameDocument>> listGamesCreated(){
        List<GameDocument> listGames = gameService.listAll();
        return ResponseEntity.ok().body(listGames);
    }

    @PostMapping
    public ResponseEntity<GameDocument> createGame(@RequestBody GameDocument game){
        return ResponseEntity.status(HttpStatus.CREATED).body(gameService.save(game));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable("id") String id){
        gameService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
