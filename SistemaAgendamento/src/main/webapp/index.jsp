<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>

<head>
    <title>Login</title>
    <meta charset="UTF-8">
</head>

<body>
	<fmt:bundle basename="messages">
	    <form name="teste" action="login.jsp" method="POST">
	        <fieldset>
	            <legend><fmt:message key="project"/></legend>
	            <legend><fmt:message key="wellcome"/></legend>
	            <div>
		            <div>
			            <p><fmt:message key="user"/></p> <input type="text"  name="user" /> 
			            <p><fmt:message key="pwd"/></p> <input type="password" name="password" /> 
			            
					</div>
			            <input type="submit" name="login" value=<fmt:message key="login"/> />
				</div>
	        </fieldset>
	    </form>
    </fmt:bundle>
</body>

</html>