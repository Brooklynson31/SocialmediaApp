package net.springroo.myfirst.domain;



import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;




@Entity
@Table(name="users")
public class Users {

    /**
     */
	
    private Long id;
    private String password;
    private String username;
    private String confirmpassword;
    private String accountTypes;
    private String firstName;
    private String lastName;
	private Boolean termsofService;
    private Set<Message> messages;
    private Set<Users> friends = new HashSet<Users>();	

    private Set<PendingFriendship> pendingFriendship;
  
    @OneToMany(mappedBy="friendId")
    public Set<PendingFriendship> getPendingFriendship() {
		return pendingFriendship;
	}

	public void setPendingFriendship(Set<PendingFriendship> pendingFriendship) {
		this.pendingFriendship = pendingFriendship;
	}

	
	
    
    @ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(name="friend",
	joinColumns={@JoinColumn(name="id")},
	inverseJoinColumns={ @JoinColumn(name="friend_id")})
    public Set<Users> getFriends() {
		return friends;
	}

	public void setFriends(Set<Users> friends) {
		this.friends = friends;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
 

	public String getPassword() {
        return this.password;
    }

	public void setPassword(String password) {
        this.password = password;
    }

	public String getUsername() {
        return this.username;
    }

	public void setUsername(String username) {
        this.username = username;
    }

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	
		@Column(name="account_types")
	  public String getAccountTypes() {
			return accountTypes;
		}
	  	@Column(name="termsof_service")
		public Boolean getTermsofService() {
			return termsofService;
		}
	

	public void setTermsofService(Boolean termsofService) {
		this.termsofService = termsofService;
	}


	public void setAccountTypes(String accountTypes) {
		this.accountTypes = accountTypes;
	}
	
	@OneToMany(fetch= FetchType.EAGER,cascade=CascadeType.ALL,mappedBy="user" )
	public Set<Message> getMessages() {
		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	

	
}
