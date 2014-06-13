package tw.com.chris.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import tw.com.chris.service.ItemService;

@Controller
public class IndexController {
	
	@Autowired
	private ItemService itemService;

	@RequestMapping("/index")
	public String index(Model model){
		model.addAttribute("items",itemService.getItems());
		return "index";
	}
	
}
