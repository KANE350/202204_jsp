<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>결과창</h2>
<!-- 	당신은 21살입니다
	성인입니다.
 -->
 	당신은 <%=request.getAttribute("age") %>살 입니다. <br>
 	   	<%=request.getAttribute("result") %>
</body>
</html>