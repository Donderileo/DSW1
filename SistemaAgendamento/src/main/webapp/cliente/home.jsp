<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
    <title>Home</title>
    <meta charset="UTF-8">
     <link href="${pageContext.request.contextPath}/styles.css" rel="stylesheet" type="text/css"/>
</head>

<body>

	<%
		String contextPath = request.getContextPath().replace("/", "");
	%>

	
	<fmt:bundle basename="messages">
	<div class="nav">
			<h1>
				<fmt:message key="project"/>			
			</h1>
			
		</div>
		
		<div class="extremos">
			<h2>
				<fmt:message key="welcome"/>
				${clienteLogado.nome}
			</h2>
			<a class="btn" href="/<%= contextPath%>/cliente/home.jsp">Home</a>		
		</div>
		
		
		<div class="btn-home">
			<a class="btn" href="/<%= contextPath%>/consultas/agendar">Agende uma consulta</a>
			<a class="btn" href="/<%= contextPath%>/clientes/listarConsultas">Suas consultas</a>
			<a class="btn" href="/<%= contextPath%>/lista">Lista de Profissionais</a>
		</div>
		
		
		

    </fmt:bundle>
</body>

</html>
