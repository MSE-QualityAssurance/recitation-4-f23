<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<!--  No scriptlets!!! 
	  See http://download.oracle.com/javaee/5/tutorial/doc/bnakc.html 
-->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="model" class="presentation.web.model.VisualizarCalendarioModel" scope="request"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/resources/app.css"> 
<title>TornGest: Visualizar Calendário</title>
</head>
<body>
	<h2>Visualizar Calendário do Jogador</h2>
	<form action="verConfrontos" method="post">
		<div class="mandatory_field">
			<label for="numeroInscricao">Número de Inscrição:</label>
			<select name="numeroInscricao">
					<c:forEach var="jogador" items="${model.jogadores}">
						<option value="${jogador.id}" ${jogador.id == model.primeiro ? 'selected="selected"' : ''}> ${jogador.id} </option>			
					</c:forEach>
			</select>
		</div> <br />
		<div class="mandatory_field">
			<label for="data">Data: </label>
			<input type="text" name="data" size=50 value="${model.data}" placeholder=" dd-MM-yyyy"/>
		</div>	
		<div class="button" align="right">
			<input type="submit" value="Visualizar">
		</div>
	</form>

	<c:if test="${model.hasMessages}" >
		<p>Mensagens</p>
		<ul>
		<c:forEach var="mensagem" items="${model.messages}">
			<li>${mensagem}</li>
		</c:forEach>
		</ul>
	</c:if>
	
	<c:if test="${model.hasJogadores}">
	<table>
		<tr>
			<th>Número</th>
			<th>Nome</th>
		</tr>
		<c:forEach var="jogador" items="${model.jogadores}">
			<tr>
				<td><c:out value="${jogador.id}" /></td>
				<td><c:out value="${jogador.nome}"/></td>
			</tr>
		</c:forEach>
	</table>
	</c:if>
</body>
</html>