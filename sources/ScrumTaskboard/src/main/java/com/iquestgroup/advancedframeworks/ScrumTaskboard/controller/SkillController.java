package com.iquestgroup.advancedframeworks.ScrumTaskboard.controller;

import java.util.ArrayList;
import java.util.List;
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
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.MetaTag;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Skill;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.SkillItem;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.SkillUpgrades;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Task;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.service.DeveloperService;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.service.MetaTagService;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.service.SkillItemService;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.service.SkillService;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.service.TaskService;

@Controller
@SessionAttributes({"loginForm","task","selectedDeveloper"})
public class SkillController {

	@Autowired
	private SkillService skillService;
	
	@Autowired
	private DeveloperService developerService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private MetaTagService metaTagService;
	
	@Autowired
	private SkillItemService skillItemService;

	
	@RequestMapping(value = "/addSkillSelectDeveloper", method = RequestMethod.GET)
	public String showFormAddSkillSelectDeveloper(Map<String, Object> model) {
		model.put("developers", developerService.findAll());
		
		Developer selectedDeveloper = new Developer();
		model.put("selectedDeveloper", selectedDeveloper);
		
		return "addSkillSelectDeveloper";
	}
	
	
	@RequestMapping(value = "/addSkillSelectDeveloper", method = RequestMethod.POST)
	public String processAddSkillSelectDeveloper(@ModelAttribute("selectedDeveloper") Developer selectedDeveloper, BindingResult result, RedirectAttributes redirectAttributes, Map<String, List<?>> model) {
		selectedDeveloper = (Developer) model.get("selectedDeveloper");
		selectedDeveloper = developerService.findById(selectedDeveloper.getId());
		redirectAttributes.addFlashAttribute("selectedDeveloper", selectedDeveloper);
		return "redirect:addSkillSelectSkill";
	}
	
	
	@RequestMapping(value = "/addSkillSelectSkill", method = RequestMethod.GET)
	public String showFormAddSkillSelectSkill(Map<String, Object> model) {
		model.put("skillItems", skillItemService.findAll());
		
		SkillItem selectedSkillItem = new SkillItem();
		model.put("selectedSkillItem", selectedSkillItem);
		return "addSkillSelectSkill";
	}
	
	
	@RequestMapping(value = "/addSkillSelectSkill", method = RequestMethod.POST)
	public String processAddSkillSelectSkill(@ModelAttribute("selectedSkillItem") SkillItem selectedSkillItem ,BindingResult result, Map<String, List<?>> model) {
		Developer selectedDeveloper = (Developer) model.get("selectedDeveloper");
		int selectedDeveloperId = selectedDeveloper.getId();
		
		selectedSkillItem = (SkillItem) model.get("selectedSkillItem");
		float percentage = selectedSkillItem.getPercentage();
		selectedSkillItem = skillItemService.findById(selectedSkillItem.getId());
		selectedSkillItem.setPercentage(percentage);
		String selectedSkillItemName = selectedSkillItem.getSkillName();
		
		if (skillService.findByNameAndDeveloper(selectedSkillItemName, selectedDeveloperId) != null) {
			Skill foundedSkill = skillService.findByNameAndDeveloper(selectedSkillItemName, selectedDeveloperId);
			if (percentage >= foundedSkill.getPercentage()) {
				foundedSkill.setPercentage(percentage);
				skillService.update(foundedSkill);
			}
		} else {
			Skill newSkill = new Skill();
			List<Skill> existentSkills = skillService.findAll();
			if (existentSkills.size() > 0) {
				Skill lastInsertedSkill = existentSkills.get(existentSkills.size() - 1);
				newSkill.setId(lastInsertedSkill.getId() + 1);
			} else {
				newSkill.setId(1);
			}
			newSkill.setSkillName(selectedSkillItemName);
			newSkill.setPercentage(percentage);
			newSkill.setDeveloper(selectedDeveloper);
			skillService.create(newSkill);
		}
		model.put("skills", skillService.findAll());
		return "showSkillsForAllDevelopers";
	}
	
	
	
	@RequestMapping(value = "/showSkillUpgrades", method = RequestMethod.GET)
	public String showFormShowSkillUpgrades(Map<String, Object> model) {
		List<SkillUpgrades> skillUpgradesForTasksInDone = new ArrayList<SkillUpgrades>();
		List<Task> tasksInDone = taskService.findAllFromPanel("in done");
		
		for(int i=0;i<tasksInDone.size();i++) {
			SkillUpgrades skillUpgrades = skillService.suggestSkillsUpgradeForDeveloper(tasksInDone.get(i));
			skillUpgradesForTasksInDone.add(skillUpgrades);
		}
		model.put("skillUpgradesForTasksInDone", skillUpgradesForTasksInDone);
		model.put("tasksInDone", tasksInDone);
		
		return "showSkillUpgrades";
	}
	
	
	@RequestMapping(value = "/showSkillUpgrades", method = RequestMethod.POST)
	public String processShowSkillUpgrades(Map<?, ?> model) {
		return "showSkillUpgrades";
	}
	
	
	
