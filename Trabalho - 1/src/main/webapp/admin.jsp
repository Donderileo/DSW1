<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
    <title>Login ADM</title>
    <meta charset="UTF-8">
</head>

<body>

	<%
		String contextPath = request.getContextPath().replace("/", "");
	%>


	<fmt:bundle basename="messages">
		
		<c:if test="${mensagens.existeErros}">
            <div id="erro">
                <ul>
                    <c:forEach var="erro" items="${mensagens.erros}">
                        <li> ${erro} </li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
		
		<legend><fmt:message key="project"/></legend>
		<legend><fmt:message key="welcome"/></legend>
			
			
		<fieldset>
			<p>Admin</p>
			<form name="teste" action="/<%= contextPath%>/admin" method="POST">
				<div>
					<div>
						<p><fmt:message key="user"/></p> <input type="text"  name="email" /> 
						<p><fmt:message key="pass"/></p> <input type="password" name="senha" /> 
					</div>
					<input type="submit" name="loginAdmin" value=<fmt:message key="login"/> />
				</div>
			</form>
		</fieldset>

    </fmt:bundle>
</body>

</html>