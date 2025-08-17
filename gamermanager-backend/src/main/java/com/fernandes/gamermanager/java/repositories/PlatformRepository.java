package com.fernandes.gamermanager.java.repositories;

import com.fernandes.gamermanager.java.documents.GameDocument;
import com.fernandes.gamermanager.java.documents.PlatformDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlatformRepository extends MongoRepository<PlatformDocument, String> {
}
