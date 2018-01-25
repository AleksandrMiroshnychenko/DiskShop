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
<%@ include file="css/diskList.css"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:set var="disk" value="${disk}" />
<title>${disk.title}</title>

<c:if test="${sessionScope.user.role ne 1 }">
	<script type="text/javascript"
		src="http://code.jquery.com/jquery-1.9.0.js"></script>
	<script type="text/javascript"><%@ include file="js/diskList.js"%></script>
</c:if>
</head>
<body onfocus="countPrice()">

	<jsp:include page="Interact.jsp"></jsp:include>


	<div class="headers">

		<h2 align="center">${disk.title}</h2>

		<h3 align="center">Информация</h3>

		<font class="attent">${attent}</font>
	</div>

	<div class="container" align="center">




		<table id="diskLook">

			<tr>
				
				<td id="price" colspan="2"><c:out
						value="Стоимость ${disk.price}"></c:out></td>

			</tr>
			
			<tr>
				<td id="stored"><c:out value="Доступно ${disk.stored}"></c:out></td>
			</tr>

			<tr>
				<td id="release" colspan="2"><c:out
						value="Год издательства ${disk.releaseDate}" /></td>
			</tr>

			<tr>
				<td colspan="3" class="tags"><c:set var="actors"
						value="${disk.actors.actorNameBatch }" /> <c:set var="asize"
						value="${fn:length(disk.actors.actorIdBatch)}"></c:set> <c:choose>

						<c:when test="${asize eq 1}">
							<c:forEach var="actor" items="${actors}">
								<c:out value="Актёр: ${actor}." />
							</c:forEach>
						</c:when>

						<c:when test="${asize eq 0}">
							Нет актёра
							</c:when>

						<c:otherwise>
							<c:set var="count" value="1"></c:set>
									Актёры: 
									<c:forEach var="actor" items="${actors}">

								<c:choose>

									<c:when test="${asize eq count}">
										<c:out value="${actor}."></c:out>
									</c:when>

									<c:otherwise>
										<c:out value="${actor},"></c:out>
									</c:otherwise>

								</c:choose>
								<c:set var="count" value="${count+1}" />

							</c:forEach>

						</c:otherwise>

					</c:choose></td>
			</tr>

			<tr>
				<td colspan="3"><c:set var="genres"
						value="${disk.genre.genreBatch}" /> <c:set var="gsize"
						value="${fn:length(disk.genre.genreIdBatch)}"></c:set> <c:choose>

						<c:when test="${gsize eq 0}">
							Нет жанров
							</c:when>

						<c:otherwise>
							<c:set var="count" value="1" />
									Жанры: 
									<c:forEach var="genre" items="${genres}">

								<c:choose>

									<c:when test="${gsize eq count}">
										<c:out value="${genre}."></c:out>
									</c:when>

									<c:otherwise>
										<c:out value="${genre},"></c:out>
									</c:otherwise>

								</c:choose>

								<c:set var="count" value="${count + 1}" />

							</c:forEach>

						</c:otherwise>

					</c:choose></td>
			</tr>
			
			<tr>
			<td>
			Рейтинг: ${disk.printRating}
			</td>
			</tr>
			

			<tr>
				<td class="pages" colspan="3" style="text-align: left;"><c:if
						test="${sessionScope.user.role ne 1 and disk.stored gt 0 }">
						<form action="Cart" method="post" class="input">

							<input type="hidden" name="diskId" value="${disk.id}">

							<c:if test="${disk.sellable ne 1}">
							Длительность аренды (дней):<input id="number" required="required"
									type="number" name="hireDuration" onclick="countPrice()"
									value="6" min="1" max="365">
								<p>
								
							</c:if>
							Количество: <input required="required" id="number" type="number"
								name="countOrderedDisk" onchange="countPrice()" value="1"
								min="1" max="${disk.stored}"><p>

							<c:if test="${disk.sellable ne 0}">
								
								<button type="submit" name="status" value="1">Купить</button>
							</c:if>
							<c:if test="${disk.sellable ne 1}">
							<button type="submit" name="status" value="0">Аренда</button>
							</c:if>
						</form>

					</c:if></td>
			</tr>
			<c:if test="${sessionScope.user.role ne 1 }">
				<tr>
					<td align="left" class="attent" id="attent" colspan="3"></td>
				</tr>
			</c:if>
			<c:if test="${sessionScope.user.role eq 1 }">
				<tr>
					<td>
						<form action="Disk_Redacting" class="input">
							<button type="submit" style="width: 200px;" name="redact"
								value="${disk.id}">Редактировать</button>
						</form>
					</td>
				</tr>
			</c:if>

		</table>

		<p>
		<h3>Описание</h3>
		<c:choose>
			<c:when test="${not empty disk.description}">
				<p>${disk.description}
			</c:when>
			<c:otherwise>
				<p>Нет описания
			</c:otherwise>
		</c:choose>

	</div>
	<c:if test="${sessionScope.user.role ne 1}">
		<jsp:include page="Footer.jsp"></jsp:include>
	</c:if>
	<p>
</body>
</html>