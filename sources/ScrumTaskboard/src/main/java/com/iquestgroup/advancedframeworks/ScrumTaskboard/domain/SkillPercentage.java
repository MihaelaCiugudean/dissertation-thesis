package com.iquestgroup.advancedframeworks.ScrumTaskboard.domain;

public class SkillPercentage {

	private int id;
	
	private float currentPercentage;
	private float upgradedPercentage;
	
	
	public SkillPercentage() {
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	
	public float getCurrentPercentage() {
		return currentPercentage;
	}
	
	public void setCurrentPercentage(float currentPercentage) {
		this.currentPercentage = currentPercentage;
	}
	
	
	public float getUpgradedPercentage() {
		return upgradedPercentage;
	}
	
	public void setUpgradedPercentage(float upgradedPercentage) {
		this.upgradedPercentage = upgradedPercentage;
	}
}
