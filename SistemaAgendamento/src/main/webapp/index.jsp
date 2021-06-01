<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
    <title>Index</title>
    <meta charset="UTF-8">
    <link href="styles.css" rel="stylesheet" type="text/css"/>
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
		<h2>
			<fmt:message key="welcome"/>
		</h2>
		<div class="btns-sup">
			<div class=btns>
				
				<a href="login">
					<div class="btn">
						<fmt:message key="login"/>
					</div>
				</a>
				
				<a href="clientes/cadastro">
					<div class="btn">
						<fmt:message key="regCliente"/>
					</div>
				</a>
				
				<a href="profissionais/cadastro">
					<div class="btn">
						<fmt:message key="regProfissional"/>
					</div>
				</a>
				
				<a href="lista">
					<div class="btn">
						<fmt:message key="regProfissional"/>
					</div>
				</a>
			</div>
		</div>
		
		
    </fmt:bundle>
</body>

</html>