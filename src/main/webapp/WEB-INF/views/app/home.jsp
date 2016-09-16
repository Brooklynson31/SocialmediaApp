<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
            <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.1.0.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(function () {
	$("#addfriendbutton").show();
		$("#addfriendbutton").click(function() {
			window.location.href="friend.htm";
		});
	$("#welcomefooterlink").show();
	$("#loginfooterlink").show();

	
		$("input[id^='yesBtn']").click(function() { 
			var userId = $(this).attr("id");
			userId= userId.substring(6);
			$("#pendingFriendshipUserId").val(userId);
			$("#action").val("acceptFriendRequest");
			$("#friendshipform").submit();
		});
		
		$("input[id^='noBtn']").click(function() {
			var userId = $(this).attr("id");
			userId= userId.substring(5);
			$("#pendingFriendshipUserId").val(userId);
			$("#action").val("denyFriendRequest");
			$("#friendshipform").submit();
			});
		
});
</script>
</head>
<body>


<h1> Home Page</h1>

<h2>Thanks for Logging in : ${user.firstName} ${user.lastName} </h2>


<h3>Share a Message: </h3>
<form:form commandName="message" method="POST">
<form:textarea style = "width: 400px; height: 80px;" path="content"/>
<input type = "submit" id="SubmitBtn" value = "Post a Message"/>
</form:form>


<h4>Messages</h4>

<c:forEach items = "${messages}" var = "message">
${message.content} From: ${message.user.username} <br/>
</c:forEach>

<c:if test="${!empty friendships}">
<h1>Pending Friendships</h1>
<form:form commandName="friendshipform" method="POST">
<form:hidden path = "pendingFriendshipUserId"/>
<form:hidden path = "action"/>
<c:forEach items = "${friendships}" var = "friendship">
User: <b>${friendship.userId.username}</b> Wishes to be your friend, Do you accept their request?  
<input type ="button" value="yes" id="yesBtn${friendship.userId.id}" />
 <input type ="button" value="no" id="noBtn${friendship.userId.id}" /><br/>
</c:forEach>
</form:form>
</c:if>




</body>
</html>