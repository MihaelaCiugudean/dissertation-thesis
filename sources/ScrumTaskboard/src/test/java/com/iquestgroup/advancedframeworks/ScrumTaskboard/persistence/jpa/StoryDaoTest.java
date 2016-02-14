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
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Story;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Taskboard;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.BurndownChartDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.StoryDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.TaskboardDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/resources/spring/application-config.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class StoryDaoTest {

	@Autowired
	private StoryDao storyDao;
	
	@Autowired
	private TaskboardDao taskboardDao;
	
	@Autowired
	private BurndownChartDao burndownChartDao;

	private BurndownChart burndownChart;
	private Taskboard taskboard;
	private Story storyInserted;
	
	
	@Before
	public void setUp() throws SQLException {
		burndownChart = new BurndownChart(2,5);
		burndownChartDao.create(burndownChart);
		
		taskboard = new Taskboard();
		taskboard.setBurndownChart(burndownChart);
		taskboardDao.create(taskboard);
		
		storyInserted = new Story();
		storyInserted.setTaskboard(taskboard);
		storyDao.create(storyInserted);
	}
	
	
	@Test
	public void testFindAll() throws SQLException {
		List<Story> storiesInserted = createStories();

		List<Story> allStories = storyDao.findAll();
		assertFindAll(storiesInserted, allStories);
	}

	
	@Test
	public void testFindById() throws SQLException {
		List<Story> allStories = storyDao.findAll();
		Story story = allStories.get(0);
		
		assertNotNull(story);
		assertEquals(storyInserted.getId(), story.getId());
	}
	
	
	@Test
	public void testCreate() throws SQLException {
		List<Story> allStories = storyDao.findAll();
		Story story = allStories.get(0);
		
		assertNotNull(story);
		assertEquals(storyInserted.getId(), story.getId());
		assertEquals(storyInserted.getDescription(), story.getDescription());
		assertEquals(storyInserted.getTaskboard().getId(), story.getTaskboard().getId());
	}
	
	
	@Test
	public void testUpdate() throws SQLException {
		Taskboard taskboardUpdated = new Taskboard();
		taskboardUpdated.setBurndownChart(burndownChart);
		taskboardDao.create(taskboardUpdated);
		
		storyInserted.setTaskboard(taskboardUpdated);
		storyDao.update(storyInserted);
		
		List<Story> allStories = storyDao.findAll();
		Story story = allStories.get(0);
		
		assertNotNull(story);
		assertEquals(storyInserted.getId(), story.getId());
		assertEquals(storyInserted.getDescription(), story.getDescription());
		assertEquals(storyInserted.getTaskboard().getId(), story.getTaskboard().getId());
	}
	
	
	@Test
	public void testDelete() throws SQLException {
		storyDao.delete(storyInserted.getId());
		
		List<Story> allStories = storyDao.findAll();

		assertEquals(allStories.size(), 0);
	}
	
	
	private ArrayList<Story> createStories() throws SQLException {
		ArrayList<Story> stories = new ArrayList<Story>();
		BurndownChart burndownChart1 = new BurndownChart(22,55);
		burndownChartDao.create(burndownChart1);
		
		Taskboard taskboard1 = new Taskboard();
		taskboard1.setBurndownChart(burndownChart1);
		taskboardDao.create(taskboard1);
		
		Story storyInserted1 = new Story();
		storyInserted1.setTaskboard(taskboard1);
		storyDao.create(storyInserted1);
		
		BurndownChart burndownChart2 = new BurndownChart(12,15);
		burndownChartDao.create(burndownChart2);
		
		Taskboard taskboard2 = new Taskboard();
		taskboard1.setBurndownChart(burndownChart2);
		taskboardDao.create(taskboard2);
		
		Story storyInserted2 = new Story();
		storyInserted2.setTaskboard(taskboard2);
		storyDao.create(storyInserted2);
		
		stories.add(storyInserted1);
		stories.add(storyInserted2);
		
		return stories;
	}
	

	private void assertFindAll(List<Story> storiesInserted, List<Story> allStories) {
		Story story1 = allStories.get(0);
		Story story2 = allStories.get(1);
		Story story3 = allStories.get(2);

		assertEquals(allStories.size(), 3);
		assertNotNull(story1);
		assertNotNull(story2);
		assertNotNull(story3);

		assertEquals(story1.getId(), storyInserted.getId());
		assertEquals(story1.getDescription(), storyInserted.getDescription());
		assertEquals(story1.getTaskboard().getId(), storyInserted.getTaskboard().getId());

		assertEquals(storiesInserted.get(0).getId(), story2.getId());
		assertEquals(storiesInserted.get(0).getDescription(), story2.getDescription());
		assertEquals(storiesInserted.get(0).getTaskboard().getId(), story2.getTaskboard().getId());

		assertEquals(storiesInserted.get(1).getId(), story3.getId());
		assertEquals(storiesInserted.get(1).getDescription(), story3.getDescription());
		assertEquals(storiesInserted.get(1).getTaskboard().getId(), story3.getTaskboard().getId());
	}
}

