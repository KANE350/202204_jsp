<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>사각형의 넓이 결과창</h2>
	<%	
		//래퍼클래스를 이용한 형변환:문자열->정수로 변환
		int whidth = Integer.parseInt(request.getParameter("whidth"));
		int heiget = Integer.parseInt(request.getParameter("heiget"));
	%>
	
	사각형의 넓이:<%=whidth*heiget%>
</body>
</html>
