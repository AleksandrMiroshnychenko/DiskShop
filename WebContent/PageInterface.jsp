<%@page import="entity.Genre"%>
<%@page import="entity.Actor"%>
<%@page import="dao.DiskDAO"%>
<%@page import="entity.Disk"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

			<div class="pages">

				<c:out value="${currentPage} страница из ${pageCount}"></c:out><p>


				<c:choose>

					<c:when test="${pageCount gt 6 }">

						<button name="pageButton" value="1">1</button>


						<c:set var="gLeftShiftVal" value="${currentPage - 10}"></c:set>
						<c:if test="${currentPage le 10 }">
							<c:set var="gLeftShiftVal" value="${1}" />
						</c:if>
						<button name="pageButton" value="${gLeftShiftVal}" type="submit">&#8606</button>



						<c:set var="LeftShiftVal" value="${currentPage - 1}"></c:set>

						<c:if test="${currentPage le 2 }">
							<c:set var="LeftShiftVal" value="${1}" />
						</c:if>

						<button name="pageButton" value="${LeftShiftVal}" type="submit">&#8606</button>


						<c:set var="RightShiftVal" value="${currentPage + 1}"></c:set>

						<c:if test="${currentPage eq pageCount}">
							<c:set var="RightShiftVal" value="${pageCount}" />
						</c:if>
						<button name="pageButton" value="${RightShiftVal}" type="submit">&#8608</button>


						<c:set var="gRightShiftVal" value="${currentPage - 10}"></c:set>

						<c:if test="${currentPage ge pageCount - 10}">
							<c:set var="gRightShiftVal" value="${currentPage}" />
						</c:if>

						<button name="pageButton" value="${gRightShiftVal}" type="submit">&#8606</button>


						<c:if test="${pageCount ne currentPage}">
							<button name="pageButton" value="${pageCount}" type="submit">
								<c:out value="${pageCount}" />
							</button>
						</c:if>

					</c:when>
					
					<c:when test="${pageCount eq 1}"></c:when>

					<c:otherwise>

						<c:forEach var="i" begin="1" end="${pageCount}">



							<button type="submit" name="pageButton"
								value="<c:out value="${i}" />">
								<c:out value="${i}" />
							</button>



						</c:forEach>


					</c:otherwise>

				</c:choose>

			</div>

</body>
</html>