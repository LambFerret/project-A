package com.lambferret.project_a.repository;

import com.lambferret.project_a.document.Bulletin;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface BulletinRepository extends MongoRepository<Bulletin, ObjectId> {

    @Query("{ 'title': ?0, 'content': ?1, 'author': ?2, 'date': ?3 }")
    Page<Bulletin> findAll(String title, String content, String author, String date, Pageable pageable);

    @Query("{ '_id': ?0 }")
    Bulletin findById(String id);

    default void updateById(String id, String title, String content, String user, String date) {
        Bulletin bulletin = findById(id);
        if (bulletin != null) {
            bulletin.setTitle(title);
            bulletin.setContent(content);
            bulletin.setDate(date);
            bulletin.setUser(user);
            save(bulletin);
        }
    }

    void deleteById(ObjectId id);
}
