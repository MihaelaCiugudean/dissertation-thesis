package com.iquestgroup.advancedframeworks.ScrumTaskboard.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Developer;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.User;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.service.UserService;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.service.impl.DeveloperServiceImplementation;

@Controller
public class DeveloperController {

	@Autowired
	private DeveloperServiceImplementation developerServiceImplementation;
	
	
	@Autowired
	private UserService userService;

	
	@RequestMapping(value = "/showDevelopers", method = RequestMethod.GET)
	public String developers(Map<String, Object> model) {
		model.put("developers", developerServiceImplementation.findAll());
		model.put("developerServiceImplementation", developerServiceImplementation);
		return "showDevelopers";
	}
	
	
	@RequestMapping(value = "/addDeveloper", method = RequestMethod.GET)
	public String showFormAdd(Map<String, Object> model) {
		Developer newDeveloper = new Developer();
		model.put("newDeveloper", newDeveloper);
		return "addDeveloper";
	}

 
	@RequestMapping(value = "/addDeveloper", method = RequestMethod.POST)
	public String processAdd(@ModelAttribute("newDeveloper") Developer newDeveloper ,BindingResult result, Map<String, List<Developer>> model) {
		newDeveloper = (Developer) model.get("newDeveloper");
		
		if(newDeveloper.getFirstName() != "" && newDeveloper.getLastName() != "" && newDeveloper.getLevel() != "") {
			List<User> existentUsers = userService.findAll();
			if(existentUsers.size() > 0) {
				User newUser = new User();
				userService.create(newUser);
				newDeveloper.setUser(newUser);
			}
			developerServiceImplementation.create(newDeveloper);
		}
		model.put("developers", developerServiceImplementation.findAll());
		return "showDevelopers";
	}
	
	
	@RequestMapping(value = "/deleteDeveloper", method = RequestMethod.GET)
	public String showFormDelete(Map<String, Object> model) {
		model.put("developers", developerServiceImplementation.findAll());
		Developer developerToDelete = new Developer();
		model.put("developerToDelete", developerToDelete);
		return "deleteDeveloper";
	}

    
	@RequestMapping(value = "/deleteDeveloper", method = RequestMethod.POST)
	public String processDelete(@ModelAttribute("developerToDelete") Developer developerToDelete,BindingResult result, Map<String, List<Developer>> model) {
		developerToDelete = (Developer) model.get("developerToDelete");
		developerServiceImplementation.delete(developerToDelete.getId());
		model.put("developers", developerServiceImplementation.findAll());

		return "showDevelopers";
	}
	
	
	public DeveloperServiceImplementation getDeveloperServiceImplementation() {
		return developerServiceImplementation;
	}

	public void setDeveloperServiceImplementation(DeveloperServiceImplementation developerService) {
		this.developerServiceImplementation = developerService;
	}
}
