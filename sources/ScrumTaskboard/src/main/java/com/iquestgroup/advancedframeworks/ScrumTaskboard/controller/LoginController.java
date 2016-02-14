package com.iquestgroup.advancedframeworks.ScrumTaskboard.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Developer;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.User;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.service.DeveloperService;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.service.UserService;

@Controller
@SessionAttributes({"loginForm","registerForm"})
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private DeveloperService developerService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showFormLogin(Map<String, Object> model) {
		User loginForm = new User();
		model.put("loginForm", loginForm);
		return "login";
	}

    
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String processLogin(@ModelAttribute("loginForm") User loginForm,BindingResult result, Map<String, Object> model) {
		loginForm = (User) model.get("loginForm");

		User foundUser = userService.findByUsernameAndPassword(loginForm.getUsername(), loginForm.getPassword());

		if (foundUser == null) {
			return "login";
		}

		model.put("loginForm", loginForm);

		if (foundUser.getPosition().equals("developer")) {
			return "loginDeveloper";
		}
		return "loginScrumMaster";
	}


	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showFormRegister(Map<String, Object> model) {
		User registerForm = new User();
		model.put("registerForm", registerForm);
		return "register";
	}

    
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String processRegister(@ModelAttribute("registerForm") User registerForm,BindingResult result, RedirectAttributes redirectAttributes, Map<String, Object> model) {
		registerForm = (User) model.get("registerForm");
		userService.registerUser(registerForm);
		
		redirectAttributes.addFlashAttribute("registerForm", registerForm);
		return "redirect:setAccountInformation";
	}
	
	
	@RequestMapping(value = "/setAccountInformation", method = RequestMethod.GET)
	public String showFormSetAccountInformation(Map<String, Object> model) {
		Developer developer = new Developer();
		model.put("developer", developer);
		return "setAccountInformation";
	}

    
	@RequestMapping(value = "/setAccountInformation", method = RequestMethod.POST)
	public String processSetAccountInformation(@ModelAttribute("developer") Developer developer,BindingResult result, Map<String, Object> model) {
		developer = (Developer) model.get("developer");
		User user = (User) model.get("registerForm");
		developer.setUser(user);
		developerService.create(developer);
		
		model.put("loginForm", user);
		return "loginDeveloper";
	}
	
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}