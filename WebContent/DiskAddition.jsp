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

<script type="text/javascript" src="js/jquery-2.1.4.js"></script>

<script charset="utf-8" type="text/javascript"><%@ include file="js/diskAdmin.js"%></script>

<title>Добавление диска</title>
</head>
<body>
	<jsp:include page="Interact.jsp"></jsp:include>

	<c:set var="actors" value="${actors}" scope="page"></c:set>
	<c:set var="genres" value="${genres}" scope="page"></c:set>

	<div class="container">

		<div class="headers">

			<h2 align="center">Добавление диска</h2>

			<font class="attent">${attention}</font>
		</div>

		<form method="post" id="general" action="Disk_Administration">

			<table class="input">

				<tr>
					<td>Название:<br> <input required type="text"
						name="title" /></td>
				</tr>

				<tr>
					<td>Стоимость:<br> <input required type="text"
						name="price" /></td>
				</tr>

				<tr>
					<td>На складе:<br> <input required type="text"
						name="stored" /></td>
				</tr>
				<tr>
					<td>Год выпуска:<br> <input type="text"
						name="release" /></td>
				</tr>
				<tr>
					<td>Название и формат обложки<br> <input type="text"
						name="cover" accept="image/jpeg,image/png">

					</td>
				</tr>

			</table>

			<div class="butplace" align=center style="margin-top: 25px;" color="black">
				<div class="butplacegenre" align="center" valign="top">
					<font>Теги: </font>

					<p></p>
					<select form="general" size="10" multiple="multiple"
						name="chosenGenre">

						<c:if test="${not empty genres}">
								 {
									<c:forEach var="genre" items="${genres}">

								<option value="${genre.id}">
									<c:out value="${genre.name}"></c:out>
								</option>
							</c:forEach>
						</c:if>
					</select>
					<p>

						<button type="button" name="plus">
							<b>+</b>
						</button>
						<button type="button" name="minus">
							<b>-</b>
						</button>
				</div>

				<div class="butplaceactor" style="margin-top: 25px;" align="center">
					<font>Актеры: </font>

					<p>
						<select form="general" size="10" multiple="multiple"
							name="chosenActor">

							<c:if test="${not empty actors}">
								<c:forEach items="${actors }" var="actor">

									<option value="${actor.id}">
										<c:out value="${actor.actorName}"></c:out>
									</option>
								</c:forEach>
							</c:if>
						</select>
					<p>

						<button type="button">
							<b>+</b>
						</button>
						<button type="button">
							<b>-</b>
						</button>
				</div>

			</div>
			<p>
			<div style="margin-top: 40px;" align="center">
				<p>
				<p>

					<select form="general" name="sellable">
						<option value="0">Только аренда</option>
						<option value="1">Только продажа</option>
						<option value="2">Продажа и аренда</option>

					</select>
				<p>
				Рейтинг:<p> 
					<select form="general" name="rating" style="width: 100px;">
						<option value="G">G</option>
						<option value="PG">PG</option>
						<option value="PG13">PG-13</option>
						<option value="R">R</option>
						<option value="NC17">NC-17</option>
					</select><p>
					<font style="margin-top: 40px;" class="paddingtop">Описание</font><br>

					<textarea maxlength="512" form="general" rows="10" cols="72"
						name="description"></textarea>
				<p></p>

				<span class="input"><button form="general" class="paddingtop"
						type="submit" style="width: 150px;">Подтвердить</button> </span>
			</div>
		</form>
	</div>

</body>
</html>