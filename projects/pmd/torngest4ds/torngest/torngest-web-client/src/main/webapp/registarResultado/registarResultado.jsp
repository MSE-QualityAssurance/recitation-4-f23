<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<!--  No scriptlets!!! 
	  See http://download.oracle.com/javaee/5/tutorial/doc/bnakc.html 
-->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="model" class="presentation.web.model.RegistarResultadoModel" scope="request"/>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="/resources/app.css"> 
	<title>TornGest: Registar resultado de encontro</title>
</head>
<body>
	<h2>Registar resultado do encontro</h2>
	<form action="processarRegistoResultado" method="post">
		<div class="mandatory_field">
			<label for="designacaoTorneio">Designação do torneio: </label>
			<label for="tentativaDesignacao">${model.designacaoTorneio}</label>
			<input type="hidden" name="designacaoTorneio" size="80" value="${model.designacaoTorneio}" />
		</div> 
		<br />
		<div class="mandatory_field">
			<label for="numeroEncontro">Número do encontro: </label>
			<select name="numeroEncontro">
				<c:forEach var="encontro" items="${model.listaEncontros}">
					<option value="${encontro.numeroEncontro}" ${encontro.numeroEncontro == model.primeiroEncontro ? 'selected="selected"' : ''}> ${encontro.numeroEncontro}</option>			
				</c:forEach>
			</select>
		</div> 
		<c:if test="${not hasEncontros}">
			<label for="naoExistemEncontros">Nao existem encontros para serem seleccionados. Volte atrás.</label>
		</c:if>
		<br />
		<br />
		<div class="mandatory_field">
			<label for="resultado">Resultado do encontro: </label>
			<select name="resultado">
				<c:forEach var="desfecho" items="${model.desfechosEncontros}">
					<option value="${desfecho}" ${desfecho == model.primeiroDesfechoEncontro ? 'selected="selected"' : ''}> ${desfecho}</option>			
				</c:forEach>
			</select>
		</div>
			<div class="button" align="right">
				<input type="submit" value="Submeter">
			</div>
	</form>
	<br />
	<c:if test="${model.hasMessages}" >
		<p>Mensagens</p>
		<ul>
		<c:forEach var="mensagem" items="${model.messages}">
			<li>${mensagem}</li>
		</c:forEach>
		</ul>
	</c:if>
	<br />
		<table>
			<tr>
				<th>Número</th>
				<th>Jogador 1</th>
				<th>Jogador 2</th>
			</tr>
			<c:forEach var="encontro" items="${model.listaEncontros}">
				<tr>
					<td><c:out value="${encontro.numeroEncontro}" /></td>
					<td><c:out value="${encontro.nomeJogador1}" /></td>
					<td><c:out value="${encontro.nomeJogador2}" /></td>
				</tr>
			</c:forEach>
		</table>
</body>
</html>