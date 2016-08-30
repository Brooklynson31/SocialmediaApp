package net.springroo.myfirst.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.springroo.myfirst.domain.Message;

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
