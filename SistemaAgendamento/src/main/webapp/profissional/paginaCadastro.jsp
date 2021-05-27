<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
    <title>Cadastro Profissional</title>
    <meta charset="UTF-8">
</head>

<body>

	<%
		String contextPath = request.getContextPath().replace("/", "");
	%>

	<fmt:bundle basename="messages">
	
		<legend><fmt:message key="project"/></legend>
		<legend><fmt:message key="welcome"/></legend>
			
		<fieldset>
			<form name="cadastro" action="/<%= contextPath%>/profissionais/insere" method="POST">
				<div>
					<div>
						<p>CPF</p> 							
						<input type="text"  name="cpf" /> 
						
						<p><fmt:message key="name"/></p>	
						<input type="text" name="nome"/> 
                        
                        <p>email</p> 						
                        <input type="text"  name="email" /> 
                        
                        <p><fmt:message key="pass"/></p>		
                        <input type="password"  name="senha"/> 
                        
                        <p><fmt:message key="espec"/></p> 		
                        <input type="text"  name="especialidade"/> 
                        
                        <p><fmt:message key="resume"/></p> 		
                        <input type="text"  name="curriculo" /> 
                        
                     
					</div>
					<input type="submit" name="register" value=<fmt:message key="register"/> />
				</div>
			</form>
		</fieldset>

    </fmt:bundle>
</body>

</html>