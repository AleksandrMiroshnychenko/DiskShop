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
<script type="text/javascript"
	src="js/jquery-2.1.4.js"></script>
<script charset="utf-8" type="text/javascript"><%@ include file="js/order.js"%></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

	<jsp:include page="Interact.jsp"></jsp:include>


	<div class="headers">

		<h2 align="center">Оформление заказа</h2>

		<h3 align="center"></h3>

		<font class="attent">${attention}</font>
	</div>

	<div class="container">

		<c:choose>
			<c:when test="${empty sessionScope.cart}">

				<h3 align="center" style="color: #911; margin-top: 30px;">
					Заказ пуст</h3>

			</c:when>
			<c:otherwise>
					<form class="input" onsubmit="return toOrder(); return toDelivery();" action="Success" method="post">

				<div align="center">



						<table id="checkCol" class="input">
							<tr>

								<td>Фамилия:<br> <input required="required" id="firstname" type="text" name="lastname"></td>

							</tr>
							<tr>

								<td>Имя:<br> <input required="required" id="lastname" type="text" name="firstname"></td>

							</tr>
							<tr>

								<td>Отчество:<br> <input required="required" id="surname" type="text" name="surname"></td>

							</tr>

							<tr>

								<td>Номер телефона:<br> <input required="required" id="phone" type="text" name="phone"></td>

							</tr>
							<tr>

								<td>E-mail:<br><input value="${sessionScope.user.email}" id="mail" required="required" type="text" name="email"></td>

							</tr>
							<tr><td id="check"> Доставка:<input style="width: 20px;" type="checkbox" value="1" name="sendable" ></td></tr>
							<tr class='temporary'><td >Область/регион<br> <input required='required' id = "region" type='text' name='region'></td></tr>
				<tr class='temporary'><td >Город:<br> <input required='required' id = "city" type='text' name='city'></td></tr>
				<tr class='temporary'><td>Адрес:<br> <input required='required' id = "address" type='text' name='address'></td></tr>
				<tr class='temporary'><td>Почтовый индекс:<br> <input required='required' id = "postIndex" type='text' name='postIndex'></td></tr>
				<tr class='temporary'><td colspan='2'>стоимость доставки: ${deliveryPrice}</td></tr>
						</table>
<font id="attent"></font>
				</div>
				<p>
					<c:set var="sum" value="${sum + disk.price}"></c:set>


					<font id="message" style="font-size: 16pt; color: #911; margin: 20% 0 0 60%"></font>

					<button  style="width: 200px;" type="submit">Заказать</button>

				</form>

			</c:otherwise>
		</c:choose>

	</div>
		<div style="position: relative; height: 30%;">
		<jsp:include page="Footer.jsp"></jsp:include>
	</div>
</body>
</html>