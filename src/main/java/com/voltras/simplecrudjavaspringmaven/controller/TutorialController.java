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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.voltras.simplecrudjavaspringmaven.model.Tutorial;
import com.voltras.simplecrudjavaspringmaven.repository.TutorialRepository;
import com.voltras.simplecrudjavaspringmaven.service.TutorialService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class TutorialController {

	@Autowired
	TutorialRepository tutorialRepository;

	@Autowired
	TutorialService tutorialService;

	// with cache
	@GetMapping("/tutorials")
	public List<Tutorial> getAllTutorials(@RequestParam(required = false) String title) {
		return tutorialService.getAllTutorialsService(title);
	}
	// with cache
	@GetMapping("/tutorials/{id}")
	public Tutorial getTutorialById(@PathVariable("id") String id) {
		return tutorialService.getTutorialByIdService(id);
	}
	@PostMapping("/tutorials")
	public Tutorial createTutorial(@RequestBody Tutorial tutorial) {
		return tutorialService.createTutorialService(tutorial);
	}

	// with cache
	@PutMapping("/tutorials/{id}")
	public Tutorial updateTutorial(@PathVariable("id") String id, @RequestBody Tutorial tutorial) {
		return tutorialService.updateTutorialService(id, tutorial);
	}

	// with cache
	@DeleteMapping("/tutorials/{id}")
	public List<Tutorial> deleteTutorial(@PathVariable("id") String id) {
		return tutorialService.deleteTutorialService(id);
	}

	// with cache
	@DeleteMapping("/tutorials")
	public void deleteAllTutorials() {
		tutorialService.deleteAllTutorialsService();
	}

	// with cache
	@GetMapping("/tutorials/published")
	public List<Tutorial> findByPublished() {
		return tutorialService.findByPublished();
	}
}