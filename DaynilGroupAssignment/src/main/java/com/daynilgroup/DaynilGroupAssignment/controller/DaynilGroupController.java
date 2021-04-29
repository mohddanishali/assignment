package com.daynilgroup.DaynilGroupAssignment.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.daynilgroup.DaynilGroupAssignment.model.Entries;
import com.daynilgroup.DaynilGroupAssignment.model.EntryRoot;
import com.daynilgroup.DaynilGroupAssignment.repository.DaynilGroupRepository;

@RestController
public class DaynilGroupController {
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private DaynilGroupRepository daynilGroupRepository;

	
	@GetMapping("/saveAPIData")
	public ResponseEntity<EntryRoot> saveAPIData() {
		final String url = "https://api.publicapis.org/entries";

		ResponseEntity<EntryRoot> responseEntityEntityRoot = restTemplate.getForEntity(url, EntryRoot.class);

		List<Entries> entries = responseEntityEntityRoot.getBody().getEntries();

		entries.forEach(e -> {
			daynilGroupRepository.save(e);
		});

		return responseEntityEntityRoot;
	}
	 

	@GetMapping("/entries")
	public ResponseEntity<EntryRoot> getEntriesList() {
		List<Entries> entriesList = daynilGroupRepository.findAll();
		EntryRoot entryRoot = new EntryRoot();
		entryRoot.setCount(String.valueOf(entriesList.size()));
		entryRoot.setEntries(entriesList);

		if (entryRoot != null) {
			return new ResponseEntity<>(entryRoot, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping("/add")
	public ResponseEntity<Entries> saveEntries(@RequestBody Entries entries) {
		Entries newEntries = daynilGroupRepository.save(entries);
		if (newEntries != null) {
			return new ResponseEntity<>(daynilGroupRepository.save(newEntries), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Entries> updateEntries(@PathVariable("id") long id, @RequestBody Entries entries) {
		Optional<Entries> optionalEntries = daynilGroupRepository.findById(id);
		Entries newEntries = null;
		if (optionalEntries.isPresent()) {
			newEntries = optionalEntries.get();
			newEntries.setTitle(entries.getTitle());
			newEntries.setAuth(entries.getAuth());
			newEntries.setLink(entries.getLink());
			newEntries.setCategory(entries.getCategory());
			newEntries.setCors(entries.getCors());
			newEntries.setHttps(entries.getHttps());
			newEntries.setDescription(entries.getDescription());

			return new ResponseEntity<>(daynilGroupRepository.save(newEntries), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> deleteEntries(@PathVariable("id") long id) {
		try {
			daynilGroupRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/category")
	public ResponseEntity<List> getEntityByCategory() {
		List list = daynilGroupRepository.getEntityByCategory();
		if (!list.isEmpty() && list.size() > 0) {
			return new ResponseEntity<>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/getCategoryByAnimals")
	public Entries getCategoryByAnimals(@RequestParam("animals") String animals) {
		Entries entries = daynilGroupRepository.getEntityByAnimals(animals);

		System.out.println(entries);
		
		return entries;
	}
}
