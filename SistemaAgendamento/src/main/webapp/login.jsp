<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
    <title>Login</title>
    <meta charset="UTF-8">
     <link href="styles.css" rel="stylesheet" type="text/css"/>
     <link href="${pageContext.request.contextPath}/styles.css" rel="stylesheet" type="text/css"/>
      <script src="script.js"></script>
      <script src="${pageContext.request.contextPath}/script.js"></script>
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
			</h2>
			<a>
				<div id="btn" class="btn" onClick="doToggle()">
					
					<div class="none" id="pro">
						Alterar login para Cliente
					</div >
					<div id="cli">
						Alterar login para Profissional
					</div>
				</div>	
			</a>	
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
		
		<fieldset id="logCli">
			<p>Cliente</p>
			<form name="teste" action="/<%= contextPath%>/login" method="POST">
				<div>
					<div>
						<p><fmt:message key="user"/></p> <input type="text"  name="email" /> 
						<p><fmt:message key="pass"/></p> <input type="password" name="senha" /> 
					</div>
					<input type="submit" name="loginCliente" value=<fmt:message key="login"/> />
				</div>
			</form>
		</fieldset>
		
		<fieldset class = "none" id="logPro">
			<p>Profissional</p>
			<form name="teste" action="/<%= contextPath%>/login" method="POST">
				<div>
					<div>
						<p><fmt:message key="user"/></p> <input type="text"  name="email" /> 
						<p><fmt:message key="pass"/></p> <input type="password" name="senha" /> 
					</div>
					<input type="submit" name="loginProfissional" value=<fmt:message key="login"/> />
				</div>
			</form>
		</fieldset>

    </fmt:bundle>
</body>

</html>