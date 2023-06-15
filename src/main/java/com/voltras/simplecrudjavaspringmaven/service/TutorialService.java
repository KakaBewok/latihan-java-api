package com.voltras.simplecrudjavaspringmaven.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.voltras.simplecrudjavaspringmaven.exception.ResourceNotFoundException;
import com.voltras.simplecrudjavaspringmaven.model.Tutorial;
import com.voltras.simplecrudjavaspringmaven.repository.TutorialDetailsRepository;
import com.voltras.simplecrudjavaspringmaven.repository.TutorialRepository;

@Service
public class TutorialService {
	@Autowired
	TutorialRepository tutorialRepository;
	
	@Autowired
	TutorialDetailsRepository tutorialDetailsRepository;

	@Cacheable("tutorials")
	public List<Tutorial> getAllTutorialsService(String title) {
		doLongRunningTask();

		List<Tutorial> tutorials = new ArrayList<Tutorial>();

		if (title == null)
			tutorialRepository.findAll().forEach(tutorials::add);
		else
			tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);

		if (tutorials.isEmpty()) {
			return null;
		}

		return tutorials;
	}

	@Cacheable("tutorial")
	public Tutorial getTutorialByIdService(long id) {
		doLongRunningTask();

		Tutorial tutorial = tutorialRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));

		return tutorial;
	}

	public Tutorial createTutorialService(Tutorial tutorial) {
		Tutorial _tutorial = tutorialRepository
				.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), tutorial.isPublished()));
		return _tutorial;
	}

	@CacheEvict(value = "tutorials", allEntries = true)
	public Tutorial updateTutorialService(long id, Tutorial tutorial) {
		Tutorial _tutorial = tutorialRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));

		_tutorial.setTitle(tutorial.getTitle());
		_tutorial.setDescription(tutorial.getDescription());
		_tutorial.setPublished(tutorial.isPublished());

		return tutorialRepository.save(_tutorial);
	}

	@CacheEvict(value = "tutorials", allEntries = true)
	public void deleteTutorialService(Long id) {
		if (tutorialDetailsRepository.existsById(id)) {
			tutorialDetailsRepository.deleteById(id);
		}

		tutorialRepository.deleteById(id);
	}

	@CacheEvict(value = { "tutorials", "tutorial", "tutorials_published", }, allEntries = true)
	public void deleteAllTutorialsService() {
		tutorialRepository.deleteAll();
	}

	@Cacheable("tutorials_published")
	public List<Tutorial> findByPublished() {
		doLongRunningTask();
		
		List<Tutorial> tutorials = tutorialRepository.findByPublished(true);

		if (tutorials.isEmpty()) {
			return null;
		}

		return tutorials;
	}

	private void doLongRunningTask() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}