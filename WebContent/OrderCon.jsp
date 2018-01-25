<%@page import="entity.Genre"%>
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
	function Subfunc() {
		getElementById("check").submit();
	}
<%@ include file="js/orderList.js"%>
	
</script>


<%@ include file="css/Cart.css"%>
<%@ include file="css/diskList.css"%>

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
		<div align="center">
			<form id="check" action="Orders">
				<c:choose>

					<c:when test="${notConfirmed eq false}">
						<label> Отобразить подтвержденные заказы <input
							onchange="submit()" type="checkbox" name="notConfirmed"
							value="true"></label>
					</c:when>

					<c:otherwise>
						<label> Отобразить подтвержденные заказы <input
							onchange="submit()" checked="checked" type="checkbox"
							name="notConfirmed" value="false"></label>
					</c:otherwise>

				</c:choose>
			</form>
		</div>
		<c:choose>
			<c:when test="${empty orders}">

				<h3 align="center" style="color: #911; margin-top: 30px;">
					Заказов нет</h3>

			</c:when>

			<c:otherwise>

				<c:forEach items="${orders}" var="order">

					<form action="Orders" method="post">

						<table align="center" id="order">
							<tr>

								<td class="input" style="text-align: center;" colspan="3">
									<input type="hidden" value="${notConfirmed}"> <input
									type="hidden" name="orderId" value="${order.id}"> <c:if
										test="${empty order.cancelDate }">



										<c:if test="${empty order.confirmDate }">

											<button name="option" value="1" style="width: 170px">Подтвердить</button>

										</c:if>
										<c:if test="${ empty order.confirmDate}">

											<button name="option" value="2" style="width: 170px">Отменить</button>


										</c:if>
										<c:if
											test="${ empty order.earnDate and not empty order.confirmDate}">
											<button name="option" value="4" style="width: 170px">Подтвердить
												оплату</button>


										</c:if>
										<c:if
											test="${order.sendable eq 1 and empty order.delivery.requestDate
											 and not empty order.confirmDate}">


											<button name="option" value="3" style="width: 170px">Подтвердить
												отправку</button>

										</c:if>
									</c:if> <c:if test="${not empty order.cancelDate}">


										<button name="option" value="5" style="width: 170px">Возобновить
											заказ</button>


									</c:if> <c:if test="${not empty order.cancelDate}">

										<button name="option" value="6" style="width: 170px">Удалить
											заказ</button>
									</c:if>
								</td>
							</tr>
							<tr id="firs">

								<td colspan="3" class="title"><font style="float: left;">Id:
										${order.id}</font> ${order.name}</td>
							</tr>

							<tr>
								<td style="width: 200px;">Телефон: <font class="title">${order.phone}</font></td>
								<td colspan="2">E-mail: <font class="title">${order.email}</font></td>

							</tr>
							<tr>

								<td style="width: 300px;">Дата подтверждения: <c:if
										test="${empty order.confirmDate}">
										<font color="red">заказ не подтвержден</font>
									</c:if> ${order.formattedConfirmDate}
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
							<c:if test="${order.sendable eq 1}">
								<tr>
									<td colspan="3">Адрес: ${order.delivery.region},
										${order.delivery.city}, ${order.delivery.address}, Индекс
										${order.delivery.postIndex}</td>
								</tr>

							</c:if>

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
										<td class="form" style="text-align: center;" colspan="3"><a
											href="http://localhost:8080/Course_Demo-1/DiskView?diskId=${cart.id}"
											color="#911"> <c:out value="${cart.title}" /></a> <font
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
											<c:if
												test="${empty cart.factReturnDate and not empty order.earnDate }">
												<tr>

													<td class="input" colspan="3"><input type="hidden"
														value="${notConfirmed}">
														<button style="width: 200px;" name="cartId"
															value="${cart.id}">Отметить возврат книги</button></td>
												</tr>
											</c:if>
											<tr>
												<td colspan="2">Окончание аренды: ${cart.formattedDate}
													<c:if test="${not empty order.confirmDate}">

														<td>Диск возвращен: ${cart.factReturnDate}<c:if
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
		<form action="Orders">
			<input value="${notConfirmed}" type="hidden" name="notConfirmed">
			<%@ include file="PageInterface.jsp"%>
		</form>
	</div>
</body>
</html>