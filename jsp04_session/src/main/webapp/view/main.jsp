<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	//세션의 userid체크
	if ('<%=session.getAttribute("userid")%>' == 'null'){
		alert('로그인 후 접근 가능');
		location.href = '/jsp04_session/view/login.jsp';
	}
	

</script>
</head>
<body>
	<h2>회원관리</h2>
	<!-- 세션의 id -->
	<%=session.getId() %>
	<hr>
	<!-- session의 연결을 끊기전까지 변수호출 가능 -->
	<!-- 세션scope에 있는 변수 읽기 -->
	<%=session.getAttribute("userid") %><br>
	<%=session.getAttribute("passwd") %>
	
	<button onclick="location.href='/jsp04_session/logout.log'">로그아웃</button>
	<hr>
	<a href="">회원리스트</a>
	
</body>
</html>