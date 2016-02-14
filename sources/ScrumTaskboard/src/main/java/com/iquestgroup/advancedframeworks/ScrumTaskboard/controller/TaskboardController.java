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
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.HistoryItem;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Task;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.User;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.service.MetaTagService;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.service.TaskService;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.service.UserService;

@Controller
@SessionAttributes({"loginForm","sprintDay"})
public class TaskboardController {

	@Autowired
	private TaskService taskService;

	@Autowired
	private UserService userService;
   
	@Autowired
	private MetaTagService metaTagService;
	
	
	@RequestMapping(value = "/showTaskboardForDeveloper", method = RequestMethod.GET)
	public String showTaskboardForDeveloper(Map<String, List<Task>> model) {
		model.put("tasksNotTaken", getTasksNotTakenAndSuggestedDeveloper());
		model.put("tasksTaken", taskService.findAllFromPanel("taken"));
		model.put("tasksInProgress", taskService.findAllFromPanel("in progress"));
		model.put("tasksInReview", taskService.findAllFromPanel("in review"));
		model.put("tasksInDone", taskService.findAllFromPanel("in done"));
		return "showTaskboardForDeveloper";
	}

	
	@RequestMapping(value = "/showTaskboardForScrumMaster", method = RequestMethod.GET)
	public String showTaskboardForScrumMaster(Map<String, List<Task>> model) {
		ArrayList<Task> tasksNotTaken =  (ArrayList<Task>) taskService.findAllFromPanel("not taken");
		for(int i=0;i<tasksNotTaken.size();i++) {
			Developer developer = metaTagService.getSuggestedDeveloperForTask(tasksNotTaken.get(i));
			tasksNotTaken.get(i).setDeveloper(developer);
		}
		
		model.put("tasksNotTaken", getTasksNotTakenAndSuggestedDeveloper());
		model.put("tasksTaken", taskService.findAllFromPanel("taken"));
		model.put("tasksInProgress", taskService.findAllFromPanel("in progress"));
		model.put("tasksInReview", taskService.findAllFromPanel("in review"));
		model.put("tasksInDone", taskService.findAllFromPanel("in done"));
		return "showTaskboardForScrumMaster";
	}

	
	@RequestMapping(value = "/addTask", method = RequestMethod.GET)
	public String showFormAdd(Map<String, Object> model) {
		Task newTask = new Task();
		model.put("newTask", newTask);
		return "addTask";
	}

    
	@RequestMapping(value = "/addTask", method = RequestMethod.POST)
	public String processAdd(@ModelAttribute("newTask") Task newTask,BindingResult result, Map<String, List<Task>> model) {
		newTask = (Task) model.get("newTask");
		newTask.setStatus("not taken");
		taskService.create(newTask);
		
		model.put("tasksNotTaken", taskService.findAllFromPanel("not taken"));
		model.put("tasksTaken", taskService.findAllFromPanel("taken"));
		model.put("tasksInProgress", taskService.findAllFromPanel("in progress"));
		model.put("tasksInReview", taskService.findAllFromPanel("in review"));
		model.put("tasksInDone", taskService.findAllFromPanel("in done"));

		return "showTaskboardForScrumMaster";
	}
	
	
	@RequestMapping(value = "/deleteTask", method = RequestMethod.GET)
	public String showFormDelete(Map<String, Object> model) {
		model.put("tasksNotTaken", taskService.findAllFromPanel("not taken"));
		
		Task taskToDelete = new Task();
		model.put("taskToDelete", taskToDelete);
		return "deleteTask";
	}

    
	@RequestMapping(value = "/deleteTask", method = RequestMethod.POST)
	public String processDelete(@ModelAttribute("taskToDelete") Task taskToDelete,BindingResult result, Map<String, List<Task>> model) {
		taskToDelete = (Task) model.get("taskToDelete");
		
		taskService.delete(taskToDelete.getId());
		
		model.put("tasksNotTaken", taskService.findAllFromPanel("not taken"));
		model.put("tasksTaken", taskService.findAllFromPanel("taken"));
		model.put("tasksInProgress", taskService.findAllFromPanel("in progress"));
		model.put("tasksInReview", taskService.findAllFromPanel("in review"));
		model.put("tasksInDone", taskService.findAllFromPanel("in done"));

		return "showTaskboardForScrumMaster";
	}
	
