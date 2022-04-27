<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>좋아하는 칼라 결과창</h2>
	<% 
		//post방식일 때애 request객체의 인코딩 방식을 utf-8로 바꿔주어야 한다
		request.setCharacterEncoding("utf-8");
		String favColor = request.getParameter	("favColor");
	%>
	
	당신이 가장 좋아하는 컬러는:<%=favColor%>
</body>
</html>