<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://code.jquery.com/jquery-3.1.0.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(function(){

	$("#welcomefooterlink").show();
	$("#loginfooterlink").show();

	

	$("#SubmitBtn").click( function() {
		var password = $("#password").val();
		var confirmpassword = $("#confirmpassword").val();
	
		if(! $("#termsofService").is(":checked") )
			{
				alert("You must agree to terms of Service!");
				return false;
			}
		 if(! $("input[name=accountTypes").is(":checked") )
		 {
			 alert("You must select an account type!");
			 return false
		 }
	
		
			if((password == confirmpassword)){
				$("#users").submit();
				}
			else {
				alert("passwords dont match!");
				return false;
				}
			
		
	});
	
});

</script>
<title>Mike's Login Page</title>
</head>
<body>
<h1>User Registration</h1>
<form:form commandName="users" method="POST">
First Name: <form:input path="firstName" id="firstName" /><br/><br/>
Last Name: <form:input path="lastName" id="lastName" /><br/><br/>
Username: <form:input path="username" id="username" /><br/><br/>
Password: <form:password path="password" id="password"/><br/><br/>
ConfirmPassword: <form:password path="confirmpassword" id="confirmpassword"/><br/><br/>
Account Type: <form:radiobuttons path="accountTypes" items="${myaccountTypes}" element="div" id = "accountTypes"/><br/><br/>
<form:checkbox path="termsofService" items="${termsofService}" label = "Agree to terms of Service?" id="termsofService"/><br/>
<input type = "submit" id="SubmitBtn" value = "Submit"/>
</form:form>
</body>
</html>