	@RequestMapping(value = "/editTask", method = RequestMethod.GET)
	public String showFormEdit(Map<String, Object> model) {
		Task taskToEdit = new Task();
		model.put("taskToEdit", taskToEdit);
		return "editTask";
	}

    
	@RequestMapping(value = "/editTask", method = RequestMethod.POST)
	public String processEdit(@ModelAttribute("taskToEdit") Task taskToEdit,BindingResult result, Map<String, List<Task>> model) {
		taskToEdit = (Task) model.get("taskToEdit");
		Task foundedTask = taskService.findByDescription(taskToEdit.getDescription());
		foundedTask.setNrHours(taskToEdit.getNrHours());
		foundedTask.setPriority(taskToEdit.getPriority());
		foundedTask.setDifficulty(taskToEdit.getDifficulty());
		taskService.update(foundedTask);
		
		model.put("tasksNotTaken", taskService.findAllFromPanel("not taken"));
		model.put("tasksTaken", taskService.findAllFromPanel("taken"));
		model.put("tasksInProgress", taskService.findAllFromPanel("in progress"));
		model.put("tasksInReview", taskService.findAllFromPanel("in review"));
		model.put("tasksInDone", taskService.findAllFromPanel("in done"));

		return "showTaskboardForScrumMaster";
	}
	
	
	@RequestMapping(value = "/moveTaskFromTakenToInProgress", method = RequestMethod.GET)
	public String showFormMoveFromTakenToInProgress(Map<String, Object> model, @ModelAttribute("loginForm") User loginForm) {
		model.put("tasksNotTaken", getTasksNotTakenAndSuggestedDeveloper());
		model.put("tasksTaken", taskService.findAllFromPanel("taken"));
		model.put("tasksInProgress", taskService.findAllFromPanel("in progress"));
		model.put("tasksInReview", taskService.findAllFromPanel("in review"));
		model.put("tasksInDone", taskService.findAllFromPanel("in done"));
		
		User foundUser = userService.findByUsernameAndPassword(loginForm.getUsername(), loginForm.getPassword());
		model.put("tasksTakenByDeveloper", taskService.findAllFromPanelForDeveloper("taken", foundUser.getId()));
		
		Task taskToMove = new Task();
		model.put("taskToMove", taskToMove);
		return "moveTaskFromTakenToInProgress";
	}

    
	@RequestMapping(value = "/moveTaskFromTakenToInProgress", method = RequestMethod.POST)
	public String processMoveFromTakenToInProgress(@ModelAttribute("taskToMove") Task taskToMove,BindingResult result, Map<String, List<Task>> model) {
		taskToMove = (Task) model.get("taskToMove");
		
		taskService.moveTaskToPanel(taskToMove,"in progress");
		
		model.put("tasksNotTaken", getTasksNotTakenAndSuggestedDeveloper());
		model.put("tasksTaken", taskService.findAllFromPanel("taken"));
		model.put("tasksInProgress", taskService.findAllFromPanel("in progress"));
		model.put("tasksInReview", taskService.findAllFromPanel("in review"));
		model.put("tasksInDone", taskService.findAllFromPanel("in done"));
		return "showTaskboardForDeveloper";
	}
	
	
	@RequestMapping(value = "/moveTaskFromInProgressToInReview", method = RequestMethod.GET)
	public String showFormMoveFromInProgressToInReview(Map<String, Object> model, @ModelAttribute("loginForm") User loginForm) {
		model.put("tasksNotTaken", getTasksNotTakenAndSuggestedDeveloper());
		model.put("tasksTaken", taskService.findAllFromPanel("taken"));
		model.put("tasksInProgress", taskService.findAllFromPanel("in progress"));
		model.put("tasksInReview", taskService.findAllFromPanel("in review"));
		model.put("tasksInDone", taskService.findAllFromPanel("in done"));
		
		User foundUser = userService.findByUsernameAndPassword(loginForm.getUsername(), loginForm.getPassword());
		model.put("tasksInProgressForDeveloper", taskService.findAllFromPanelForDeveloper("in progress",foundUser.getId()));
		
		Task taskToMove = new Task();
		model.put("taskToMove", taskToMove);
		return "moveTaskFromInProgressToInReview";
	}

   
	@RequestMapping(value = "/moveTaskFromInProgressToInReview", method = RequestMethod.POST)
	public String processMoveFromInProgressToInReview(@ModelAttribute("taskToMove") Task taskToMove,BindingResult result, Map<String, List<Task>> model) {
		taskToMove = (Task) model.get("taskToMove");
		
		taskService.moveTaskToPanel(taskToMove,"in review");
		
		model.put("tasksNotTaken", getTasksNotTakenAndSuggestedDeveloper());
		model.put("tasksTaken", taskService.findAllFromPanel("taken"));
		model.put("tasksInProgress", taskService.findAllFromPanel("in progress"));
		model.put("tasksInReview", taskService.findAllFromPanel("in review"));
		model.put("tasksInDone", taskService.findAllFromPanel("in done"));
		return "showTaskboardForDeveloper";
	}
	
	
	@RequestMapping(value = "/setSprintDay", method = RequestMethod.GET)
	public String showSprintDay(Map<String, Object> model) {
		HistoryItem historyItem = new HistoryItem();
		historyItem.setDayNumber(0);
		model.put("historyItem", historyItem);
		return "setSprintDay";
	}

   
	@RequestMapping(value = "/setSprintDay", method = RequestMethod.POST)
	public String processSetSprintDay(@ModelAttribute("historyItem") HistoryItem historyItem,BindingResult result,RedirectAttributes redirectAttributes, Map<String, Object> model) {
		historyItem = (HistoryItem) model.get("historyItem");
		redirectAttributes.addFlashAttribute("historyItem", historyItem);
		return "redirect:showBurndownChart";
	}
	
	
	@RequestMapping(value = "/moveTaskFromInReviewToDone", method = RequestMethod.GET)
	public String showFormMoveFromInReviewToDone(Map<String, Object> model) {
		model.put("tasksNotTaken", getTasksNotTakenAndSuggestedDeveloper());
		model.put("tasksTaken", taskService.findAllFromPanel("taken"));
		model.put("tasksInProgress", taskService.findAllFromPanel("in progress"));
		model.put("tasksInReview", taskService.findAllFromPanel("in review"));
		model.put("tasksInDone", taskService.findAllFromPanel("in done"));
		
		Task taskToMove = new Task();
		model.put("taskToMove", taskToMove);
		return "moveTaskFromInReviewToDone";
	}

  
	@RequestMapping(value = "/moveTaskFromInReviewToDone", method = RequestMethod.POST)
	public String processMoveFromInReviewToDone(@ModelAttribute("taskToMove") Task taskToMove, @ModelAttribute("historyItem") HistoryItem historyItem,BindingResult result,RedirectAttributes redirectAttributes, Map<String, Object> model) {
		taskToMove = (Task) model.get("taskToMove");
		
		taskToMove = taskService.findById(taskToMove.getId());
		model.put("taskToMove", taskToMove);
		
		taskService.moveTaskToPanel(taskToMove,"in done");
		
		model.put("tasksNotTaken", getTasksNotTakenAndSuggestedDeveloper());
		model.put("tasksTaken", taskService.findAllFromPanel("taken"));
		model.put("tasksInProgress", taskService.findAllFromPanel("in progress"));
		model.put("tasksInReview", taskService.findAllFromPanel("in review"));
		model.put("tasksInDone", taskService.findAllFromPanel("in done"));
		
		return "setSprintDay";
	}
	
	
	@RequestMapping(value = "/moveTaskFromNotTakenToTaken", method = RequestMethod.GET)
	public String showFormMoveFromNotTakenToTaken(Map<String, Object> model) {
		model.put("tasksNotTaken", getTasksNotTakenAndSuggestedDeveloper());
		model.put("tasksTaken", taskService.findAllFromPanel("taken"));
		model.put("tasksInProgress", taskService.findAllFromPanel("in progress"));
		model.put("tasksInReview", taskService.findAllFromPanel("in review"));
		model.put("tasksInDone", taskService.findAllFromPanel("in done"));
		
		Task taskToMove = new Task();
		model.put("taskToMove", taskToMove);
		return "moveTaskFromNotTakenToTaken";
	}

   
	@RequestMapping(value = "/moveTaskFromNotTakenToTaken", method = RequestMethod.POST)
	public String processMoveFromNotTakenToTaken(@ModelAttribute("taskToMove") Task taskToMove, @ModelAttribute("loginForm") User loginForm,BindingResult result, Map<String, List<Task>> model) {
		loginForm = (User) model.get("loginForm");
		taskToMove = (Task) model.get("taskToMove");
		
		User foundUser = userService.findByUsernameAndPassword(loginForm.getUsername(), loginForm.getPassword());
	
		taskService.moveTaskToTaken(taskToMove, foundUser.getId());
	
		model.put("tasksNotTaken", getTasksNotTakenAndSuggestedDeveloper());
		model.put("tasksTaken", taskService.findAllFromPanel("taken"));
		model.put("tasksInProgress", taskService.findAllFromPanel("in progress"));
		model.put("tasksInReview", taskService.findAllFromPanel("in review"));
		model.put("tasksInDone", taskService.findAllFromPanel("in done"));
		return "showTaskboardForDeveloper";
	}
	
	
	@RequestMapping(value = "/moveTaskFromTakenToNotTaken", method = RequestMethod.GET)
	public String showFormMoveFromTakenToNotTaken(Map<String, Object> model, @ModelAttribute("loginForm") User loginForm) {
		model.put("tasksNotTaken", getTasksNotTakenAndSuggestedDeveloper());
		model.put("tasksTaken", taskService.findAllFromPanel("taken"));
		model.put("tasksInProgress", taskService.findAllFromPanel("in progress"));
		model.put("tasksInReview", taskService.findAllFromPanel("in review"));
		model.put("tasksInDone", taskService.findAllFromPanel("in done"));
		
		User foundUser = userService.findByUsernameAndPassword(loginForm.getUsername(), loginForm.getPassword());
		model.put("tasksTakenByDeveloper", taskService.findAllFromPanelForDeveloper("taken",foundUser.getId()));
		
		Task taskToMove = new Task();
		model.put("taskToMove", taskToMove);
		return "moveTaskFromTakenToNotTaken";
	}

    
	@RequestMapping(value = "/moveTaskFromTakenToNotTaken", method = RequestMethod.POST)
	public String processMoveFromTakenToNotTaken(@ModelAttribute("taskToMove") Task taskToMove,BindingResult result, Map<String, List<Task>> model) {
		taskToMove = (Task) model.get("taskToMove");
		
		taskService.moveTaskToPanel(taskToMove,"not taken");
		
		model.put("tasksNotTaken", getTasksNotTakenAndSuggestedDeveloper());
		model.put("tasksTaken", taskService.findAllFromPanel("taken"));
		model.put("tasksInProgress", taskService.findAllFromPanel("in progress"));
		model.put("tasksInReview", taskService.findAllFromPanel("in review"));
		model.put("tasksInDone", taskService.findAllFromPanel("in done"));
		return "showTaskboardForDeveloper";
	}
	
	
	@RequestMapping(value = "/moveTaskFromInProgressToTaken", method = RequestMethod.GET)
	public String showFormMoveFromInProgressToTaken( @ModelAttribute("loginForm") User loginForm,Map<String, Object> model) {
		model.put("tasksNotTaken", getTasksNotTakenAndSuggestedDeveloper());
		model.put("tasksTaken", taskService.findAllFromPanel("taken"));
		model.put("tasksInProgress", taskService.findAllFromPanel("in progress"));
		model.put("tasksInReview", taskService.findAllFromPanel("in review"));
		model.put("tasksInDone", taskService.findAllFromPanel("in done"));
		
		User foundUser = userService.findByUsernameAndPassword(loginForm.getUsername(), loginForm.getPassword());
		model.put("tasksInProgressForDeveloper", taskService.findAllFromPanelForDeveloper("in progress",foundUser.getId()));
		
		Task taskToMove = new Task();
		model.put("taskToMove", taskToMove);
		return "moveTaskFromInProgressToTaken";
	}

    
	@RequestMapping(value = "/moveTaskFromInProgressToTaken", method = RequestMethod.POST)
	public String processMoveFromInProgressToTaken(@ModelAttribute("taskToMove") Task taskToMove,BindingResult result, Map<String, List<Task>> model) {
		taskToMove = (Task) model.get("taskToMove");
		
		taskService.moveTaskToPanel(taskToMove, "taken");
	
		model.put("tasksNotTaken", getTasksNotTakenAndSuggestedDeveloper());
		model.put("tasksTaken", taskService.findAllFromPanel("taken"));
		model.put("tasksInProgress", taskService.findAllFromPanel("in progress"));
		model.put("tasksInReview", taskService.findAllFromPanel("in review"));
		model.put("tasksInDone", taskService.findAllFromPanel("in done"));
		return "showTaskboardForDeveloper";
	}
	

