package com.voltras.simplecrudjavaspringmaven.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voltras.simplecrudjavaspringmaven.exception.ResourceNotFoundException;
import com.voltras.simplecrudjavaspringmaven.model.Tutorial;
import com.voltras.simplecrudjavaspringmaven.model.TutorialDetails;
import com.voltras.simplecrudjavaspringmaven.repository.TutorialDetailsRepository;
import com.voltras.simplecrudjavaspringmaven.repository.TutorialRepository;

@Service
public class TutorialDetailsService {
	@Autowired
	private TutorialDetailsRepository detailsRepository;

	@Autowired
	private TutorialRepository tutorialRepository;

	public TutorialDetails getDetailsByIdService(Long id) {
		TutorialDetails details = detailsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial Details with id = " + id));

		return details;
	}

	public TutorialDetails createDetailsService(Long tutorialId, TutorialDetails detailsRequest) {

		Tutorial tutorial = tutorialRepository.findById(tutorialId)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId));

		detailsRequest.setCreatedOn(new java.util.Date());
		detailsRequest.setTutorial(tutorial);
		TutorialDetails details = detailsRepository.save(detailsRequest);

		return details;
	}

	public TutorialDetails updateDetailsService(Long id, TutorialDetails detailsRequest) {

		TutorialDetails details = detailsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Id " + id + " not found"));

		details.setCreatedBy(detailsRequest.getCreatedBy());

		detailsRepository.save(details);

		return details;
	}

	public void deleteDetailsService(Long id) {
		detailsRepository.deleteById(id);
	}

	public void deleteDetailsOfTutorialService(Long tutorialId) {
		if (!tutorialRepository.existsById(tutorialId)) {
			throw new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId);
		}

		detailsRepository.deleteByTutorialId(tutorialId);
	}
}
