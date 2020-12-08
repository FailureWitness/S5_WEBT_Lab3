package WEBT.lab3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import WEBT.lab3.constants.FileConstants;
import WEBT.lab3.constants.WebPathConstants;
import WEBT.lab3.views.HomeView;
import WEBT.lab3.views.View;

@Controller
public class MainController {
	
	@GetMapping(WebPathConstants.home)
	private String home_GET(Model model) {
		View view = new HomeView();
		view.buildModel(model);
		return view.getFile();
	}
}
// Made by EugeneVV