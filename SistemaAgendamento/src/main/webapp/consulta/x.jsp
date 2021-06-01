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
			<fmt:message key="project"/>
		
			<fmt:message key="welcome"/>
			
				${profissionalLogado.nome}
				${clienteLogado.nome}
				
				<a href="/<%= contextPath%>/profissional/home.jsp">Home</a>			
						
		</div>
		
		<c:if test="${mensagens.existeErros}">
            <div id="erro">
                <ul>
                    <c:forEach var="erro" items="${mensagens.erros}">
                        <li> ${erro} </li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
        
        <a href="/<%= contextPath%>/"><fmt:message key="backToLogin"/></a>
		

    </fmt:bundle>
</body>

</html>