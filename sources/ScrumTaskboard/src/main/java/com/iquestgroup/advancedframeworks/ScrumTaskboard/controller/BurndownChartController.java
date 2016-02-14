package com.iquestgroup.advancedframeworks.ScrumTaskboard.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.BurndownChart;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.HistoryItem;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.service.BurndownChartService;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.service.DeveloperService;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.service.HistoryItemService;

@Controller
@SessionAttributes("loginForm")
public class BurndownChartController {

	@Autowired
	private BurndownChartService burndownChartService;
	
	@Autowired
	private DeveloperService developerService;
	
	@Autowired
	private HistoryItemService historyItemService;

	
	@RequestMapping(value = "/setSprintDuration", method = RequestMethod.GET)
	public String showSprintDuration(Map<String, Object> model) {
		BurndownChart burndownChart = new BurndownChart();
		model.put("burndownChart", burndownChart);
		return "setSprintDuration";
	}
	
    
	@RequestMapping(value = "/setSprintDuration", method = RequestMethod.POST)
	public String processSetSprintDuration(@ModelAttribute("burndownChart") BurndownChart burndownChart,BindingResult result, Map<String, Object> model) {
		burndownChart = (BurndownChart) model.get("burndownChart");	
		
		float storyPointsNumber = burndownChartService.computeStoryPointsNumber();
		burndownChart.setStoryPointsNumber(storyPointsNumber);
		burndownChartService.create(burndownChart);
		return "showBurndownChart";
	}
	
	
	@RequestMapping(value = "/showBurndownChart", method = RequestMethod.GET)
	public String showBurndownChart(@ModelAttribute("historyItem") HistoryItem historyItem,Map<String, Object> model) throws IOException {
		BurndownChart burndownChart = new BurndownChart();
		float storyPointsNumber = burndownChartService.computeStoryPointsNumber();
		
		if(burndownChartService.findAll().size() > 0) {
			burndownChart = burndownChartService.findAll().get(0);
		}
		else {
			burndownChart.setSprintDaysNumber(10);
		}
		burndownChart.setStoryPointsNumber(storyPointsNumber);
		burndownChartService.update(burndownChart);
		
		if(historyItem.getDayNumber() != 0) {
			historyItem.setStoryPointsNr(storyPointsNumber);
			historyItemService.update(historyItem);
		}

		List<HistoryItem> historyItems = historyItemService.findAll();
		
		burndownChart.setHistoryItems(historyItems);
		
		burndownChartService.drawBurndownChart(burndownChart);
		model.put("burndownChart", burndownChart);
		return "showBurndownChart";
	}
	
	
	@RequestMapping(value = "/showBurndownChartDescription", method = RequestMethod.GET)
	public String showBurndownChartDescription(Map<String, Object> model) throws IOException {
		BurndownChart burndownChart = burndownChartService.findAll().get(0);	
		
		String burndownChartDescription = burndownChartService.obtainBurndownChartDescription(burndownChart);
		
		model.put("burndownChart", burndownChart);
		model.put("burndownChartDescription", burndownChartDescription);
		return "showBurndownChartDescription";
	}
	
	@RequestMapping(value = "/showTeamInformation", method = RequestMethod.GET)
	public String showTeamInformation(Map<String, Object> model) throws IOException {
		BurndownChart burndownChart = burndownChartService.findAll().get(0);	
		
		String burndownChartDescription = burndownChartService.obtainBurndownChartDescription(burndownChart);
		String teamInformation = developerService.obtainTeamInformation();
		
		model.put("burndownChart", burndownChart);
		model.put("burndownChartDescription", burndownChartDescription);
		model.put("teamInformation", teamInformation);
		return "showTeamInformation";
	}
}
