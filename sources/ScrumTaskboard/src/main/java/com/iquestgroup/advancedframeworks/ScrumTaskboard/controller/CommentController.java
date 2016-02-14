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

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Comment;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Task;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.User;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.service.CommentService;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.service.TaskService;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.service.UserService;

@Controller
@SessionAttributes("loginForm")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value = "/showCommentsForAllTasks", method = RequestMethod.GET)
	public String showCommentsForAllTasks(Map<String, List<Comment>> model) {
		model.put("comments", commentService.findAll());
		return "showCommentsForAllTasks";
	}

	
	@RequestMapping(value = "/showCommentsForDeveloper", method = RequestMethod.GET)
	public String showCommentsForDeveloper(@ModelAttribute("loginForm") User loginForm,Map<String, List<Comment>> model) {
		User foundUser = userService.findByUsernameAndPassword(loginForm.getUsername(), loginForm.getPassword());
		model.put("commentsForDeveloper", commentService.findAllForDeveloper(foundUser.getId()));
		return "showCommentsForDeveloper";
	}

	
	@RequestMapping(value = "/addCommentForTask", method = RequestMethod.GET)
	public String showFormAddForTask(Map<String, Object> model) {
		model.put("tasksInReview", taskService.findAllFromPanel("in review"));
		
		Task selectedTask = new Task();
		model.put("selectedTask", selectedTask);
		
		Comment newComment = new Comment();
		model.put("newComment", newComment);
		return "addCommentForTask";
	}

  
	@RequestMapping(value = "/addCommentForTask", method = RequestMethod.POST)
	public String processAddForTask(@ModelAttribute("selectedTask") Task selectedTask,@ModelAttribute("newComment") Comment newComment,BindingResult result, Map<String, List<Comment>> model) {
		selectedTask = (Task) model.get("selectedTask");
		newComment = (Comment) model.get("newComment");
		
		List<Comment> existentComments = commentService.findAll();
		if(existentComments.size()>0) {
		Comment lastInsertedComment = existentComments.get(existentComments.size()-1);
		newComment.setId(lastInsertedComment.getId()+1);
		}
		
		newComment.setTask(selectedTask);
		commentService.create(newComment);
		
		model.put("comments", commentService.findAll());
		return "showCommentsForAllTasks";
	}
}
