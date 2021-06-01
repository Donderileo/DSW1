<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
    <title>Edicao Profissionais</title>
    <meta charset="UTF-8">
</head>

<body>

	<%
		String contextPath = request.getContextPath().replace("/", "");
	%>

	<fmt:bundle basename="messages">
	
		<legend><fmt:message key="project"/></legend>
		<legend><fmt:message key="welcome"/></legend>
			

			<form name="edicao" action="/<%= contextPath%>/profissionais/atualizar" method="POST">
					<fieldset>
						<legend> EDICAO DE PROFISSIONAIS </legend>
						
						CPF <br/>
						<input type="text" name="cpf" value="${profissional.cpf}" readonly/> <br/>
						
						NOME <br/>
						<input type="text" name="nome" value="${profissional.nome}"/> <br/>
						
						EMAIL <br/>
						<input type="email" name="email" value="${profissional.email}"/> <br/>
						
						SENHA <br/>
						<input type="password" name="senha" value="${profissional.senha}"/> <br/>
						
						ESPECIALIDADE <br/>
						<input type="text" name="especialidade" value="${profissional.especialidade}"/> <br/>
						
						CURRICULO <br/>
						<input type="text" name="curriculo" value="${profissional.curriculo}"/> <br/>

						<input type="submit" name="enviar" value="atualizar" />
					</fieldset>
			</form>
		

    </fmt:bundle>
</body>

</html>