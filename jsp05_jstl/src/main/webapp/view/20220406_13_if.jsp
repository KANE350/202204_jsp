<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>if문</h2>
	<c:set var="age" value="18"/>
<%-- 	<%
		//if (age >19){}
	%>
--%>
	<!-- test는 조건문 : true/false -->
 	<c:if test="${age>19}">
 		<h6>성인입니다.</h6> 
 	</c:if>
 	<c:if test="${age<=19}">
 		<h6>미성년자입니다.</h6>
 	</c:if>
 	
 	<!-- 여러개의 조건 -->
 	<h2>choose문</h2>
 	<c:choose>
 		<c:when test="${age>19}">
 			<h6>성인입니다.</h6> 
 		</c:when>
 		<c:when test="${age>13}">
 			<h6>청소년입니다.</h6>
 		</c:when>
 		<c:otherwise>
 			<h6>어린이입니다.</h6>
 		</c:otherwise>
 	</c:choose>
 	
 	
 	
 	
 	
 	
 	
 	
 	
</body>
</html>