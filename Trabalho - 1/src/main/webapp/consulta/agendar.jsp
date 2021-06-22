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
		
		<form action="/<%= contextPath%>/consultas/insere" method="post">
		
					<fieldset>
					<label for="profissional">
							<fmt:message key="profissional"/>:
					</label>	
					<br/>
						
						<select name="profissional">
							<c:forEach var="profissional" items="${requestScope.listaProfissionais}">
								<option value="${profissional.cpf}">${profissional.nome}, ${profissional.especialidade}</option>
							</c:forEach>
						</select> <br/>
						
						<br/>
							
						<label for="data">
							<fmt:message key="date"/>:
						</label> 
						
						<br/>
						
						<input type="date" name="data"> <br/>
  						
  						<br/>
  						
  						<label for="horario">
							<fmt:message key="schedule"/>:
						</label> 
						
						<br/>
  						
  						<select name="horario" id="horario">
							<option value="00">00:00 - 01:00</option>
							<option value="01">01:00 - 02:00</option>
							<option value="02">02:00 - 03:00</option>
							<option value="03">03:00 - 04:00</option>
							<option value="04">04:00 - 05:00</option>
							<option value="05">05:00 - 06:00</option>
							<option value="06">06:00 - 07:00</option>
							<option value="07">07:00 - 08:00</option>
							<option value="08">08:00 - 09:00</option>
							<option value="09">09:00 - 10:00</option>
							<option value="10">10:00 - 11:00</option>
							<option value="11">11:00 - 12:00</option>
							<option value="12">12:00 - 13:00</option>
							<option value="13">13:00 - 14:00</option>
							<option value="14">14:00 - 15:00</option>
							<option value="15">15:00 - 16:00</option>
							<option value="16">16:00 - 17:00</option>
							<option value="17">17:00 - 18:00</option>
							<option value="18">18:00 - 19:00</option>
							<option value="19">19:00 - 20:00</option>
							<option value="20">20:00 - 21:00</option>
							<option value="21">21:00 - 22:00</option>
							<option value="22">22:00 - 23:00</option>
							<option value="23">23:00 - 00:00</option>
						</select> <br/>
						
						<br/>
						
						<input type="submit" name="consultaOK" value="<fmt:message key="register"/>"/>
					</fieldset>
				</form>
			
		

    </fmt:bundle>
</body>

</html>