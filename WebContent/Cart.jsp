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

		<h2 align="center">Корзина</h2>


		<font class="attent">${attention}</font>
	</div>

	<div class="container">



		<c:choose>
			<c:when test="${empty cart}">

				<h3 align="center" style="color: #911; margin-top: 30px;">
					Корзина пуста</h3>

			</c:when>

			<c:otherwise>


				<c:set var="sum" value="${0}"></c:set>

				<c:forEach items="${cart}" var="disk">

					<div align="center">
						<form action="Cart" id="cancel" method="post"></form>
						<form action="DiskView" method="get">

							<table id="table">
								<tr>
									<td class="input">

										<button style="width: 150px" form="cancel" name="cancel"
											value="${disk.id}" type="submit">Убрать с корзины</button>

										<button style="width: 150px" name="diskId" value="${disk.id}"
											type="submit">Изменить статус</button>

									</td>

									<td colspan="2" class="title">${disk.title}</td>
								</tr>

								<tr>

									<td>Количество: <font class="title">${disk.count}</font></td>
									<td>Cумма: <font class="title">${disk.price}</font></td>

								</tr>
								<tr>

									<td colspan="3"><c:if test="${not empty disk.date}">
								Дата окончания аренды: <font class="title">${disk.formattedDate}</font>
										</c:if></td>

								</tr>

							</table>
						</form>

					</div>
					<p>
						<c:set var="sum" value="${sum + disk.price}"></c:set>
				</c:forEach>


				<font style="font-size: 16pt; color: #911; margin: 20% 0 0 60%">Сумма
					заказа: ${sum}</font>

				<div class="input">

					<form action="DiskList" style="margin-left: 60%;">

						<button style="width: 200px;" type="submit">Перейти к списку
							</button>

					</form>


					<form style="margin-left: 60%;" action="Order" method="post">
						<button style="width: 200px;" name="countSum" value="${sum}"
							type="submit">Перейти к окну заказа</button>

					</form>

				</div>
			</c:otherwise>
		</c:choose>

	</div>

	
		<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>