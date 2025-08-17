package com.fernandes.gamermanager.java.repositories;

import com.fernandes.gamermanager.java.documents.GameDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameRepository extends MongoRepository<GameDocument, String> {
}
