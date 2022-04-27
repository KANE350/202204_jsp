<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>jsp cookie</h2>
	<%
		//쿠키를 읽기
		Cookie[] cookies = request.getCookies();
		if (cookies != null){
			for(Cookie cookie:cookies){
				out.print(cookie.getName() +":"+ cookie.getValue());
			}
		}
	
	%>
	<h2>EL cookie</h2>
	${cookie.JSESSIONID.value}
</body>
</html>