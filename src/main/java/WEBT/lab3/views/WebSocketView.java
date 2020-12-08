package WEBT.lab3.views;

import org.springframework.ui.Model;

import WEBT.lab3.constants.FileConstants;

public class WebSocketView extends View{
	private final String[] scripts = new String[] {
		"/webjars/sockjs-client/sockjs.min.js",
		"/webjars/stomp-websocket/stomp.min.js",	
		"/resources/js/" + FileConstants.WebSocketJS.getValue()	
	};
	public WebSocketView() {
		super(FileConstants.DrawBoard.getValue());
	}
	@Override
	public void buildModel(Model model) {
		super.buildModel(model);
		model.addAttribute("scripts", scripts);
	}
}
//Made by EugeneVV