package com.iquestgroup.advancedframeworks.ScrumTaskboard.controller;

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
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.SkillItem;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Task;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.service.MetaTagService;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.service.SkillItemService;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.service.TaskService;

@Controller
@SessionAttributes({"loginForm", "selectedTask"})
public class MetaTagController {

	@Autowired
	private MetaTagService metaTagService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private SkillItemService skillItemService;
	
	
	@RequestMapping(value = "/addTagSelectTask", method = RequestMethod.GET)
	public String showFormAddTagSelectTask(Map<String, Object> model) {
		model.put("tasksNotTaken", taskService.findAllFromPanel("not taken"));
		
		Task selectedTask = new Task();
		model.put("selectedTask", selectedTask);
		return "addTagSelectTask";
	}
	
	
	@RequestMapping(value = "/addTagSelectTask", method = RequestMethod.POST)
	public String processAddTagSelectTask(@ModelAttribute("selectedTask") Task selectedTask,BindingResult result, RedirectAttributes redirectAttributes,Map<String, List<MetaTag>> model) {
		selectedTask = (Task) model.get("selectedTask");
		selectedTask = taskService.findById(selectedTask.getId());
		redirectAttributes.addFlashAttribute("selectedTask", selectedTask);
		return "redirect:addTagSelectTag";
	}
	
	
	
	@RequestMapping(value = "/addTagSelectTag", method = RequestMethod.GET)
	public String showFormAddTagSelectTag(Map<String, Object> model) {
		model.put("metaTags", skillItemService.findAll());
		
		SkillItem selectedSkillItem = new SkillItem();
		model.put("selectedSkillItem", selectedSkillItem);
		return "addTagSelectTag";
	}
	
	
	@RequestMapping(value = "/addTagSelectTag", method = RequestMethod.POST)
	public String processAddTagForTask(@ModelAttribute("selectedSkillItem") SkillItem selectedSkillItem,BindingResult result, Map<String, List<MetaTag>> model) {
		Task selectedTask = (Task) model.get("selectedTask");
		selectedSkillItem = (SkillItem) model.get("selectedSkillItem");
		selectedSkillItem = skillItemService.findById(selectedSkillItem.getId());
		
		String selectedSkillName = selectedSkillItem.getSkillName();
		if (metaTagService.findByNameAndTask(selectedSkillName, selectedTask.getId()) == null) {
			MetaTag newMetatag = new MetaTag();
			List<MetaTag> existentTags = metaTagService.findAll();
			if (existentTags.size() > 0) {
				MetaTag lastInsertedTag = existentTags.get(existentTags.size() - 1);
				newMetatag.setId(lastInsertedTag.getId() + 1);
			} else {
				newMetatag.setId(1);
			}
				
			newMetatag.setMetaTagName(selectedSkillName);
			newMetatag.setTask(selectedTask);
			metaTagService.create(newMetatag);
		}
		model.put("metaTags", metaTagService.findAll());
		return "showTagsForAllTasks";
	}
	
	
	@RequestMapping(value = "/suggestDeveloperForTask", method = RequestMethod.GET)
	public String showFormSuggestDeveloperForTask(Map<String, Object> model) {
		model.put("tasksNotTaken", taskService.findAllFromPanel("not taken"));
		
		Task selectedTask = new Task();
		model.put("selectedTask", selectedTask);
		return "selectTaskToGetSuggestedDeveloper";
	}
	
	@RequestMapping(value = "/suggestDeveloperForTask", method = RequestMethod.POST)
	public String processSuggestDeveloperForTask(@ModelAttribute("selectedTask") Task selectedTask,BindingResult result, Map<String, Object> model) {
		selectedTask = (Task) model.get("selectedTask");
		selectedTask.setId(taskService.findByDescription(selectedTask.getDescription()).getId());
		
		Developer suggestedDeveloper = metaTagService.getSuggestedDeveloperForTask(selectedTask);
		model.put("suggestedDeveloper", suggestedDeveloper);
		
		return "suggestDeveloperForTask";
	}
}
