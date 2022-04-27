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
		<h1>쇼핑몰 회원관리 ver1.0</h1>
	</header>
	<nav>
		<a href="/jsp03_hrd/view/memberAdd.jsp">회원등록</a>
		<!-- 상대경로로 쓰면 주소이탈이 일어날 수 있다.  반드시 절대경로로 진행하게끔 한다.  -->
		<a href="/jsp03_hrd/member/list">회원등록조회/수정</a>
		<!-- 컨텍스트패스를 맨 앞에 붙이고 컨테인스해서 리스트가 있다면 데이터를 조회해서 제이에스피로 가면 된다(버튼이 없기 때문에 서블릿을 바로 호출)  -->
		<a href="/jsp03_hrd/money/list">회원매출조회</a>
		<a href="/jsp03_hrd/view/index.jsp">홈으로</a>
	</nav>
	<hr>
</body>
</html>	