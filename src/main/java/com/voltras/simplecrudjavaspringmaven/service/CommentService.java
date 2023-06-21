package com.voltras.simplecrudjavaspringmaven.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.voltras.simplecrudjavaspringmaven.exception.ResourceNotFoundException;
import com.voltras.simplecrudjavaspringmaven.model.Comment;
import com.voltras.simplecrudjavaspringmaven.repository.CommentRepository;
import com.voltras.simplecrudjavaspringmaven.repository.TutorialRepository;

@Service
public class CommentService {
	@Autowired
	private TutorialRepository tutorialRepository;
	
	@Autowired
	private CommentRepository commentRepository;

	@Cacheable("all_comments_by_tutor_id")
	public List<Comment> getAllCommentsByTutorialIdService(Long tutorialId) {
		doLongRunningTask();
		
		if (!tutorialRepository.existsById(tutorialId)) {
			throw new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId);
		}

		List<Comment> comments = commentRepository.findByTutorialId(tutorialId);
		return comments;
	}
	
	@Cacheable("comment_by_tutor_id")
	public Comment getCommentsByTutorialIdService(Long id) {
		doLongRunningTask();
		
		Comment comment = commentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Comment with id = " + id));

		return comment;
	}
	
	public Comment createCommentService(Long tutorialId, Comment commentRequest) {
		
		Comment comment = tutorialRepository.findById(tutorialId).map(tutorial -> {
			commentRequest.setTutorial(tutorial);
			
			return commentRepository.save(commentRequest);
		}).orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId));

		return comment;
	}
	
	@CacheEvict(value = "all_comments_by_tutor_id", allEntries = true)
	public Comment updateCommentService(long id, Comment commentRequest) {
		Comment comment = commentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("CommentId " + id + "not found"));

		comment.setContent(commentRequest.getContent());

		return commentRepository.save(comment);
	}
	
	@CacheEvict(value = "all_comments_by_tutor_id", allEntries = true)
	public void deleteCommentService(long id) {
		commentRepository.deleteById(id);
	}
	
	public void deleteAllCommentsOfTutorialService(Long tutorialId) {
		if (!tutorialRepository.existsById(tutorialId)) {
			throw new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId);
		}

		commentRepository.deleteByTutorialId(tutorialId);
	}

	private void doLongRunningTask() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}