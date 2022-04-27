<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>배열변수 출력</h2>
	<!-- 배열변수 만들기  -->
	<!-- String[] arr = {"one","two", "tree", "four", "five"}; -->
	<c:set var="arr">one,two, tree, four, five </c:set>
	${arr}
	<hr>
	<c:forEach var="eng" items="${arr}" varStatus="status">
	<!-- arr 이라는 변수를 가져오는 것이기 때문에 el태그를 붙인다-->
	<!-- varstatus를 붙이면 for문 안에서 상태값을 갖고 있는 변수명이 된다 -->
		${status.index} ${status.count} ${eng}<br>
		<!--{status.index}를 붙이면 인덱스 번호도 가져올 수 있다.   -->
		<!--${status.count}는 인덱스 번호가 아닌 카운터를 보여준다  -->
	</c:forEach>
</body>
</html>