package com.voltras.simplecrudjavaspringmaven.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.voltras.simplecrudjavaspringmaven.model.Comment;
import com.voltras.simplecrudjavaspringmaven.service.CommentService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@GetMapping("/tutorials/{tutorialId}/comments")
	public List<Comment> getAllCommentsByTutorialId(@PathVariable(value = "tutorialId") Long tutorialId) {
		return commentService.getAllCommentsByTutorialIdService(tutorialId);
	}

	@GetMapping("/comments/{id}")
	public Comment getCommentsByTutorialId(@PathVariable(value = "id") Long id) {
		return commentService.getCommentsByTutorialIdService(id);
	}

	@PostMapping("/tutorials/{tutorialId}/comments")
	public Comment createComment(@PathVariable(value = "tutorialId") Long tutorialId,
			@RequestBody Comment commentRequest) {
		
		return commentService.createCommentService(tutorialId, commentRequest);
	}

	@PutMapping("/comments/{id}")
	public Comment updateComment(@PathVariable("id") long id, @RequestBody Comment commentRequest) {
		return commentService.updateCommentService(id, commentRequest);
	}

	@DeleteMapping("/comments/{id}")
	public void deleteComment(@PathVariable("id") long id) {
		commentService.deleteCommentService(id);
	}

	@DeleteMapping("/tutorials/{tutorialId}/comments")
	public void deleteAllCommentsOfTutorial(@PathVariable(value = "tutorialId") Long tutorialId) {
		commentService.deleteAllCommentsOfTutorialService(tutorialId);
	}
}