package com.jrp.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.pma.dto.ChartData;
import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.entities.Project;
import com.jrp.pma.services.EmployeeService;
import com.jrp.pma.services.ProjectService;

// accessed via http://localhost:8080/

@Controller
public class HomeController {

	@Value("${version}")
	private String ver;
	
	
	@Autowired
	ProjectService proService;
	
	@Autowired
	EmployeeService empService;
	
	
	//landing page for the application so we use the root url
	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {
		
		//passing the ver variable to the HTML code
		model.addAttribute("versionNumber", ver);
		
		List<Project> projects = proService.getAll();
		
		model.addAttribute("projectsList", projects);
		List<EmployeeProject> employeesProjectCnt = empService.employeeProjects();
		
		List<ChartData> projectData = proService.getProjectStatus();
		
		//converting projectData object into a JSON structure for use in JavaScript
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(projectData);
		
		model.addAttribute("projectStatusCnt",jsonString);
		
		model.addAttribute("employeesListProjectCnt", employeesProjectCnt);
		
		return "main/home";
		
		
	}
}
