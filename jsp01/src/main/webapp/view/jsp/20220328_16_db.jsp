<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>db에 저장</h2>
	<form action="20220328_16_insert.jsp" method="post">
		아이디:<input type="text" name="userid"><br>
		비밀번호:<input type="password" name="passwd"><br>
		이름:<input type="text" name="name"><br>
		이메일:<input type="email" name="email">
		<button>가입</button>
		<!-- userid에 pk를 설정했기 때문에 돔에서 값 작성시 userid값이 일치하지 않도록 주의해야 한다 --> 
	</form>
 <hr>
 메세지 :	<%= request.getParameter("msg") %>
 </body>
</html>