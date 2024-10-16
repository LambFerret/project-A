package com.lambferret.project_a.service;

import com.lambferret.project_a.document.Bulletin;
import com.lambferret.project_a.repository.BulletinRepository;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BulletinService {

    BulletinRepository bulletinRepository;

    public BulletinService(BulletinRepository bulletinRepository) {
        this.bulletinRepository = bulletinRepository;
    }

    public void updateById(String id, String title, String content, String user, String date) {
        bulletinRepository.updateById(id, title, content, user, date);
    }

    public void saveBulletin(Bulletin bulletin) {
        bulletinRepository.save(bulletin);
    }

    public Bulletin findById(String id) {
        return bulletinRepository.findById(id);
    }

    public Page<Bulletin> findAll(String title, String content, String author, String date, Pageable pageable) {
        return bulletinRepository.findAll(title, content, author, date, pageable);
    }

    public void deleteById(ObjectId id) {
        bulletinRepository.deleteById(id);
    }

}
