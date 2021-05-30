<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
    <title>Cadastro Cliente</title>
    <meta charset="UTF-8">
</head>

<body>

	<%
		String contextPath = request.getContextPath().replace("/", "");
	%>

	<fmt:bundle basename="messages">
	
		<legend><fmt:message key="project"/></legend>
		<legend><fmt:message key="welcome"/></legend>
			

			<form name="edicao" action="/<%= contextPath%>/clientes/atualizar" method="POST">
					<fieldset>
						<legend> EDICAO DE CLIENTES </legend>
						
						CPF </br>
						<input type="text" name="cpf" value="${cliente.cpf}" readonly/> <br/>
						
						NOME </br>
						<input type="text" name="nome" value="${cliente.nome}"/> <br/>
						
						EMAIL </br>
						<input type="email" name="email" value="${cliente.email}"/> <br/>
						
						SENHA </br>
						<input type="password" name="senha" value="${cliente.senha}"/> <br/>
						
						SEXO </br>
						<input type="text" name="sexo" value="${cliente.sexo}"/> <br/>
						
						TELEFONE </br>
						<input type="text" name="telefone" value="${cliente.telefone}"/> <br/>
						
						DATA DE NASC </br>
						<input type="text" name="dataNasc" value="${cliente.dataNasc}"/> <br/>

						<input type="submit" name="enviar" value="atualizar" />
					</fieldset>
			</form>
		

    </fmt:bundle>
</body>

</html>