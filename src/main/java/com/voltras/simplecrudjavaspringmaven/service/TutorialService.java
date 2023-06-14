//package com.voltras.simplecrudjavaspringmaven.service;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import com.voltras.simplecrudjavaspringmaven.model.Tutorial;
//import com.voltras.simplecrudjavaspringmaven.repository.TutorialRepository;
//
//@Service
//public class TutorialService {
//	@Autowired
//	TutorialRepository tutorialRepository;
//
//	@Cacheable("tutorials")
//	public Map<String, Object> getAllTutorialsService(String title, int page, int size) {
//		doLongRunningTask();
//
//		Map<String, Object> response = new HashMap<>();
//
//		try {
//			List<Tutorial> tutorials = new ArrayList<Tutorial>();
//
//			Pageable paging = PageRequest.of(page, size);
//
//			Page<Tutorial> pageTuts;
//
//			if (title == null)
//				pageTuts = tutorialRepository.findAll(paging);
//			else
//				pageTuts = tutorialRepository.findByTitleContainingIgnoreCase(title, paging);
//
//			tutorials = pageTuts.getContent();
//
//			response.put("tutorials", tutorials);
//			response.put("currentPage", pageTuts.getNumber());
//			response.put("totalItems", pageTuts.getTotalElements());
//			response.put("totalPages", pageTuts.getTotalPages());
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return response;
//	}
//
//	@Cacheable("tutorial")
//	public Tutorial getTutorialByIdService(String id) {
//		doLongRunningTask();
//
//		Optional<Tutorial> tutorial = tutorialRepository.findById(id);
//
//		if (tutorial.isPresent()) {
//			return tutorial.get();
//		} else {
//			return null;
//		}
//	}
//
//	public Tutorial createTutorialService(Tutorial tutorial) {
//		Tutorial _tutorial = tutorialRepository
//				.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), tutorial.isPublished()));
//		return _tutorial;
//	}
//
//	@CacheEvict(value = "tutorials", allEntries = true)
//	public Tutorial updateTutorialService(String id, Tutorial tutorial) {
//		Optional<Tutorial> tutorialData = tutorialRepository.findById(id);
//
//		if (tutorialData.isPresent()) {
//			Tutorial _tutorial = tutorialData.get();
//			_tutorial.setTitle(tutorial.getTitle());
//			_tutorial.setDescription(tutorial.getDescription());
//			_tutorial.setPublished(tutorial.isPublished());
//			return tutorialRepository.save(_tutorial);
//		} else {
//			return null;
//		}
//	}
//
//	@CacheEvict(value = "tutorials", allEntries = true)
//	public List<Tutorial> deleteTutorialService(String id) {
//		tutorialRepository.deleteById(id);
//
//		List<Tutorial> tutorials = new ArrayList<Tutorial>();
//
//		tutorialRepository.findAll().forEach(tutorials::add);
//
//		return tutorials;
//	}
//
//	@CacheEvict(value = { "tutorials", "tutorial", "tutorials_published", }, allEntries = true)
//	public void deleteAllTutorialsService() {
//		tutorialRepository.deleteAll();
//	}
//
//	@Cacheable("tutorials_published")
//	public Map<String, Object> findByPublished(int page, int size) {
//		doLongRunningTask();
//		
//		Map<String, Object> response = new HashMap<>();
//
//		try {
//			List<Tutorial> tutorials = new ArrayList<Tutorial>();
//			Pageable paging = PageRequest.of(page, size);
//
//			Page<Tutorial> pageTuts = tutorialRepository.findByPublished(true, paging);
//			tutorials = pageTuts.getContent();
//
//			if (tutorials.isEmpty()) {
//				return null;
//			}
//
//			response.put("tutorials", tutorials);
//			response.put("currentPage", pageTuts.getNumber());
//			response.put("totalItems", pageTuts.getTotalElements());
//			response.put("totalPages", pageTuts.getTotalPages());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return response;
//	}
//
//	private void doLongRunningTask() {
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}
//}