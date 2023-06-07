package com.voltras.simplecrudjavaspringmaven.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.voltras.simplecrudjavaspringmaven.model.Tutorial;

public interface TutorialRepository extends MongoRepository<Tutorial, String> {
	@Query("{published: ?0}")
	List<Tutorial> findByPublished(boolean published);

	@Query("{title: ?0}")
	List<Tutorial> findByTitle(String title);

}