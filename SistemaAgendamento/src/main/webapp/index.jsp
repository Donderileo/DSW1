<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>

<head>
    <title>Index</title>
    <meta charset="UTF-8">
</head>

<body>
	<fmt:bundle basename="messages">
	
		<legend><fmt:message key="project"/></legend>
		<legend><fmt:message key="welcome"/></legend>
		<a href="login">
			<fmt:message key="login"/>
		</a>
		<a href="clientes/cadastro">
			<fmt:message key="regCliente"/>
		</a>
		<a href="profissional/cadastro">
			<fmt:message key="regProfissional"/>
		</a>

    </fmt:bundle>
</body>

</html>