package com.fernandes.gamermanager.java.controller;

import com.fernandes.gamermanager.java.service.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/platforms")
public class PlatformController {

    @Autowired
    private PlatformService platformService;

    @PostMapping("migrate-from-igdb")
    public ResponseEntity<Void> migrateFromIGDB(){
        platformService.migratePlatforms();
        return ResponseEntity.ok().build();
    }
}
