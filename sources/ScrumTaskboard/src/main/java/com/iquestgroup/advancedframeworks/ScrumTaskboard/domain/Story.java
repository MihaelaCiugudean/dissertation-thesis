package com.iquestgroup.advancedframeworks.ScrumTaskboard.domain;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="story")
public class Story {
	
	@Id
	@GeneratedValue
	@Column(name="storyId")
	private int id;
	
	@Column(name="description")
	private String description;
	
	@OneToMany(mappedBy="story",fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private Collection<Task> tasks;
	
	@ManyToOne()
	@JoinColumn(name="taskboardId")
	private Taskboard taskboard;

	public Story() {
	}

	public Story(int id, String description, List<Task> tasks) {
		this.id = id;
		this.description = description;
		this.tasks = tasks;
	}

	public Story(String description) {
		this.description = description;
	} 
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public Collection<Task> getTasks() {
		return tasks;
	}

	
	public void setTasks(Collection<Task> tasks) {
		this.tasks = tasks;
	}
	
	
	public Taskboard getTaskboard() {
		return taskboard;
	}

	public void setTaskboard(Taskboard taskboard) {
		this.taskboard = taskboard;
	}

	@Override
	public String toString() {
		return description;
	}
}
