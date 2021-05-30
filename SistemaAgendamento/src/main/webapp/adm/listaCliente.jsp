<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
    <title>Lista de Clientes</title>
    <meta charset="UTF-8">
</head>

<body>
			<%
				String contextPath = request.getContextPath().replace("/", "");
			%>

<div align="center">
			<table border="1">
				<caption> Clientes </caption>
				<tr>
					<th>CPF</th>
					<th>NOME</th>
					<th>EMAIL</th>
					<th>SENHA</th>
					<th>SEXO</th>
					<th>TELEFONE</th>
	             	<th>DATA NASC</th>
	             	<th>AÇÂO</th>
	
				</tr>
				<c:forEach var="cliente" items="${requestScope.listaClientes}">
				<tr>
					<td>${cliente.cpf}</td>
					<td>${cliente.nome}</td>
					<td>${cliente.email}</td>
					<td>${cliente.senha}</td>
					<td>${cliente.sexo}</td>
					<td>${cliente.telefone}</td>
					<td>${cliente.dataNasc}</td>
					<td>
						<a href="/<%= contextPath%>/clientes/editar?cpf=${cliente.cpf}">
						EDITAR
						</a>
						<a href="/<%= contextPath%>/clientes/remover?cpf=${cliente.cpf}">
						REMOVER
						</a>
					</td>
	
				</tr>
				</c:forEach>
			</table>
		</div>

</body>
</html>