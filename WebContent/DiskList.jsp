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
<title>Список Дисков</title>
</head>
<body>

	<jsp:include page="Interact.jsp"></jsp:include>

	<div class="headers" >

		<h2 align="center">Главная</h2>

		<h3 align="center">Список дисков</h3>

		<font class="attent">${attention}</font>
	</div>

	<div class="container">
		<div class="wrapper" rel="main" align="center">
			<c:set var="counter" value="${0}" />
			<c:set var="disks" value="${disks}" scope="page" />
			<c:forEach items="${disks}" var="disk">

				<div class="disklist" align="center">
					<table >
						<tr>

							<td id="title" class="title">

									<a href="http://localhost:8080/Course_Demo-1/DiskView?diskId=${disk.id}"
										color="#911"> <c:out value="${disk.title}" /></a>

							</td>
						</tr>
						

						<tr>

							<td id="price" colspan="2"><c:out
									value="Стоимость ${disk.price}"></c:out></td>


						</tr>
						<tr>
							<td id="release" colspan="2"><c:out
									value="Год выпуска ${disk.releaseDate}"></c:out></td>
						</tr>
						<tr>

							<td id="sellable"><c:choose>
									<c:when test="${disk.sellable eq 1}">
						
									Только продажа
									
									</c:when>
									<c:when test="${disk.sellable eq 2}">
						
									Продажа и аренда
									
									</c:when>
									<c:otherwise>
	
									Только аренда
									
								</c:otherwise>
								</c:choose></td>
						</tr>

						<tr>
							<td class="tags"><c:set var="actors"
									value="${disk.actors.actorNameBatch }" /> <c:set var="asize"
									value="${fn:length(disk.actors.actorIdBatch)}"></c:set> <c:choose>

									<c:when test="${asize eq 1}">
										<c:forEach var="actor" items="${actors}">
											<c:out value="Актер: ${actor}." />
										</c:forEach>
									</c:when>

									<c:when test="${asize eq 0}">
							Нет актеров
							</c:when>

									<c:otherwise>
										<c:set var="count" value="1"></c:set>
									Актеры: 
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
							<td><c:set var="genres" value="${disk.genre.genreBatch}" />
								<c:set var="gsize" value="${fn:length(disk.genre.genreIdBatch)}"></c:set>
								<c:choose>

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

					</table>
					<c:if test="${sessionScope.user.role eq 1}">
						<form action="DiskList" method="post" class="input" >
							<input  type="hidden" name="pageButton" value="${currentPage}">
							<button name="del" style="width: 150px; " value="${disk.id}">Удалить
								</button>
						</form>
					</c:if>
				</div>
				<c:if test="${counter mod 2 eq 1 }"><p></c:if>

				<c:set var="counter" value="${counter + 1}" />
			</c:forEach>
		</div>
		<form action="DiskList">

			<%@ include file="PageInterface.jsp"%>

		</form><p><p>
	<c:if test="${sessionScope.user.role ne 1}">
		<jsp:include page="Footer.jsp"></jsp:include>
	</c:if>
	</div>


</body>
</html>