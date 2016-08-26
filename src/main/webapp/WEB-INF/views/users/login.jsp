<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Login</h1>
<form:form commandName="users" method="POST">
Username: <form:input path="username" id="username" /><br/><br/>
Password: <form:password path="password" id="password"/><br/><br/>
<input type = "submit" id="SubmitBtn" value = "Submit"/>
${fail}
</form:form>
</body>
</html>