package com.voltras.simplecrudjavaspringmaven.controller;

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
import com.voltras.simplecrudjavaspringmaven.model.TutorialDetails;
import com.voltras.simplecrudjavaspringmaven.service.TutorialDetailsService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class TutorialDetailsController {
	
	@Autowired
	private TutorialDetailsService tutorialDetailsService;

	//done
	@GetMapping({ "/details/{id}", "/tutorials/{id}/details" })
	public TutorialDetails getDetailsById(@PathVariable(value = "id") Long id) {
		return tutorialDetailsService.getDetailsByIdService(id);
	}

	//done
	@PostMapping("/tutorials/{tutorialId}/details")
	public TutorialDetails createDetails(@PathVariable(value = "tutorialId") Long tutorialId,
			@RequestBody TutorialDetails detailsRequest) {
		
		return tutorialDetailsService.createDetailsService(tutorialId, detailsRequest);
	}

	//done
	@PutMapping("/details/{id}")
	public TutorialDetails updateDetails(@PathVariable("id") Long id,
			@RequestBody TutorialDetails detailsRequest) {
		
		return tutorialDetailsService.updateDetailsService(id, detailsRequest);
	}

	//done
	@DeleteMapping("/details/{id}")
	public void deleteDetails(@PathVariable("id") Long id) {
		tutorialDetailsService.deleteDetailsService(id);
	}

	//done
	@DeleteMapping("/tutorials/{tutorialId}/details")
	public void deleteDetailsOfTutorial(
			@PathVariable(value = "tutorialId") Long tutorialId) {
		
		tutorialDetailsService.deleteDetailsOfTutorialService(tutorialId);
	}
}