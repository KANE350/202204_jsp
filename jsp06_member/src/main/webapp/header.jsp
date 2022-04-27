<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header>
		<div>
		<h2>회원관리</h2>
		</div>
		<div>
		<c:if test="${not empty sessionScope.userid}">
		<a href="${path}/info.member?userid=${sessionScope.userid}" >${sessionScope.userid}</a>님 환영합니다.
		<a href="${path}/logout.member">로그아웃</a>
 		</c:if>	

		<c:if test="${empty sessionScope.userid}">
		<a href="${path}/login.jsp">로그인</a>
 		</c:if>
 		</div>
	</header>
	<hr>
	<nav>
		<a href="${path}/main.jsp">홈</a>
		<a href="${path}/member/join.jsp">회원가입</a>
		<a href="${path}/list.member">리스트</a>
	</nav>
	<hr>
</body>
</html>