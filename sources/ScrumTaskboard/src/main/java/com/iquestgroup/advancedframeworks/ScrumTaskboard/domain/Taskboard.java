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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="taskboard")
public class Taskboard {

	@Id
	@GeneratedValue
	@Column(name="taskboardId")
	private int id;

	@OneToOne()
	@JoinColumn(name="burndownChartId")
	private BurndownChart burndownChart;
	
	@OneToMany(mappedBy="taskboard",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private Collection<Story> stories;

	
	public Taskboard() {
	}

	
	public Taskboard(List<Story> stories, BurndownChart burndownChart) {
		this.stories = stories;
		this.burndownChart = burndownChart;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Collection<Story> getStories() {
		return stories;
	}

	
	public void setStories(Collection<Story> stories) {
		this.stories = stories;
	}

	
	public BurndownChart getBurndownChart() {
		return burndownChart;
	}

	
	public void setBurndownChart(BurndownChart burndownChart) {
		this.burndownChart = burndownChart;
	}
	

	@Override
	public String toString() {
		return "Taskboard with id= "+id;
	}
}