	@RequestMapping(value = "/moveTaskFromInReviewToInProgressByDeveloper", method = RequestMethod.GET)
	public String showFormMoveFromInReviewToInProgressByDeveloper(Map<String, Object> model, @ModelAttribute("loginForm") User loginForm) {
		model.put("tasksNotTaken", getTasksNotTakenAndSuggestedDeveloper());
		model.put("tasksTaken", taskService.findAllFromPanel("taken"));
		model.put("tasksInProgress", taskService.findAllFromPanel("in progress"));
		model.put("tasksInReview", taskService.findAllFromPanel("in review"));
		model.put("tasksInDone", taskService.findAllFromPanel("in done"));
		
		User foundUser = userService.findByUsernameAndPassword(loginForm.getUsername(), loginForm.getPassword());
		model.put("tasksInReviewForDeveloper", taskService.findAllFromPanelForDeveloper("in review",foundUser.getId()));
		
		Task taskToMove = new Task();
		model.put("taskToMove", taskToMove);
		return "moveTaskFromInReviewToInProgressByDeveloper";
	}

    
	@RequestMapping(value = "/moveTaskFromInReviewToInProgressByDeveloper", method = RequestMethod.POST)
	public String processMoveFromInReviewToInProgressByDeveloper(@ModelAttribute("taskToMove") Task taskToMove,BindingResult result, Map<String, List<Task>> model) {
		taskToMove = (Task) model.get("taskToMove");
		
		taskService.moveTaskToPanel(taskToMove,"in progress");
		
		model.put("tasksNotTaken", getTasksNotTakenAndSuggestedDeveloper());
		model.put("tasksTaken", taskService.findAllFromPanel("taken"));
		model.put("tasksInProgress", taskService.findAllFromPanel("in progress"));
		model.put("tasksInReview", taskService.findAllFromPanel("in review"));
		model.put("tasksInDone", taskService.findAllFromPanel("in done"));
		return "showTaskboardForDeveloper";
	}
	
	
	@RequestMapping(value = "/moveTaskFromInReviewToInProgressByScrumMaster", method = RequestMethod.GET)
	public String showFormMoveFromInReviewToInProgressByScrumMaster(Map<String, Object> model) {
		model.put("tasksNotTaken", getTasksNotTakenAndSuggestedDeveloper());
		model.put("tasksTaken", taskService.findAllFromPanel("taken"));
		model.put("tasksInProgress", taskService.findAllFromPanel("in progress"));
		model.put("tasksInReview", taskService.findAllFromPanel("in review"));
		model.put("tasksInDone", taskService.findAllFromPanel("in done"));
		
		Task taskToMove = new Task();
		model.put("taskToMove", taskToMove);
		return "moveTaskFromInReviewToInProgressByScrumMaster";
	}

   
	@RequestMapping(value = "/moveTaskFromInReviewToInProgressByScrumMaster", method = RequestMethod.POST)
	public String processMoveFromInReviewToInProgressByScrumMaster(@ModelAttribute("taskToMove") Task taskToMove,BindingResult result, Map<String, List<Task>> model) {
		taskToMove = (Task) model.get("taskToMove");
		
		taskService.moveTaskToPanel(taskToMove,"in progress");
		
		model.put("tasksNotTaken", getTasksNotTakenAndSuggestedDeveloper());
		model.put("tasksTaken", taskService.findAllFromPanel("taken"));
		model.put("tasksInProgress", taskService.findAllFromPanel("in progress"));
		model.put("tasksInReview", taskService.findAllFromPanel("in review"));
		model.put("tasksInDone", taskService.findAllFromPanel("in done"));
		return "showTaskboardForScrumMaster";
	}

    
	@RequestMapping(value = "/showMenu", method = RequestMethod.GET)
	public String showMenu(Map<String, Object> model,@ModelAttribute("loginForm") User loginForm) {
		loginForm = (User) model.get("loginForm");
		
		User foundUser = userService.findByUsernameAndPassword(loginForm.getUsername(), loginForm.getPassword());
		
		if(foundUser.getPosition().equals("developer")) return "loginDeveloper";
		return "loginScrumMaster";
	}
	
	
	private List<Task> getTasksNotTakenAndSuggestedDeveloper() {
		ArrayList<Task> tasksNotTaken =  (ArrayList<Task>) taskService.findAllFromPanel("not taken");
		for(int i=0;i<tasksNotTaken.size();i++) {
			if(tasksNotTaken.get(i).getMetaTags().size() >0) {
				Developer developer = metaTagService.getSuggestedDeveloperForTask(tasksNotTaken.get(i));
				tasksNotTaken.get(i).setDeveloper(developer);
			}
		}
		return tasksNotTaken;
	}
	
	
	public TaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}
}
