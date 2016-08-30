package net.springroo.myfirst.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.springroo.myfirst.dao.MessageDAO;
import net.springroo.myfirst.domain.Message;
import net.springroo.myfirst.domain.Users;

@Controller
@RequestMapping("/app")
public class HomeController {
	
	@Autowired
	MessageDAO messagedao;
	
@RequestMapping(value = "home", method = RequestMethod.GET)	
String home(ModelMap model){
	
	List<Message> messages = messagedao.getMessages();
	Message message = new Message();
	
	model.put("message", message);
	model.put("messages", messages);
	
	return "app/home";
}


@RequestMapping(value = "home", method = RequestMethod.POST)	
String homePost(@ModelAttribute("message") Message message, Model model, ModelMap mod, HttpServletRequest request ){
	
	Users user = (Users) request.getSession().getAttribute("users");
	
	message.setUser(user);
	messagedao.saveMessage(message);
	
	List<Message> messages = messagedao.getMessages();
	message = new Message();
	
	mod.put("message", message);
	mod.put("messages",messages);
	
	
	return "app/home";
}


}
