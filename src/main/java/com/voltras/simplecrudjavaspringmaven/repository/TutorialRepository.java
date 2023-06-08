package com.voltras.simplecrudjavaspringmaven.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.voltras.simplecrudjavaspringmaven.model.Tutorial;

public interface TutorialRepository extends MongoRepository<Tutorial, String> {

	Page<Tutorial> findByTitleContainingIgnoreCase(String title, org.springframework.data.domain.Pageable pageable);

	Page<Tutorial> findByPublished(boolean published, org.springframework.data.domain.Pageable pageable);

}