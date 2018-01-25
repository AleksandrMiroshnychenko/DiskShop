<%@page import="entity.Genre"%>
<%@page import="entity.Actor"%>
<%@page import="dao.DiskDAO"%>
<%@page import="entity.Disk"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>

<%@ include file="css/Cart.css"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

	<jsp:include page="Interact.jsp"></jsp:include>


	<div class="headers">

		<h2 align="center">Успех</h2>

		<font class="attent">Поздравляем с успешным заказом, ждите звонка оператора.</font>
	</div>

	<div class="container">
	<div class="title" style="text-align: center;">
	<p>
<a href="DiskList" >К списку дисков</a><p>
<c:if test="${sessionScope.user.role eq 0}"><a href="UserOrders" >К списку заказов</a></c:if>
</p></div>
	</div>
		<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>