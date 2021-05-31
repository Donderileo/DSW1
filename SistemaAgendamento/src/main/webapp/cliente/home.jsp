<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
    <title>Home</title>
    <meta charset="UTF-8">
</head>

<body>

	<%
		String contextPath = request.getContextPath().replace("/", "");
	%>


	<fmt:bundle basename="messages">
		<legend><fmt:message key="project"/></legend>
		<legend><fmt:message key="welcome"/></legend>
		<p>${sessionScope.clienteLogado.nome}</p>
		
		<a href="/<%= contextPath%>/consultas/agendar">Agende uma consulta</a>

		
		<c:forEach var="consulta" items="${sessionScope.listaConsulta}">
			<p>${consulta.profissional.nome} - ${consulta.data}</p>
		</c:forEach>			
		

    </fmt:bundle>
</body>

</html>