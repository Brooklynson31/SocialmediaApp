<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
            <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1> Home Page</h1>
Thanks for Logging in : ${user.username}

<h2>Share a Message: </h2>
<form:form commandName="message" method="POST">
<form:textarea style = "width: 400px; height: 80px;" path="content"/>
<input type = "submit" id="SubmitBtn" value = "Post a Message"/>
</form:form>


<h3>Messages</h3>

<c:forEach items = "${messages}" var = "message">
${message.content} <br/>
</c:forEach>

</body>
</html>