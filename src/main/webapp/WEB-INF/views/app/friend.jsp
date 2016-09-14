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
	$("#yesBtn").click( function() {
		$("#action").val("Yes");
			$("#searchform").submit();
		});
	
	
});
</script>

</head>
<body>

<form:form commandName="searchform" method="POST">
<form:hidden path = "action"/>
<form:input path="searchString" />
<input type = "submit" value = "Find Friend"/>
</form:form>

<c:if test="${!empty searchresult}">
Would you like to add ${searchresult.username} as a friend?
 <input type ="button" value="yes" id="yesBtn" />
</c:if>



</body>
</html>