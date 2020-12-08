package WEBT.lab3.views;

import org.springframework.ui.Model;

import WEBT.lab3.constants.FileConstants;

public class LongPollView extends View{
	private final String[] scripts = {
		"/resources/js/" + FileConstants.LongPollJS.getValue()	
	};
	
	public LongPollView() {
		super(FileConstants.DrawBoard.getValue());
	}
	
	@Override
	public void buildModel(Model model) {
		super.buildModel(model);
		model.addAttribute("scripts", scripts);
	}
}
//Made by EugeneVV