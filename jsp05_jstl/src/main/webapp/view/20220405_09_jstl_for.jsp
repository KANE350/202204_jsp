<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 반복문을 쓰기 위해서는 위의 코어가 반드시 들어가 있어야 한다. -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2> jsp 반복문</h2>
	<%
		for(int i=0; i<10; i++){
	%>
		<%=i%>
	<% 	}	
	%>
	<h2> jstl 반복문</h2>
	<c:forEach var="i" begin="0" end="9" step="1">
		${i}
	</c:forEach>
	<hr>
	<h2>구구단</h2>
	<c:forEach var="i" begin="1" end="9" step="1">
		2*${i}=${i*2}<br>
	</c:forEach>
	<hr>
	<c:forEach var="i" begin="2" end="9" step="1">
		<c:forEach var="j" begin="1" end="9" step="1">
		${i}*${j}=${i*j}<br>
		</c:forEach>
	</c:forEach>
</body>
</html>