	@RequestMapping(value = "/upgradeSkillSelectTask", method = RequestMethod.GET)
	public String showFormUpgradeSkillSelectTask(Map<String, Object> model) {
		List<SkillUpgrades> skillUpgradesForTasksInDone = new ArrayList<SkillUpgrades>();
		List<Task> tasksInDone = taskService.findAllFromPanel("in done");
		
		for(int i=0;i<tasksInDone.size();i++) {
			SkillUpgrades skillUpgrades = skillService.suggestSkillsUpgradeForDeveloper(tasksInDone.get(i));
			skillUpgradesForTasksInDone.add(skillUpgrades);
		}
		model.put("skillUpgradesForTasksInDone", skillUpgradesForTasksInDone);
		model.put("tasksInDone", tasksInDone);
		
		Task task = new Task();
		model.put("task", task);
		
		return "upgradeSkillSelectTask";
	}
	
	
	@RequestMapping(value = "/upgradeSkillSelectTask", method = RequestMethod.POST)
	public String processUpgradeSkillSelectTask(@ModelAttribute("task") Task task, BindingResult result, RedirectAttributes redirectAttributes, Map<String, List<MetaTag>> model) {
		task = (Task) model.get("task");
		task = taskService.findById(task.getId());
		
		List<MetaTag> taskRequiredSkills = task.getMetaTags();
		redirectAttributes.addFlashAttribute("taskRequiredSkills", taskRequiredSkills);
		redirectAttributes.addFlashAttribute("task", task);
		return "redirect:upgradeSkillSelectSkill";
	}
	
	
	
	@RequestMapping(value = "/upgradeSkillSelectSkill", method = RequestMethod.GET)
	public String showFormUpgradeSkillSelectSkill(Map<String, Object> model) {
		List<SkillUpgrades> skillUpgradesForTasksInDone = new ArrayList<SkillUpgrades>();
		List<Task> tasksInDone = taskService.findAllFromPanel("in done");
		
		for(int i=0;i<tasksInDone.size();i++) {
			SkillUpgrades skillUpgrades = skillService.suggestSkillsUpgradeForDeveloper(tasksInDone.get(i));
			skillUpgradesForTasksInDone.add(skillUpgrades);
		}
		model.put("skillUpgradesForTasksInDone", skillUpgradesForTasksInDone);
		
		MetaTag metaTag = new MetaTag();
		model.put("metaTag", metaTag);
		return "upgradeSkillSelectSkill";
	}
	
	
	@RequestMapping(value = "/upgradeSkillSelectSkill", method = RequestMethod.POST)
	public String processUpgradeSkillSelectSkill(@ModelAttribute("metaTag") MetaTag metaTag, BindingResult result,Map<String, List<SkillUpgrades>> model) {
		Task task = (Task) model.get("task");
		task = taskService.findById(task.getId());
		Developer developer = task.getDeveloper();
		int developerId = developer.getId();
		metaTag = (MetaTag) model.get("metaTag");
		metaTag = metaTagService.findById(metaTag.getId());
		String metaTagName = metaTag.getMetaTagName();
		
		Skill skill;
		if(skillService.findByNameAndDeveloper(metaTagName, developerId) != null) {
			skill = skillService.findByNameAndDeveloper(metaTagName, developerId);
		}
		else{
			skill = new Skill();
			List<Skill> existentSkills = skillService.findAll();
			if(existentSkills.size() >0) {
				Skill lastInsertedSkill = existentSkills.get(existentSkills.size() - 1);
				skill.setId(lastInsertedSkill.getId() + 1);
			}
			
			else skill.setId(1);
			skill.setSkillName(metaTagName);
			skill.setPercentage(0);
			skill.setDeveloper(developer);
			skillService.create(skill);
		}
		
		SkillUpgrades skillUpgradesForSelectedTask = skillService.suggestSkillsUpgradeForDeveloper(task);
		float upgradedPercentage = skillUpgradesForSelectedTask.getUpgrades().get(metaTagName).getUpgradedPercentage();
		skill.setPercentage(upgradedPercentage);
		skillService.update(skill);
		
		List<SkillUpgrades> skillUpgradesForTasksInDone = new ArrayList<SkillUpgrades>();
		List<Task> tasksInDone = taskService.findAllFromPanel("in done");
	    
		for(int i=0;i<tasksInDone.size();i++) {
			if(tasksInDone.get(i).getDeveloper().getId() == developerId) {
				if(metaTagService.findByNameAndTask(metaTagName, tasksInDone.get(i).getId()) != null) {
				    metaTag = metaTagService.findByNameAndTask(metaTagName, tasksInDone.get(i).getId());
				    metaTagService.delete(metaTag.getId());
				}
			}
		}
		
		tasksInDone = taskService.findAllFromPanel("in done");
		for(int i=0;i<tasksInDone.size();i++) {
			SkillUpgrades skillUpgrades = skillService.suggestSkillsUpgradeForDeveloper(tasksInDone.get(i));
			skillUpgradesForTasksInDone.add(skillUpgrades);
		}
		model.put("skillUpgradesForTasksInDone", skillUpgradesForTasksInDone);
		
		return "showSkillUpgrades";
	}
}
