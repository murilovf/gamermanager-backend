package com.fernandes.gamermanager.java.repositories;

import com.fernandes.gamermanager.java.documents.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserDocument, String> {

    Optional<UserDocument> findUserByEmail(String email);
}
