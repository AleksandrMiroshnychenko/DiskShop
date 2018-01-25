<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="css/navBar.css"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${sessionScope.user.role eq 0 and not empty sessionScope.user}">
<a href = "UserOrders" class = "c"> Список заказов </a>
</c:if>
<c:set var = "cont" value="/Course_Demo-1/Cart.jsp"></c:set>
<c:if test="${sessionScope.user.role eq 1 }">
<a href = "Orders" class ="c"> Список заказов </a>
<a href = "Disk_Administration" class="c">Добавление Диска</a>
</c:if>
<a href = "DiskList" class="c">Список дисков</a>
<c:if test="${not empty sessionScope.cart and pageContext.request.requestURI ne cont}">
<a href = "Cart" class ="c">Корзина</a>
</c:if>
</body>
</html>