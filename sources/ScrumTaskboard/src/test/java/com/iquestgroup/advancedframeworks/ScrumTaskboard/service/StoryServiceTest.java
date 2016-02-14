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
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Story;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Taskboard;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.StoryDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.service.impl.StoryServiceImplementation;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/resources/spring/application-config.xml"}) 
public class StoryServiceTest {
	private static final int STORY_ID = 1;
	
	@Autowired
	private StoryService storyService;
	
	private StoryDao storyDao;
	
	private Story story;
	private Taskboard taskboard;
	private BurndownChart burndownChart;
	
	
	@Before
	public void setUp() {
		storyDao = Mockito.mock(StoryDao.class);
		storyService = new StoryServiceImplementation(storyDao);
		
		burndownChart = new BurndownChart(11,21);
		taskboard = new Taskboard();
		taskboard.setBurndownChart(burndownChart);
		
		story = new Story("Story 1");
		story.setTaskboard(taskboard);
	}
	
	
	@Test
	public void testFindAll() throws SQLException {
		ArrayList<Story> myStories = (ArrayList<Story>) generateStories();
		Mockito.when(storyDao.findAll()).thenReturn(myStories);
		
		ArrayList<Story> storiesFounded = (ArrayList<Story>) storyService.findAll();
		assertNotNull(storiesFounded);
		Mockito.verify(storyDao).findAll();
	}
	
	
	@Test
	public void testFindById() throws SQLException {
		Mockito.when(storyDao.findById(STORY_ID)).thenReturn(story);
		Story storyFounded = storyService.findById(STORY_ID);
		assertNotNull(storyFounded);
		assertEquals(story.getDescription(), storyFounded.getDescription());
		assertEquals(story.getTaskboard().getId(), storyFounded.getTaskboard().getId());
		Mockito.verify(storyDao).findById(STORY_ID);
	}
	
	
	@Test
	public void testCreate() throws SQLException {
		storyService.create(story);
		
		Mockito.verify(storyDao).create(story);
	}
	

	@Test
	public void testUpdate() throws SQLException {
		storyService.create(story);
		
		story.setDescription("Updated Story1");
		storyService.update(story);
		
		Mockito.verify(storyDao).update(story);
	}
	
	
	@Test
	public void testDelete() throws SQLException {
		storyService.create(story);
		
		storyService.delete(story.getId());
		
		Mockito.verify(storyDao).delete(story.getId());
	}
	
	
	private List<Story> generateStories() {
		ArrayList<Story> stories = new ArrayList<Story>();
		
		story = new Story("Story 2");
		stories.add(story);
		
		story = new Story("Story 3");
		stories.add(story);
		
		return stories;
	}
}
