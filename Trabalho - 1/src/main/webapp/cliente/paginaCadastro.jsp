<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
    <title>Cadastro Cliente</title>
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
			<a class="btn" href="/<%= contextPath%>/login.jsp"><fmt:message key="jaConta"/></a>		
		</div>
			
		<fieldset>
			<form name="cadastro" action="/<%= contextPath%>/clientes/insere" method="POST">
				<div>
					<div>
						<p>CPF</p> 							
						<input type="text"  name="cpf" /> 
						
						<p><fmt:message key="name"/></p>	
						<input type="text" name="nome" /> 
                        
                        <p>Email</p> 						
                        <input type="email"  name="email" /> 
                        
                        <p><fmt:message key="pass"/></p>		
                        <input type="password"  name="senha" /> 
                        
                        <p><fmt:message key="tel"/></p> 		
                        <input type="text" name="telefone" /> 
                        
                        <p><fmt:message key="gender"/></p> 		
                        <input type="radio" name="sexo" value="male"> Male<br>
					  	<input type="radio" name="sexo" value="female"> Female<br>
					  	<input type="radio" name="sexo" value="other"> Other
                        
                        <p><fmt:message key="birthDate"/></p> 	
                        <input type="date"  name="dataNasc" /> 
                     
					</div>
					<input type="submit" name="register" value=<fmt:message key="register"/> />
				</div>
			</form>
		</fieldset>

    </fmt:bundle>
</body>

</html>