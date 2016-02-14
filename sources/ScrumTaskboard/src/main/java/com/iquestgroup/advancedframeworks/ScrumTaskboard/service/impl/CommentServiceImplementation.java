package com.iquestgroup.advancedframeworks.ScrumTaskboard.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Comment;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Developer;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Task;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.CommentDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.DeveloperDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.TaskDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.service.CommentService;

@Service("commentService")
public class CommentServiceImplementation implements CommentService {

	private CommentDao commentDao;

	@Autowired
	private DeveloperDao developerDao;

	@Autowired
	private TaskDao taskDao;

	private Logger logger = Logger.getLogger(CommentServiceImplementation.class
			.getName());

	@Autowired
	public CommentServiceImplementation(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	public List<Comment> findAll() {
		ArrayList<Comment> comments = new ArrayList<Comment>();
		try {
			comments = (ArrayList<Comment>) commentDao.findAll();
		} catch (SQLException e) {
			logger.warn("Unable to retrieve all comments from the database!");
			e.printStackTrace();
		}
		return comments;
	}

	public List<Comment> findAllForDeveloper(int userId) {
		ArrayList<Comment> comments = new ArrayList<Comment>();
		Developer developer;

		try {
			developer = developerDao.findByUserId(userId);
			comments = (ArrayList<Comment>) commentDao
					.findAllForDeveloper(developer.getId());
		} catch (SQLException e) {
			logger.warn("Unable to retrieve all comments corresponding to the specified developer from the database!");
			e.printStackTrace();
		}
		return comments;
	}

	public Comment findById(int commentId) {
		try {
			Comment comment = commentDao.findById(commentId);
			return comment;
		} catch (SQLException e) {
			logger.warn("The comment with the specified id can not be found!",
					e);
			e.printStackTrace();
		}
		return null;
	}

	public void create(Comment comment) {
		try {
			commentDao.create(comment);
		} catch (SQLException e) {
			logger.warn("The comment can not be created!", e);
			e.printStackTrace();
		}
	}

	public void createForAllDevelopers(Comment comment) {
		List<Developer> developers;
		try {
			commentDao.create(comment);

			developers = developerDao.findAll();
			for (int i = 0; i < developers.size(); i++) {
				List<Comment> comments = new ArrayList<Comment>();
				List<Task> tasks = taskDao.findAllFromPanelForDeveloper("in review", developers.get(i).getId());
				for (int j = 0; j < tasks.size(); j++) {
					List<Comment> previousComments = tasks.get(j).getComments();
					comments.addAll(previousComments);
				}
				comments.add(comment);
			}
		} catch (SQLException e) {
			logger.warn("The comment can not be added to all the developers!",
					e);
			e.printStackTrace();
		}
	}

	public void update(Comment comment) {
		try {
			commentDao.update(comment);
		} catch (SQLException e) {
			logger.warn("The comment can not be updated!", e);
			e.printStackTrace();
		}
	}

	public void delete(int commentId) {
		try {
			commentDao.delete(commentId);
		} catch (SQLException e) {
			logger.warn(
					"The comment with the specified id can not be deleted!", e);
			e.printStackTrace();
		}
	}
}
