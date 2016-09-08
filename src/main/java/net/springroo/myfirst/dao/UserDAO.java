package net.springroo.myfirst.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.springroo.myfirst.domain.Users;

@Repository
@Transactional
public class UserDAO {
	
	@PersistenceContext
	EntityManager em;
	
	public Users getUserbyUsername(String username){
		Session session = (Session) em.getDelegate();
		
		return (Users) session.createCriteria(Users.class).add(Restrictions.eq("username",username)).uniqueResult();
		
		
	}
	public void addFriend(Users user, Users friend){
		
		Session session = (Session) em.getDelegate();
		
		user = (Users) session.createCriteria(Users.class).add(Restrictions.idEq(user.getId())).uniqueResult();
		user.getFriends().add(friend);
		
		session.saveOrUpdate(user);
		
		
	}

}
