<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
    <title>Lista de Profissionais</title>
    <meta charset="UTF-8">
</head>

<body>
			<%
				String contextPath = request.getContextPath().replace("/", "");
			%>

<div align="center">
			<table border="1">
				<caption> Profissionais </caption>
				<tr>
					<th>CPF</th>
					<th>NOME</th>
					<th>EMAIL</th>
					<th>SENHA</th>
					<th>ESPECIALIDADE</th>
					<th>CURRICULO</th>
					<th>AÇÂO</th>
				</tr>
				<c:forEach var="Profissional" items="${requestScope.listaProfissional}">
				<tr>
					<td>${Profissional.cpf}</td>
					<td>${Profissional.nome}</td>
					<td>${Profissional.email}</td>
					<td>${Profissional.senha}</td>
					<td>${Profissional.especialidade}</td>
					<td>${Profissional.curriculo}</td>
					<td>
						<a href="/<%= contextPath%>/profissionais/editar?cpf=${Profissional.cpf}">
						EDITAR
						</a>
						<a href="/<%= contextPath%>/profissionais/remover?cpf=${Profissional.cpf}">
						REMOVER
						</a>
					</td>
				</tr>
				</c:forEach>
			</table>
		</div>
</body>
</html>