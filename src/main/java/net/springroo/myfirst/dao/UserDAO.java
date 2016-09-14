package net.springroo.myfirst.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.springroo.myfirst.domain.PendingFriendship;
import net.springroo.myfirst.domain.Users;

@Repository
@Transactional //allows us to load users into memory because it of the lazy load
public class UserDAO {
	
	@PersistenceContext
	EntityManager em;

	public Users getUserbyUsername(String username) {
		
		Session session = (Session) em.getDelegate();
		
		return (Users) session.createCriteria(Users.class).add(Restrictions.eq("username",username)).uniqueResult();
		
	}
	
	public void addFriend(Users user, Users friend){
		Session session = (Session) em.getDelegate();
		user = (Users) session.createCriteria(Users.class).add(Restrictions.idEq(user.getId())).uniqueResult();
		user.getFriends().add(friend);
		friend.getFriends().add(user);
		
		session.saveOrUpdate(user);
	}
	
public void deleteFriendRequest(Users user, Users userbyid) 
{
	Session session = (Session) em.getDelegate();

	PendingFriendship pf = (PendingFriendship) session.createCriteria(PendingFriendship.class)
			.add(Restrictions.eq("friendId", user)) 
			.add(Restrictions.eqOrIsNull("userId", userbyid)).uniqueResult();
	
	session.delete(pf); //deltes row of pending friendship from table affter db query
	
}

	public void addFriendRequest(Users user, Users friend) {
		Session session = (Session) em.getDelegate();

		user = (Users) session.createCriteria(Users.class).add(Restrictions.idEq(user.getId())).uniqueResult();

		PendingFriendship friendship = (PendingFriendship) session.createCriteria(PendingFriendship.class)
		.add(Restrictions.eq("friendId", friend)) // string must match field in pending dao
		.add(Restrictions.eqOrIsNull("userId", user)).uniqueResult();
		
		
		if (friendship != null)
		 {												
			//pendingFriendship.setId(friendship.getId()); //if dont have identiefir set in object hibernate assumes you want to create new one. does update, not save
			return;
		 }												//because we setID hibernate assumes ID exists so hibernate will update existing one
														//tells hibernate Id already exists in database
		
		PendingFriendship pendingFriendship = new PendingFriendship();
		pendingFriendship.setFriendId(friend);
		pendingFriendship.setUserId(user);
		user.getPendingFriendship().add(pendingFriendship);		
		session.saveOrUpdate(pendingFriendship); //need to update pending friendship table not user
												
		}

	public Users getUserbyId(Long pendingfriendshipUserId) {

		Session session = (Session) em.getDelegate();
		
		return (Users) session.createCriteria(Users.class).add(Restrictions.eq("id",pendingfriendshipUserId)).uniqueResult();
			}

	

}
