package WEBT.lab3.views;

import org.springframework.ui.Model;

import WEBT.lab3.constants.FileConstants;

public class FrequentPollView extends View{
	private final String[] scripts = {
		"/resources/js/" + FileConstants.FrequentPollJS.getValue()	
	};
	
	public FrequentPollView() {
		super(FileConstants.DrawBoard.getValue());
	}
	
	@Override
	public void buildModel(Model model) {
		super.buildModel(model);
		model.addAttribute("scripts", scripts);
	}
}
//Made by EugeneVV