package com.iquestgroup.advancedframeworks.ScrumTaskboard.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="burndownchart")
public class BurndownChart {

	@Id
	@GeneratedValue
	@Column(name="burndownChartId")
	private int id;
	
	@Column(name="storyPointsNr")
	private float storyPointsNumber;
	
	@Column(name="sprintDaysNr")
	private int sprintDaysNumber;

	@OneToMany(mappedBy="burndownChart",fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<HistoryItem> historyItems;
	
	
	public BurndownChart(float storyPointsNumber, int sprintDaysNumber) {
	     this.storyPointsNumber = storyPointsNumber;
		this.sprintDaysNumber = sprintDaysNumber;
	}
	

	public BurndownChart() {
		super();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public float getStoryPointsNumber() {
		return storyPointsNumber;
	}

	public void setStoryPointsNumber(float storyPointsNumber) {
		this.storyPointsNumber = storyPointsNumber;
	}
	

	public int getSprintDaysNumber() {
		return sprintDaysNumber;
	}

	public void setSprintDaysNumber(int sprintDaysNumber) {
		this.sprintDaysNumber = sprintDaysNumber;
	}


	public List<HistoryItem> getHistoryItems() {
		return historyItems;
	}


	public void setHistoryItems(List<HistoryItem> historyItems) {
		this.historyItems = historyItems;
	}


	@Override
	public String toString() {
		return "Number of story points= "+storyPointsNumber+ " performed in "+ sprintDaysNumber
				      + " days.";
	}
}