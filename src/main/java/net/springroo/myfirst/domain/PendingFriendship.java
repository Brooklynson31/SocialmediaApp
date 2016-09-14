package net.springroo.myfirst.domain;




import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import net.springroo.myfirst.domain.Users;


@Entity
@Table(name="pending_friendship")
public class PendingFriendship {
	
	private Users friendId;
	private Users userId;
	private Long id;
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	//@JoinColumn(name="friend_id")
	@ManyToOne()
	public Users getFriendId() {
		return friendId;
	}
	public void setFriendId(Users friendId) {
		this.friendId = friendId;
	}
	
	@ManyToOne()
	public Users getUserId() {
		return userId;
	}

	public void setUserId(Users userId) {
		this.userId = userId;
	}


}
