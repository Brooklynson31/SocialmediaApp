package net.springroo.myfirst.domain;



import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    private Boolean termsofService;
    private Set<Message> messages;

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

	
	public Boolean getTermsofService() {
		return termsofService;
	}

	public void setTermsofService(Boolean termsofService) {
		this.termsofService = termsofService;
	}



	public String getAccountTypes() {
		return accountTypes;
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

	
}
