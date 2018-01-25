<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New Interact page</title>
<%@ include file="css/Styles.css"%>

</head>
<body>
	<div class="topPanel">

		<jsp:include page="Navigation.jsp"></jsp:include>

		<c:choose>
			<c:when test="${empty sessionScope.user.nickname}">

				<form method="post" action="AuthorizateServlet"
					style="float: right; margin-right: 2vw;" class="input">

					<button style="margin: 0 5px;" type="submit" name="sendName">
						Sign in</button>
					Login <input required type="text" name="login"> Password <input
						required type="password" name="password">

				</form>
				<form action="RegisterServ" style="float: right;" class="input">
					<input type="hidden" name="registration">
					<button style="margin: 0 15px;" type="submit" name="toRegistr">
						Registration</button>
				</form>
			</c:when>

			<c:otherwise>
				<form action="Logout" style="float: right; margin-right: 50px;"
					class="input">

					<button style="margin: 0 5px;" type="submit" name="tologout">
						Sign out</button>

				</form>
				<font style="float: right; margin: 0 5px;"><c:out
						value="${sessionScope.user.nickname}"></c:out> </font>
			</c:otherwise>
			
		</c:choose>

		<form action="DiskList" style="float: right;" class="input">
			<input type="text" placeholder="Поиск" style="width: 150px;"
				name="searchQuery">
		</form>
	</div>


</body>
</html>