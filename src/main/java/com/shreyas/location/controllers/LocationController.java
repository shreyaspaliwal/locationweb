package com.shreyas.location.controllers;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shreyas.location.entities.Location;
import com.shreyas.location.repos.LocationRepository;
import com.shreyas.location.service.LocationService;
import com.shreyas.location.util.EmailUtil;
import com.shreyas.location.util.ReportUtil;

import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class LocationController {
	
	@Autowired
	LocationService service;
	
	@Autowired
	LocationRepository repo;
	
	@Autowired
	EmailUtil emailUtil;
	
	@Autowired
	ReportUtil reportUtil;
	
	@Autowired
	ServletContext servletContext;
	
	@RequestMapping("/showCreate")
	public String showCreate() {
		return "createLocation";
	}
	
	@RequestMapping("/saveLoc")
	public String saveLocation(@ModelAttribute("location") Location location, ModelMap  modelMap) {
		Location locationSaved = service.saveLocation(location);
		String msg = "Location saved with id:" + locationSaved.getId();
		modelMap.addAttribute("msg", msg);
		emailUtil.sendEmail("abc@xyz.com", "Location saved", "Location saved succesfully and about to return the response");
		return "createLocation";
	}
	
	@RequestMapping("/displayLocations")
	public String displayLocations(ModelMap modelMap) {
		List<Location> locations = service.getAllLocations();
		modelMap.addAttribute("locations", locations);
		return "displayLocations";
	}
	
	@RequestMapping("deleteLocations")
	public String deleteLocations(@RequestParam("id") int id, ModelMap modelMap) {
		Location location = service.getLocationById(id);
		service.deleteLocation(location);
		List<Location> locations = service.getAllLocations();
		modelMap.addAttribute("locations", locations);
		return "displayLocations";
	}
	
	@RequestMapping("/showUpdate")
	public String showUpdate(@RequestParam("id") int id, ModelMap modelMap) {
		Location location = service.getLocationById(id);
		modelMap.addAttribute("location", location);
		return "updateLocation";
	}
	
	@RequestMapping("/updateLoc")
	public String updateLocation(@ModelAttribute("location") Location location, ModelMap  modelMap) {
		service.updateLocation(location);
		List<Location> locations = service.getAllLocations();
		modelMap.addAttribute("locations", locations);
		return "displayLocation";
	}
	
	@RequestMapping("/generateReport")
	public String generateReport() {
		String path = servletContext.getRealPath("/");
		List<Object[]> data = repo.findTypeAndHowMany();
		reportUtil.generatePieChart(path, data);
		return "report";
	}
}
	