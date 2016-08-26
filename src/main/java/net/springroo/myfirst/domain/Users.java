package net.springroo.myfirst.domain;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@Entity
@Table(name="users")
public class Users {

    /**
     */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     */
    private String password;

    /**
     */
    private String username;
    
   private String confirmpassword;
   private String accountTypes;

   private Boolean termsofService;

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

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

	
}
