package net.springroo.myfirst.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/app")
public class HomeController {
	
@RequestMapping(value = "home", method = RequestMethod.GET)	
String home(Model model){
	return "app/home";
}


@RequestMapping(value = "home", method = RequestMethod.POST)	
String homePost(Model model){
	return "app/home";
}


}
