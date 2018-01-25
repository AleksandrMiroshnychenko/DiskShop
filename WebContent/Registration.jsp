<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Регистрация</title>
<%@ include file="css/Styles.css"%>
<script charset="utf-8" type="text/javascript"><%@ include file="js/registrer.js"%></script>

</head>

<!--<jsp:include page="Interact.jsp" />-->
<body>

	<jsp:include page="Interact.jsp"></jsp:include>
	<div class="container">

		<h2 align="center">Регистрация</h2>

		<form method="post" onsubmit="return Validate();" id="registr"
			action="RegisterServ">

			<table align="center" class="input">

				<tr>
					<td>Логин<br> <input required id="login" type="text"
						name="login" /></td>
				</tr>

				<tr>
					<td>Пароль<br> <input required id="password"
						type="password" name="password" /></td>
				</tr>

				<tr>
					<td>Подтвердите пароль<br> <input required id="password2"
						type="password" name="password2" /></td>
				</tr>

				<tr>
					<td>E-mail<br> <input id="email" required type="text"
						name="email" /></td>
				</tr>

				<tr>
					<td colspan="2"><button id="submit" type="submit"
							style="width: 150px;">Регистрация</button></td>
				</tr>
				<tr>
					<td colspan="2"><font id="attent">${attention}</font></td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>