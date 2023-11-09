<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<!--  No scriptlets!!! 
	  See http://download.oracle.com/javaee/5/tutorial/doc/bnakc.html 
-->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="model" class="presentation.web.model.VerConfrontosModel" scope="request"/>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="/resources/app.css"> 
	<title>TornGest: Visualizar Calendário</title>
</head>
<body>
	<div>
		<label for="numeroInscricao">Número de Inscrição: </label>
		<label for="inscricaoInserida">${model.numeroInscricao}</label>
		<br />
		<label for="data">Data: </label>
		<label for="dataInserida">${model.data}</label>
	</div>	

	<c:if test="${model.hasMessages}" >
		<p>Mensagens</p>
		<ul>
		<c:forEach var="mensagem" items="${model.messages}">
			<li>${mensagem}</li>
		</c:forEach>
		</ul>
	</c:if>
	
	<c:if test="${model.hasConfrontos}">
		<table>
			<tr>
				<th>Torneio</th>
				<th>Data Início</th>
				<th>Nome Adversário</th>
				<th>Número Encontros</th>
				<th>Diferença de Pontos</th>
			</tr>
			<c:forEach var="confronto" items="${model.confrontos}" >
					<tr>
						<td><c:out value="${confronto.torneio}" /></td>
						<td><c:out value="${confronto.dataInicio}" /></td>
						<td><c:out value="${confronto.jogadorAdversario.nome}" /></td>
						<td><c:out value="${confronto.quantidadeEncontros}" /></td>
						<td><c:out value="${confronto.diferencaPontos}" /></td>
					</tr>
			</c:forEach>
		</table>
	</c:if>
	
	<form action="visualizarCalendario" method="post">	
		<div class="button" align="right">
			<input type="submit" value="Voltar">
		</div>
	</form>
</body>
</html>