package com.iquestgroup.advancedframeworks.ScrumTaskboard.domain;

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

import org.hibernate.annotations.Index;

@Entity
@Table(name="task")
public class Task {
	
	@Id
	@GeneratedValue
	@Column(name="taskId")
	private int id;
	
	@Column(name="description")
	private String description;

	@ManyToOne()
	@JoinColumn(name="storyId")
	private Story story;
	
	@ManyToOne()
	@JoinColumn(name="developerId")
	private Developer developer;
	
	@Column(name="status")
	@Index(name = "statusIDX")
	private String status;
	
	@Column(name="nrHours")
	private int nrHours;
	
	/**
	 * Task's priority = [0,5]  0-lowest priority , 5-highest priority
	 */
	@Column(name="priority")
	private int priority; 
	
	/**
	 * Task's difficulty: 1-low difficulty, 2-medium difficulty, 3-high difficulty
	 */
	@Column(name="difficulty")
	private int difficulty; 
	
	
	@OneToMany(mappedBy="task",fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Comment> comments;
	

	@OneToMany(mappedBy="task",fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
	private List<MetaTag> metaTags;
	
	
	public Task() {
	}
	
	public Task( String description)
	{
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


	public Story getStory() {
		return story;
	}

	public void setStory(Story story) {
		this.story = story;
	}


	public Developer getDeveloper() {
		return developer;
	}

	public void setDeveloper(Developer developer) {
		this.developer = developer;
	}
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	public int getNrHours() {
		return nrHours;
	}

	public void setNrHours(int nrHours) {
		this.nrHours = nrHours;
	}
	

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	
	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}


	public List<MetaTag> getMetaTags() {
		return metaTags;
	}

	public void setMetaTags(List<MetaTag> metaTags) {
		this.metaTags = metaTags;
	}

	
	@Override
	public String toString() {
		return description;
	}
}
