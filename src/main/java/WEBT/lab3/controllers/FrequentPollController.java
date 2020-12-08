package WEBT.lab3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import WEBT.lab3.constants.WebPathConstants;
import WEBT.lab3.views.FrequentPollView;
import WEBT.lab3.views.View;

@Controller
@RequestMapping(WebPathConstants.frequentPoll)
public class FrequentPollController {
	
	private String jsonArr = "";
	
	@GetMapping
	private String longPoll(Model model) {
		View view = new FrequentPollView();
		view.buildModel(model);
		return view.getFile();
	}
	
	@GetMapping("/" + WebPathConstants.arr)
	@ResponseBody
	public String longPoll() {
		return "[" + jsonArr + "]";
	}
	
	@PostMapping("/" + WebPathConstants.message)
	@ResponseBody
	public String updateArr(@RequestBody String body) {
		if(body.equals("clear")) {
			jsonArr = "";
		} else if(jsonArr.isEmpty()) {
			jsonArr = body.substring(1, body.length() - 1);
		} else {
			jsonArr += ',' + body.substring(1, body.length() - 1);
		}
		return "success";
	}
	
}

//Made by EugeneVV