<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>jsp파라메터</h2>
	아이디:<%=request.getParameter("userid") %><br>
	이름:<%=request.getParameter("name") %>
	<h2>el파라메터</h2>
	${param.userid}<br>
	${param.name}
	<!--파라미터로 읽을 떄에는 jsp파라미터처럼 적어도 되지만 위와 같이 적는 편이 더 간편하다. 뷰를 보면 동일하게 출력된 것을 알 수 있다.   -->
</body>
</html>