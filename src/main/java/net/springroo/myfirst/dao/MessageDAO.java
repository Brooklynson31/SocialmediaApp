package net.springroo.myfirst.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.springroo.myfirst.domain.Message;
import net.springroo.myfirst.domain.Users;

@Repository
@Transactional
public class MessageDAO {

	@PersistenceContext
	EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Message> getMessages(){
		Session session = (Session) em.getDelegate();
		
		return session.createCriteria(Message.class).list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Message> getFriendsMessages(Users user){
		
		Session session = (Session) em.getDelegate();
		
		user = (Users) session.createCriteria(Users.class).add(Restrictions.idEq(user.getId())).uniqueResult();
		
		if(!user.getFriends().isEmpty())
		{
			return session.createCriteria(Message.class)
					.add(Restrictions.in("user", user.getFriends()))
					.list();
		}
		else{
			return null;
		}
	}
	
	public Message getMessageByID(Long messageID){
		Session session = (Session) em.getDelegate();

		@SuppressWarnings("unchecked")
		List<Message> messages = session.createCriteria(Message.class)
				.add(Restrictions.eq("id", messageID))
				.list();
		if(messages.size() == 0)
		{
			return null;
		}
		else
		{
			return messages.get(0);
		}
	}
	
	public void saveMessage(Message message){
		Session session = (Session) em.getDelegate();
		
		session.save(message);
		session.flush();
		
	}

}
