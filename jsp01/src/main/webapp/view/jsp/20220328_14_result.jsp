<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>리다이렉트 결과창</h2>
	<!-- url에서 읽어야 하기 떄문에 getParameter사용 -->
	아이디 : <%=request.getParameter("userid") %><br>
	출생년도: <%=request.getParameter("birthYear") %>
	
</body>
</html>