<%@page import="dao.GenreDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.awt.List"%>
<%@page import="dao.ActorDAO"%>
<%@page import="entity.Actor"%>
<%@page import="entity.Genre"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>

<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.9.0.js"></script>

<script charset="utf-8" type="text/javascript"><%@ include file="js/diskAdmin.js"%></script>

<title>Изменение книги</title>
</head>
<body>
	<jsp:include page="Interact.jsp"></jsp:include>

	<c:set var="actors" value="${actors}" scope="page"></c:set>
	<c:set var="genres" value="${genres}" scope="page"></c:set>

	<div class="container">

		<div class="headers">

			<h2 align="center">Меню управления книгами</h2>

			<h3 align="center">Изменение книги</h3>

			<font class="attent">${attention}</font>
		</div>

		<form method="post" id="general" action="Disk_Redacting">
			<input type="hidden" name="id" value="${redact.id}">

			<table class="input">

				<tr>
					<td>Название:<br> <input required value="${redact.title}"
						type="text" name="title" /></td>
				</tr>

				<tr>
					<td>Стоимость:<br> <input required
						value="${redact.price}" type="text" name="price" /></td>
				</tr>

				<tr>
					<td>На складе:<br> <input required
						value="${redact.stored}" type="text" name="stored" /></td>
				</tr>
				<tr>
					<td>Год выпуска:<br> <input type="text"
						value="${redact.releaseDate}" name="release" /></td>
				</tr>

				<tr>
					<td>Название и формат обложки<br>
					<input value="${redact.cover}" style="width: 330px;"
						type="text" name="cover" accept="image/jpeg,image/png">
					</td>
				</tr>



			</table>


			<%--  <form action="Disk_Administration" style="text-align: center;">
				
				
				</form>--%>


			<div align="center" style="margin-top: 25px;">
				<p>

					<select form="general" name="sellable">
						<option value="0">Только аренда</option>
						<option value="1">Только продажа</option>
						<option value="2">Продажа и аренда</option>
					</select>				<p>
				Рейтинг:<p> 
					<select form="general" name="rating" style="width: 100px;">
						<option value="G">G</option>
						<option value="PG">PG</option>
						<option value="PG13">PG-13</option>
						<option value="R">R</option>
						<option value="NG17">NG-17</option>
					</select><p>  <font class="paddingtop">
					
					
					Description</font><p>

					<textarea maxlength="512" form="general" rows="10" cols="72"
						name="description">${redact.description}</textarea>
				<p></p>

				<span class="input"><button form="general" class="paddingtop"
						type="submit" style="width: 150px;">Submit</button> </span>
			</div>
		</form>
	</div>

</body>
</html>