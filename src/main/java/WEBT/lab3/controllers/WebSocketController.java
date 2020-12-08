package WEBT.lab3.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import WEBT.lab3.constants.WebPathConstants;
import WEBT.lab3.views.View;
import WEBT.lab3.views.WebSocketView;

@Controller
@RequestMapping(WebPathConstants.webSocket)
public class WebSocketController{
	
	private String jsonArr = "";
	
	@GetMapping()
	private String webSocket_GET(Model model) {
		View view = new WebSocketView();
		view.buildModel(model);
		return view.getFile();
	}
	
	@GetMapping("/" + WebPathConstants.arr)
	@ResponseBody
	private String getArr() {
		return '[' + jsonArr + ']';
	}
	
	@MessageMapping(WebPathConstants.webSocket + "/" + WebPathConstants.message)
	@SendTo("/topic/points")
	public String webSocket(String msg) {
		if(msg.equals("clear")) {
			jsonArr = "";
		} else if(jsonArr.isEmpty()){
			jsonArr = msg.substring(1, msg.length() - 1);
		} else {
			jsonArr += ',' + msg.substring(1, msg.length() - 1);
		}
		return msg;
	}
}
// Made by EugeneVV