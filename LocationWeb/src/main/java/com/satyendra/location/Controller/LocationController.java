package com.satyendra.location.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.satyendra.location.entities.Location;
import com.satyendra.location.repos.LocationRepository;
import com.satyendra.location.service.LocationService;
import com.satyendra.location.util.EmailUtil;
import com.satyendra.location.util.ReportUtil;

import jakarta.servlet.ServletContext;

@Controller
public class LocationController {
	
	@Autowired
	LocationService service;
	
	@Autowired
	EmailUtil emailutil;
	
	@Autowired
	LocationRepository repository;
	
	@Autowired
	ReportUtil reportUtil;
	
	@Autowired
	ServletContext sc;
	
	@RequestMapping("/showCreate")
	public String showCreate() {
		return "createLocation";
	}
	
	@RequestMapping("/saveLoc")
	public String saveLocation(@ModelAttribute("location") Location location,ModelMap modelMap) {
		Location locationSaved=service.saveLocation(location);
		String msg="Location saved with id: "+locationSaved.getId();
		modelMap.addAttribute("msg", msg);
		//emailutil.sendEmail("drax123drax@gmail.com","location saved","Location saved Successfully");
		return "createLocation";
	}
	
	@RequestMapping("/displayLocations")
	public String displayLocations(ModelMap modelMap) {
		List<Location> locations=service.getAllLocations();
		modelMap.addAttribute("locations", locations);
		return "displayLocations";
		
	}
	
	@RequestMapping("deleteLocation")
	public String deleteLocation(@RequestParam("id") int id,ModelMap modelMap) {
		Location location=service.getLocationById(id);
		service.deleteLocation(location);
		List<Location> locations=service.getAllLocations();
        modelMap.addAttribute("locations",locations);
		return "displayLocations";
	}
	
	@RequestMapping("/showUpdate")
	public String showUpdate(@RequestParam("id") int id,ModelMap modelMap) {
		Location location=service.getLocationById(id);
		modelMap.addAttribute("location", location);
		return "updateLocation";
	}
	
	@RequestMapping("/updateLoc")
	public String updateLocation(@ModelAttribute("location") Location location,ModelMap modelMap) {
		service.updateLocation(location);
		
		List<Location> locations=service.getAllLocations();
		modelMap.addAttribute("locations",locations);
		return "displayLocations";
	}
	
	@RequestMapping("/generateReport")
	public String generateReport() {
		String path=sc.getRealPath("/");
		List<Object[]> data=repository.findTypeAndTypeCount();
		reportUtil.generatePieChart(path,data);
		return "report";
	}
}
