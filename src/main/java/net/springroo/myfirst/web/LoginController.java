package net.springroo.myfirst.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.springroo.myfirst.domain.Users;
import net.springroo.myfirst.service.LoginService;

@Controller
@RequestMapping(value = "/users")
public class LoginController implements ApplicationContextAware {
	
	@Autowired
	LoginService loginservice;
	
	private ApplicationContext applicationContext;
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(@ModelAttribute("users") Users users, ModelMap model, Locale locale , Model mod ){
	model.put("users", new Users());
		
	loginservice = applicationContext.getBean(LoginService.class);
	logger.info(loginservice.toString());
	
	return "users/login";
	
	}


	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String loginPost(@ModelAttribute("users") Users users, ModelMap model, Locale locale,Model mod, HttpServletRequest request  ){

	 Users userfromDB = loginservice.validateUsernamePassword(users);
	
	
	if(userfromDB != null){
		 request.getSession().setAttribute("users", userfromDB);
		model.addAttribute("users",userfromDB);
		return "redirect:/app/home.htm"; 
	}
	else {
		model.addAttribute("fail", "Incorrect Login");
		return "users/login";
	}
	
		
	}

	

	
@RequestMapping(value = "registration", method = RequestMethod.GET)
	public String registration(@ModelAttribute("users") Users users, ModelMap model, Locale locale , Model mod ){
	model.put("users", new Users());
	
	populateModel(mod);
	return "users/registration";
}


@RequestMapping(value = "registration", method = RequestMethod.POST)
public String registrationPost(@ModelAttribute("users") Users users, ModelMap model, Locale locale,Model mod  ){
	model.put("users", users);
	loginservice.createUser(users);
	populateModel(mod);
		return "users/registration";
}



private void populateModel(Model mod) {
	List<String> accountTypes = new ArrayList<String>();
	accountTypes.add("Basic");
	accountTypes.add("Standard");
	accountTypes.add("Full");
	accountTypes.add("Premium");
	
	List<String> visitedCountries = new ArrayList<String>();
	visitedCountries.add("US");
	visitedCountries.add("RUS");
	visitedCountries.add("GER");
	visitedCountries.add("GBR");
	visitedCountries.add("BRA");
	
	mod.addAttribute("mycountries", visitedCountries);
	mod.addAttribute("myaccountTypes", accountTypes);
}


@Override
public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
this.applicationContext = applicationContext;	
}
	
}
