package com.iquestgroup.advancedframeworks.ScrumTaskboard.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="metaTag")
public class MetaTag {
	@Id
	@GeneratedValue
	@Column(name="metaTagId")
	private int id;
	
	@Column(name="metaTagName")
	private String metaTagName;
	
	@ManyToOne()
	@JoinColumn(name="taskId")
	private Task task;
	
	
	public MetaTag() {
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getMetaTagName() {
		return metaTagName;
	}

	public void setMetaTagName(String metaTagName) {
		this.metaTagName = metaTagName;
	}


	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}
}
