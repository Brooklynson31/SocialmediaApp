package net.springroo.myfirst.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.springroo.myfirst.domain.Users;

@Repository
@Transactional
public class LoginDAO {
	
	@PersistenceContext
	EntityManager em;
	
	
	public void createUser(Users user)
	{
		Session session = (Session) em.getDelegate();
		
		session.save(user);
	}
	
	
	public Users getUser(Users user){
		
		Session session = (Session) em.getDelegate();
		
		@SuppressWarnings("unchecked")
		List<Users> list = session.createCriteria(Users.class)
				.add(Restrictions.eq("username", user.getUsername()))
				.add(Restrictions.eq("password", user.getPassword()))
				.list();
		if(list.isEmpty())
			return null;
		else
			{
			return list.get(0);
			}		
	}

}


