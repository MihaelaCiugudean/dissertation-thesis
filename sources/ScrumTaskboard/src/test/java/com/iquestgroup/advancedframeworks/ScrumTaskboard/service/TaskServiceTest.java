package com.iquestgroup.advancedframeworks.ScrumTaskboard.service;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.BurndownChart;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Department;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Developer;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Story;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Task;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Taskboard;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.TaskDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.service.impl.TaskServiceImplementation;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/resources/spring/application-config.xml"}) 
public class TaskServiceTest {

	private static final int TASK_ID = 1;
	
	@Autowired
	private TaskService taskService;
	
	private TaskDao taskDao;
	
	private Task task;
	private Story story;
	private Taskboard taskboard;
	private BurndownChart burndownChart;
	private Developer developer;
	private Department department;
	
	
	@Before
	public void setUp() {
		taskDao = Mockito.mock(TaskDao.class);
		taskService = new TaskServiceImplementation(taskDao);
		
		burndownChart = new BurndownChart(9,12);
		taskboard = new Taskboard();
		taskboard.setBurndownChart(burndownChart);
		story = new Story("Story 1");
		story.setTaskboard(taskboard);
		
		department = new Department("Java Solutions", "Ioana Man");
		developer = new Developer("Ioana", "Zamfir");
		developer.setDepartment(department);	
		
		task = new Task("Task 1");
		task.setStory(story);
		task.setDeveloper(developer);
	}
	
	
	@Test
	public void testFindAll() throws SQLException {
		ArrayList<Task> myTasks = (ArrayList<Task>) generateTasks();
		Mockito.when(taskDao.findAll()).thenReturn(myTasks);
		
		ArrayList<Task> tasksFounded = (ArrayList<Task>) taskService.findAll();
		assertNotNull(tasksFounded);
		Mockito.verify(taskDao).findAll();
	}
	

	@Test
	public void testFindById() throws SQLException {
		Mockito.when(taskDao.findById(TASK_ID)).thenReturn(task);
		Task taskFounded = taskService.findById(TASK_ID);
		assertNotNull(taskFounded);
		assertEquals(task.getDescription(), taskFounded.getDescription());
		assertEquals(task.getStory().getId(), taskFounded.getStory().getId());
		assertEquals(task.getDeveloper().getId(), taskFounded.getDeveloper().getId());
		Mockito.verify(taskDao).findById(TASK_ID);
	}
	
	
	@Test
	public void testCreate() throws SQLException {
		taskService.create(task);
		
		Mockito.verify(taskDao).create(task);
	}
	

	@Test
	public void testUpdate() throws SQLException {
		taskService.create(task);
		
		task.setDescription("Updated Task1");
		taskService.update(task);
		
		Mockito.verify(taskDao).update(task);
	}
	
	
	@Test
	public void testDelete() throws SQLException {
		taskService.create(task);
		
		taskService.delete(task.getId());
		
		Mockito.verify(taskDao).delete(task.getId());
	}
	
	
	private List<Task> generateTasks() {
		ArrayList<Task> tasks = new ArrayList<Task>();
		
		tasks.add(task);
		
		task = new Task("Task 2");
		tasks.add(task);
		
		task = new Task("Task 3");
		tasks.add(task);
		
		return tasks;
	}
}
