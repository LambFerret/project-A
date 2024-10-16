package com.lambferret.project_a.repository;

import com.lambferret.project_a.document.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    @Query("{ 'username': ?0 }")
    User findByUsername(String username);


}
