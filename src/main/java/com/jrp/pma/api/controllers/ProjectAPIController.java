package com.jrp.pma.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;

@RestController
@RequestMapping("/app-api/projects")
public class ProjectAPIController {
	
	@Autowired
	ProjectRepository proRepo;
	
	@GetMapping
	public Iterable<Project> getEmployees() {
		return proRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Project getProjectById(@PathVariable("id") Long id) {
		return proRepo.findById(id).get();
	}
	
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Project create(@RequestBody @Valid Project project) {
		return proRepo.save(project);
	}
	
	@PutMapping(path="/{id}", consumes="applicatoni/json")
	@ResponseStatus(HttpStatus.OK)
	public Project update(@RequestBody Project project) {
		return proRepo.save(project);
	}
	
	@PatchMapping(path="/{id}",consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Project partialUpdate(@PathVariable("id") Long id, @RequestBody Project patchProject) {
		Project pro = proRepo.findById(id).get();
		
		if(patchProject.getDescription() != null) {
			pro.setDescription(patchProject.getDescription());
		}
		
		if(patchProject.getName() != null) {
			pro.setName(patchProject.getName());
		}
		
		if(patchProject.getStage() != null) {
			pro.setStage(patchProject.getStage());
		}
		
		return proRepo.save(pro);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") long id) {
		try {
			proRepo.deleteById(id);
		} catch(EmptyResultDataAccessException e) {
			
		}
	}
	
	@GetMapping(params = {"page","size"})
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Project> findPaginatedProjects(@RequestParam("page") int page, @RequestParam("size") int size) {
		Pageable pageAndSize = PageRequest.of(page, size);
		return proRepo.findAll(pageAndSize);	
	}
}
