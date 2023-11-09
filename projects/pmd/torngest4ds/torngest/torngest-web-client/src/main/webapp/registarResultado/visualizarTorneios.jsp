<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<!--  No scriptlets!!! 
	  See http://download.oracle.com/javaee/5/tutorial/doc/bnakc.html 
-->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="model" class="presentation.web.model.VisualizarTorneiosModel" scope="request"/>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="/resources/app.css"> 
	<title>TornGest: Registar resultado de encontro</title>
</head>
<body>
	<h2>Visualizar Torneios</h2>
	<form action="registarResultado" method="post">
		<div class="mandatory_field">
			<label for="designacaoTorneio">Designação do torneio: </label>			
			<select name="designacaoTorneio">
				<c:forEach var="torneio" items="${model.listaTorneios}">
					<option value="${torneio.designacaoTorneio}" ${torneio.designacaoTorneio == model.primeiroTorneio ? 'selected="selected"' : ''}> ${torneio.designacaoTorneio} </option>			
				</c:forEach>
			</select>
		</div>
		 <br />
		<div class="button" align="right">
			<input type="submit" value="Procurar Encontros">
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
	
	<c:if test="${model.hasTorneios}">
		<table>
			<tr>
				<th>Designação</th>
			</tr>
			<c:forEach var="torneio" items="${model.listaTorneios}">
				<tr>
					<td><c:out value="${torneio.designacaoTorneio}" /></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>