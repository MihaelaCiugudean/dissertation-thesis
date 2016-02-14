package com.iquestgroup.advancedframeworks.ScrumTaskboard.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="historyItem")
public class HistoryItem {
	@Id
	@GeneratedValue
	@Column(name="historyItemId")
	private int id;
	
	@Column(name="dayNr")
	private int dayNumber;
	
	@Column(name="storyPointsNr")
	private float storyPointsNr;

	@ManyToOne()
	@JoinColumn(name="burndownChartId")
	private BurndownChart burndownChart;

	public HistoryItem() {
	}
	
	public HistoryItem(int dayNumber, float storyPointsNr) {
		this.dayNumber = dayNumber;
		this.storyPointsNr= storyPointsNr;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public int getDayNumber() {
		return dayNumber;
	}

	public void setDayNumber(int dayNumber) {
		this.dayNumber = dayNumber;
	}


	public float getStoryPointsNr() {
		return storyPointsNr;
	}

	public void setStoryPointsNr(float storyPointsNr) {
		this.storyPointsNr = storyPointsNr;
	}

	public BurndownChart getBurndownChart() {
		return burndownChart;
	}

	public void setBurndownChart(BurndownChart burndownChart) {
		this.burndownChart = burndownChart;
	}
}
