package WEBT.lab3.views;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;

import WEBT.lab3.constants.WebPathConstants;
import WEBT.lab3.templates.NavigationTemplate;

public class View {
	protected final String file;
	protected View(String file) {
		this.file = file;
	}
	protected List<NavigationTemplate> buildNavList(){
		List<NavigationTemplate> navList = new ArrayList<>();
		
		navList.add(new NavigationTemplate("Home", WebPathConstants.home));
		navList.add(new NavigationTemplate("Web Socket", WebPathConstants.webSocket));
		navList.add(new NavigationTemplate("Long Poll", WebPathConstants.longPoll));
		navList.add(new NavigationTemplate("Frequent Poll", WebPathConstants.frequentPoll));
		
		return navList;
	}
	public void buildModel(Model model) {
		model.addAttribute("navList", buildNavList());
	}
	public String getFile() {
		return file;
	}
}
// Made by EugeneVV