package net.springroo.myfirst.service;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import net.springroo.myfirst.dao.LoginDAO;
import net.springroo.myfirst.domain.Users;

@Service

public class LoginService {
	@Autowired
	private LoginDAO logindao;

	
	private LoginService(){
		
	}

	
	public Users validateUsernamePassword(Users user){
		
		
		if("".equals(user.getUsername()) || "".equals(user.getPassword()) ){
			return null;
		}
	
		Users userfromDB = logindao.getUser(user);
		return userfromDB;
	}


	public void createUser(Users users) {
		 logindao.createUser(users);
		
	}
	
	

}
