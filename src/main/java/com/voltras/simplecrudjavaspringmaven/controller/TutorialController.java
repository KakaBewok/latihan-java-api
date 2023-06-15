package com.voltras.simplecrudjavaspringmaven.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.voltras.simplecrudjavaspringmaven.exception.ResourceNotFoundException;
import com.voltras.simplecrudjavaspringmaven.model.Tutorial;
import com.voltras.simplecrudjavaspringmaven.repository.TutorialDetailsRepository;
import com.voltras.simplecrudjavaspringmaven.repository.TutorialRepository;
import com.voltras.simplecrudjavaspringmaven.service.TutorialService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class TutorialController {
	@Autowired
	TutorialService tutorialService;

	//done
	@GetMapping("/tutorials")
	public List<Tutorial> getAllTutorials(@RequestParam(required = false) String title) {
		return tutorialService.getAllTutorialsService(title);
	}
	//done
	@GetMapping("/tutorials/{id}")
	public Tutorial getTutorialById(@PathVariable("id") long id) {
		return tutorialService.getTutorialByIdService(id);
	}
	//done
	@PostMapping("/tutorials")
	public Tutorial createTutorial(@RequestBody Tutorial tutorial) {
		return tutorialService.createTutorialService(tutorial);
	}
	//done
	@PutMapping("/tutorials/{id}")
	public Tutorial updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
		return tutorialService.updateTutorialService(id, tutorial);
	}
	//done
	@DeleteMapping("/tutorials/{id}")
	public void deleteTutorial(@PathVariable("id") Long id) {
		tutorialService.deleteTutorialService(id);
	}
	//done
	@DeleteMapping("/tutorials")
	public void deleteAllTutorials() {
		tutorialService.deleteAllTutorialsService();
	}
	//done
	@GetMapping("/tutorials/published")
	public List<Tutorial> findByPublished() {
		return tutorialService.findByPublished();
	}
}