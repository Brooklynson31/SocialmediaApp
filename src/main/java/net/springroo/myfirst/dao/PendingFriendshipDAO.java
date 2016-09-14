package net.springroo.myfirst.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.springroo.myfirst.domain.PendingFriendship;
import net.springroo.myfirst.domain.Users;

@Repository
@Transactional 
public class PendingFriendshipDAO {
	
	@PersistenceContext
	EntityManager em;

	@SuppressWarnings("unchecked")
	public  List<PendingFriendship> getPendingFriendships(Users user) {
		Session session = (Session) em.getDelegate();
		
		return (List<PendingFriendship>) session.createCriteria(PendingFriendship.class)
				.add(Restrictions.eq("friendId", user)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		
		
	}

}
	