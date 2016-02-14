package com.iquestgroup.advancedframeworks.ScrumTaskboard.domain;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.BurndownChartDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.DepartmentDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.DeveloperDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.ScrumMasterDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.StoryDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.TaskDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.TaskboardDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.jpa.JpaBurndownChartDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.jpa.JpaDepartmentDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.jpa.JpaDeveloperDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.jpa.JpaScrumMasterDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.jpa.JpaStoryDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.jpa.JpaTaskDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.jpa.JpaTaskboardDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.service.BurndownChartService;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.service.CommentService;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.service.DepartmentService;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.service.DeveloperService;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.service.ScrumMasterService;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.service.StoryService;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.service.TaskService;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.service.TaskboardService;


public class Main {

	private static Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, SQLException {
       
		ApplicationContext context = new FileSystemXmlApplicationContext("/src/main/resources/spring/application-config.xml");
        
     /*
		BurndownChartDao burndownChartDaoJpa = (JpaBurndownChartDao) context.getBean("jpaBurndownChartDao");
		BurndownChart burndownChart = new BurndownChart(2, 3);
		burndownChartDaoJpa.create(burndownChart);
		
		
		burndownChart.setSprintDaysNumber(10);
		burndownChart.setStoryPointsNumber(20);
		burndownChartDaoJpa.update(burndownChart);
		
		//BurndownChart findBurndownChartJpa = burndownChartDaoJpa.findById(1);
		logger.info(findBurndownChartJpa);
		
		TaskboardDao taskboardDaoJpa = (JpaTaskboardDao) context.getBean("jpaTaskboardDao");
		Taskboard taskboard = new Taskboard();
		taskboard.setBurndownChart(findBurndownChartJpa);
		taskboardDaoJpa.create(taskboard);
		
		StoryDao storyDaoJpa = (JpaStoryDao) context.getBean("jpaStoryDao");

		Story story1 = new Story();
		story1.setDescription("Story1");
		story1.setTaskboard(taskboard);
		storyDaoJpa.create(story1);
	
		
		Story story2 = new Story();
		story2.setDescription("Story2");
		story2.setTaskboard(taskboard);
		storyDaoJpa.create(story2);
		
		
		Story story = new Story();
		story.setId(1);
		story.setTaskboard(taskboard);
		storyDaoJpa.update(story);

		
		DepartmentDao departmentDaoJpa = (JpaDepartmentDao) context.getBean("jpaDepartmentDao");
		Department department = new Department("Java Solutions", "Oana Bufnea");
		departmentDaoJpa.create(department);
		
		
		DeveloperDao developerDaoJpa = (JpaDeveloperDao) context.getBean("jpaDeveloperDao");
		Developer developer = new Developer("Mihaela", "Ciugudean");
		developer.setDepartment(department);
		developerDaoJpa.create(developer);
		
 		TaskDao taskDaoJpa = (JpaTaskDao) context.getBean("jpaTaskDao");
		Task task = new Task("Task1");
		task.setStory(story);
		task.setDeveloper(developer);
		taskDaoJpa.create(task);
		
		Task newTask = new Task("new");
		newTask.setStory(story1);
		newTask.setDeveloper(developer);
		taskDaoJpa.create(newTask);
	
		
		ScrumMasterDao scrumMasterDaoJpa = (JpaScrumMasterDao) context.getBean("jpaScrumMasterDao");
		ScrumMaster scrumMaster = new ScrumMaster("Tudor", "Popescu");
		scrumMaster.setTaskboard(taskboard);
		scrumMasterDaoJpa.create(scrumMaster);   
		*/
		
		BurndownChartService burndownChartService = (BurndownChartService) context.getBean("burndownChartService");
		BurndownChart burndownChartS = new BurndownChart(4, 5);
		burndownChartService.create(burndownChartS);
		
		burndownChartS.setSprintDaysNumber(8);
		burndownChartS.setStoryPointsNumber(20);
		burndownChartService.update(burndownChartS);
		
		BurndownChart findBurndownChartJpa = burndownChartService.findById(1);
		logger.info(findBurndownChartJpa);  
		logger.info("---------------------------------------------------------------");
		
		
		DepartmentService departmentService = (DepartmentService) context.getBean("departmentService");
		Department departmentS = new Department("IT Services", "Adela Pop");
		departmentService.create(departmentS);
		
		departmentS.setDepartmentName("IT Services & Products");
		departmentS.setManager("Adela Maniu");
		departmentService.update(departmentS);
		
		Department findDepartment = departmentService.findById(1);
		logger.info(findDepartment);
		logger.info("---------------------------------------------------------------");
		
		
		DeveloperService developerService = (DeveloperService) context.getBean("developerService");
		
		Developer developerS = new Developer("Maria", "Lazar");
		developerS.setDepartment(departmentS);
		developerService.create(developerS);
		
		developerS.setFirstName("Maria");
		developerS.setLastName("Danciu");
		developerService.update(developerS);
		
		Developer findDeveloper = developerService.findById(1);
		logger.info(findDeveloper);
		logger.info("---------------------------------------------------------------");
		
	
		TaskboardService taskboardService =  (TaskboardService) context.getBean("taskboardService");
		
		Taskboard taskboardS = new Taskboard();
		taskboardS.setBurndownChart(burndownChartS);
		
		taskboardService.create(taskboardS);
		
		Taskboard findTaskboard = taskboardService.findById(1);
		logger.info(findTaskboard);
		logger.info("---------------------------------------------------------------");
		
		
		ScrumMasterService scrumMasterService  =  (ScrumMasterService) context.getBean("scrumMasterService");
		
		ScrumMaster scrumMasterS = new ScrumMaster("Laura", "Pop");
		scrumMasterS.setTaskboard(taskboardS);
		scrumMasterService.create(scrumMasterS);
		
		scrumMasterS.setFirstName("Laura");
		scrumMasterS.setLastName("Dan");
		scrumMasterService.update(scrumMasterS);
		
		ScrumMaster findScrumMaster = scrumMasterService.findById(1);
		logger.info(findScrumMaster);
		logger.info("---------------------------------------------------------------");
	
		
		StoryService storyService = (StoryService) context.getBean("storyService");
		
		Story storyS = new Story();
		storyS.setDescription("Story1");
		storyS.setTaskboard(taskboardS);
		
		storyService.create(storyS);
		
		storyS.setDescription("Updated Story1");
		storyService.update(storyS);
		
		Story findStory = storyService.findById(1);
		logger.info(findStory);
		logger.info("---------------------------------------------------------------");
		
		//storyService.delete(1);
		
		
		TaskService taskService = (TaskService) context.getBean("taskService");
		
		Task taskS = new Task("Task1");
		taskS.setDeveloper(developerS);
		taskS.setStory(storyS);
		
		taskService.create(taskS);
		
		taskS.setDescription("Updated Task1");
		taskService.update(taskS);
		
		Task findTask = taskService.findById(1);
		logger.info(findTask);
		logger.info("---------------------------------------------------------------");
		
		//taskService.delete(1);
		
		CommentService commentService = (CommentService) context.getBean("commentService");
		Comment comment = new Comment("comm");
		comment.setTask(findTask);
		commentService.create(comment);
	}
}
