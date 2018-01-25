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
<script type="text/javascript" src="js/jquery-2.1.4.js"></script>
<script type="text/javascript">
	
<%@ include file="js/orderList.js"%>
	
</script>
<%@ include file="css/Cart.css"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

	<jsp:include page="Interact.jsp"></jsp:include>


	<div class="headers">

		<h2 align="center">Заказы</h2>

		<h3 align="center">Список Заказов</h3>

		<font class="attent">${attention}</font>
	</div>

	<div class="container">

		<c:choose>
			<c:when test="${empty orders}">

				<h3 align="center" style="color: #911; margin-top: 30px;">
					Заказов нет</h3>

			</c:when>
			<c:when
				test="${sessionScope.user.role ne 0 and empty sessionScope.user }">
				<h3 align="center" style="color: #911; margin-top: 30px;">У вас
					нет доступа к этой странице</h3>

			</c:when>

			<c:otherwise>

				<c:forEach items="${orders}" var="order" varStatus="loop">

					<form action="UserOrders" method="post">

						<table align="center" id="order" class="${loop.index}">
							<tr id="firs" class="${loop.count}">

								<td colspan="3" class="title">${order.name}</td>
							</tr>
							<tr>
								<td style="width: 200px;">Телефон: <font class="title">${order.phone}</font></td>
								<td colspan="2">E-mail: <font class="title">${order.email}</font></td>

							</tr>
							<tr>

								<td style="width: 300px;">Дата подтверждения: <c:if
										test="${empty order.confirmDate}">
										<font color="red">заказ не подтвержден</font>
									</c:if> ${order.formattedConfirmDate}<%--to  --%>
								</td>

								<c:if test="${not empty order.cancelDate}">

									<td style="text-align: left; width: 300px;">Дата отмены
										заказа: ${order.formattedCancelDate}</td>

								</c:if>

							</tr>
							<c:if test="${not empty order.earnDate}">

								<tr>
									<td>Дата оплаты: ${order.formattedEarnDate}</td>
								</tr>
							</c:if>

							<tr>
								<td>Последнее изменение: ${order.formattedUpdateDate}</td>
								<td>Нужна доставка: <font class="title"> <c:if
											test="${order.sendable eq 1}">Да</c:if> <c:if
											test="${order.sendable ne 1}">Нет</c:if>
								</font></td>
							</tr>

							<c:if test="${not empty order.delivery.requestDate }">
								<tr>
									<td>Дата отправки: ${order.delivery.formattedRequestDate}</td>
								</tr>
							</c:if>

							<c:if test="${not empty order.cart}">
								<tr>
									<td style="text-align: center;" colspan="3" class="title">

										Корзина
										<hr style="color: #bbb;">

									</td>
								</tr>

								<c:forEach var="cart" items="${order.cart}">


									<tr>
										<td class="title" style="text-align: center;" colspan="3"><a
											href="http://localhost:8080/Course_Demo-1/DiskView?diskId=${cart.id}"
											color="#911">Книга: ${cart.title}</a> <font
											style="float: right;">Количество: ${cart.count}</font></td>
									</tr>
									<c:choose>
										<c:when test="${empty cart.date}">

											<tr>
												<td colspan="3"><font class="title"
													style="margin-right: 30px" color="red"> Покупка </font>
													<hr style="color: #bbb;"></td>
											</tr>

										</c:when>
										<c:otherwise>
											<tr>
												<td colspan="2">Окончание аренды: ${cart.formattedDate}
													<c:if test="${not empty order.confirmDate}">

														<td>Книга возвращена: ${cart.formattedFactReturnDate}<c:if
																test="${empty cart.factReturnDate}"> нет
										</c:if></td>

													</c:if>

												</td>
											</tr>

										</c:otherwise>

									</c:choose>

								</c:forEach>

							</c:if>

						</table>
					</form>

				</c:forEach>

			</c:otherwise>
		</c:choose>

	</div>


	<jsp:include page="Footer.jsp"></jsp:include>

</body>
</html>