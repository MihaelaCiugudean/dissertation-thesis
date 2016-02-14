package com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.BurndownChart;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Department;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Developer;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Story;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Task;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Taskboard;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.BurndownChartDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.DepartmentDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.DeveloperDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.StoryDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.TaskDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.TaskboardDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/resources/spring/application-config.xml"})  
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class TaskDaoTest {

	@Autowired
	private TaskDao taskDao;
	
	@Autowired
	private StoryDao storyDao;
	
	@Autowired
	private TaskboardDao taskboardDao;
	
	@Autowired
	private BurndownChartDao burndownChartDao;
	
	@Autowired
	private DeveloperDao developerDao;
	
	@Autowired
	private DepartmentDao departmentDao;
	
	private BurndownChart burndownChart;
	private Taskboard taskboard;
	private Story story;
	private Developer developer;
	private Department department;
	private Task taskInserted;
	
	
	@Before
	public void setUp() throws SQLException {
		burndownChart = new BurndownChart(2,5);
		burndownChartDao.create(burndownChart);
		
		taskboard = new Taskboard();
		taskboard.setBurndownChart(burndownChart);
		taskboardDao.create(taskboard);
		
		story= new Story();
		story.setTaskboard(taskboard);
		storyDao.create(story);
		
		department = new Department("Java Solutions", "Alin Pop");
		departmentDao.create(department);
		
		developer = new Developer("Ana", "Crisan");
		developer.setDepartment(department);
		developerDao.create(developer);
		
		taskInserted = new Task("Task1");
		taskInserted.setStory(story);
		taskInserted.setDeveloper(developer);
		taskDao.create(taskInserted);
	}
	
	
	@Test
	public void testFindAll() throws SQLException {
		List<Task> tasksInserted = createTasks();

		List<Task> allTasks = taskDao.findAll();
		assertFindAll(tasksInserted, allTasks);
	}

	
	@Test
	public void testFindById() throws SQLException {
		List<Task> allTasks = taskDao.findAll();
		Task task = allTasks.get(0);
		
		assertNotNull(task);
		assertEquals(taskInserted.getId(), task.getId());
	}
	
	
	@Test
	public void testCreate() throws SQLException {
		List<Task> allTasks = taskDao.findAll();
		Task task = allTasks.get(0);
		
		assertNotNull(task);
		assertEquals(taskInserted.getId(), task.getId());
		assertEquals(taskInserted.getDescription(), task.getDescription());
		assertEquals(taskInserted.getStory().getId(), task.getStory().getId());
		assertEquals(taskInserted.getDeveloper().getId(), task.getDeveloper().getId());
	}
	
	
	@Test
	public void testUpdate() throws SQLException {
		taskInserted.setDescription("Description updated");
		taskDao.update(taskInserted);
		
		List<Task> allTasks = taskDao.findAll();
		Task task = allTasks.get(0);
		
		assertNotNull(task);
		assertEquals(taskInserted.getId(), task.getId());
		assertEquals(taskInserted.getDescription(), task.getDescription());
		assertEquals(taskInserted.getStory().getId(), task.getStory().getId());
		assertEquals(taskInserted.getDeveloper().getId(), task.getDeveloper().getId());
	}
	
	
	@Test
	public void testDelete() throws SQLException {
		taskDao.delete(taskInserted.getId());
		
		List<Task> allTasks = taskDao.findAll();

		assertEquals(allTasks.size(), 0);
	}
	

	private ArrayList<Task> createTasks() throws SQLException {
		ArrayList<Task> tasks = new ArrayList<Task>();
		
		BurndownChart burndownChart1 = new BurndownChart(22,55);
		burndownChartDao.create(burndownChart1);
		
		Taskboard taskboard1 = new Taskboard();
		taskboard1.setBurndownChart(burndownChart1);
		taskboardDao.create(taskboard1);
		
		Story story1 = new Story();
		story1.setTaskboard(taskboard1);
		storyDao.create(story1);
		
		Department department1 = new Department("Oracle", "Tudor Man");
		departmentDao.create(department1);
		
		Developer developer1 = new Developer("Sorin", "Bara");
		developer.setDepartment(department1);
		developerDao.create(developer1);
		
		Task taskInserted1 = new Task("Task2");
		taskInserted1.setStory(story1);
		taskInserted1.setDeveloper(developer1);
		taskDao.create(taskInserted1);
		
		
		BurndownChart burndownChart2 = new BurndownChart(32,35);
		burndownChartDao.create(burndownChart2);
		
		Taskboard taskboard2 = new Taskboard();
		taskboard1.setBurndownChart(burndownChart2);
		taskboardDao.create(taskboard2);
		
		Story story2 = new Story();
		story2.setTaskboard(taskboard2);
		storyDao.create(story2);
		
		Department department2 = new Department(".NET", "Laura Pop");
		departmentDao.create(department2);
		
		Developer developer2 = new Developer("Calin", "Costin");
		developer.setDepartment(department2);
		developerDao.create(developer2);
		
		Task taskInserted2 = new Task("Task3");
		taskInserted2.setStory(story2);
		taskInserted2.setDeveloper(developer2);
		taskDao.create(taskInserted2);
		
		tasks.add(taskInserted1);
		tasks.add(taskInserted2);
		
		return tasks;
	}
	
	
	private void assertFindAll(List<Task> tasksInserted, List<Task> allTasks) {
		Task task1 = allTasks.get(0);
		Task task2 = allTasks.get(1);
		Task task3 = allTasks.get(2);

		assertEquals(allTasks.size(), 3);
		assertNotNull(task1);
		assertNotNull(task2);
		assertNotNull(task3);

		assertEquals(task1.getId(), taskInserted.getId());
		assertEquals(task1.getDescription(), taskInserted.getDescription());
		assertEquals(task1.getStory().getId(), taskInserted.getStory().getId());
		assertEquals(task1.getDeveloper().getId(), taskInserted.getDeveloper().getId());


		assertEquals(tasksInserted.get(0).getId(), task2.getId());
		assertEquals(tasksInserted.get(0).getDescription(), task2.getDescription());
		assertEquals(tasksInserted.get(0).getStory().getId(), task2.getStory().getId());
		assertEquals(tasksInserted.get(0).getDeveloper().getId(), task2.getDeveloper().getId());

		assertEquals(tasksInserted.get(1).getId(), task3.getId());
		assertEquals(tasksInserted.get(1).getDescription(), task3.getDescription());
		assertEquals(tasksInserted.get(1).getStory().getId(), task3.getStory().getId());
		assertEquals(tasksInserted.get(1).getDeveloper().getId(), task3.getDeveloper().getId());
	}
}
