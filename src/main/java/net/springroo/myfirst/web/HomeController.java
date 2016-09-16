package net.springroo.myfirst.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.springroo.myfirst.dao.MessageDAO;
import net.springroo.myfirst.dao.PendingFriendshipDAO;
import net.springroo.myfirst.dao.UserDAO;
import net.springroo.myfirst.domain.FriendshipForm;
import net.springroo.myfirst.domain.Message;
import net.springroo.myfirst.domain.PendingFriendship;
import net.springroo.myfirst.domain.SearchForm;
import net.springroo.myfirst.domain.Users;

@Controller
@RequestMapping("/app")
public class HomeController {
	
	
	@Autowired
	MessageDAO messagedao;
	
	@Autowired
	PendingFriendshipDAO pendingfriendshipdao;
	
	@Autowired
	UserDAO userdao;
	
@RequestMapping(value = "home", method = RequestMethod.GET)	
String home(ModelMap model, HttpServletRequest request){
	
	
	Users user = (Users) request.getSession().getAttribute("users");
	
	populateHomemodel(model, user);
	
	return "app/home";
}





@RequestMapping(value = "home", method = RequestMethod.POST)	
String homePost(@ModelAttribute("message") Message message, @ModelAttribute("friendshipform") FriendshipForm friendshipform, ModelMap model, HttpServletRequest request ){
	
	Users user = (Users) request.getSession().getAttribute("users");
	
	
	
	if(friendshipform.getPendingFriendshipUserId() != null && "acceptFriendRequest".equals(friendshipform.getAction()))
		{
			Users userbyid = userdao.getUserbyId(friendshipform.getPendingFriendshipUserId());
			userdao.addFriend(user, userbyid);
			userdao.deleteFriendRequest(user,userbyid);
		}
	else if(friendshipform.getPendingFriendshipUserId() != null && "denyFriendRequest".equals(friendshipform.getAction()))
	{
		Users userbyid = userdao.getUserbyId(friendshipform.getPendingFriendshipUserId());
		userdao.deleteFriendRequest(user,userbyid);
	}
	
	else if(message.getContent() != null)
	{
		message.setUser(user);
		messagedao.saveMessage(message);
	}
	
	populateHomemodel(model, user);

	
	return "app/home";
}

private void populateHomemodel(ModelMap model, Users user) {
	List<Message> messages = messagedao.getFriendsMessages(user);
	Message message = new Message();
	List<PendingFriendship> friendships = pendingfriendshipdao.getPendingFriendships(user);
	FriendshipForm friendshipform = new FriendshipForm();

	model.put("friendshipform", friendshipform);
	model.put("friendships", friendships);
	model.put("user", user);
	model.put("message", message);
	model.put("messages", messages);
}

@RequestMapping(value = "friend", method = RequestMethod.GET)	
String friendget(ModelMap model, HttpServletRequest request){
	
	SearchForm searchstring = new SearchForm();
	model.put("searchform", searchstring);
	

	
	return "app/friend";
}

@RequestMapping(value = "friend", method = RequestMethod.POST)	
String friendPost(@ModelAttribute("searchform") SearchForm searchstring, ModelMap model, HttpServletRequest request ){
	
	 String action = searchstring.getAction();

	 if("Yes".equals(action))
	 {
		 Users user = (Users) request.getSession().getAttribute("users");
		 Users friend = userdao.getUserbyUsername(searchstring.getSearchString()); 
		 userdao.addFriendRequest(user, friend);
	}
	 else
	 {
			Users userbyusername = userdao.getUserbyUsername(searchstring.getSearchString());
			model.put("searchresult", userbyusername);

	 }
	 
	
	
	return "app/friend";
}


}
