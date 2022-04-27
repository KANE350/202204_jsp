<!-- 결과창 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>원의 넓이 결과창</h2>
	<%
		double area = (double)request.getAttribute("area");
	%>
	원의 넓이는 <%=area %>
</body>
</html>