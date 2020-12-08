package WEBT.lab3.controllers;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

//import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import WEBT.lab3.constants.WebPathConstants;
import WEBT.lab3.views.LongPollView;
import WEBT.lab3.views.View;

@Controller
@RequestMapping(WebPathConstants.longPoll)
public class LongPollController {
	
	private String jsonArr = "";
	private Long lastModified = -1L;
	
	@GetMapping
	private String longPoll(Model model) {
		View view = new LongPollView();
		view.buildModel(model);
		return view.getFile();
	}
	
	@GetMapping("/" + WebPathConstants.arr)
	@ResponseBody
	public DeferredResult<String> longPoll(@RequestParam(name = "lastUpdate", required = false, defaultValue = "-2") Long lastUpdate) {
		Long timeout = 60 * 1000L; // 1 minute
		String timeoutResponce = "time out";
		DeferredResult<String> result = new DeferredResult<>(timeout, timeoutResponce);
		CompletableFuture.runAsync(() -> {
			try {
				while(!result.isSetOrExpired()) {
					if(lastUpdate < lastModified) {
						result.setResult("[" + jsonArr + "]"); 
					}
					TimeUnit.MILLISECONDS.sleep(100);
				}
			} catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		});
		return result;
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
		lastModified = System.currentTimeMillis();
		return "success";
	}
}

//Made by EugeneVV