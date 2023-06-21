package com.voltras.simplecrudjavaspringmaven.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.voltras.simplecrudjavaspringmaven.model.Comment;

import jakarta.transaction.Transactional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findByTutorialId(Long postId);

	@Transactional
	void deleteByTutorialId(long tutorialId);
}
