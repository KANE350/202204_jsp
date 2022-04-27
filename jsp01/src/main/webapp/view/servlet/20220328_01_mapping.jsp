<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>서블릿 매핑</h2>
	<!-- contextpath + webservlet의 매핑정보를 적어주어야 한다 여기서는 다른 프로젝트를 호출할 수도 있지만
	자바에서는 불가하기 때문에 여기서만 컨텍스트패스 작성 가능하다.-->
	<!--/이 붙으면 절대경로 ..이 붙거나 아무것도 안쓰면 상대경로-->
	<!-- 절대경로: contextpath부터 :/jsp01/J20220328_01-->
	<form action="/jsp01/J20220328_01" method="post">
	이름 : <input type="text" name="name"><br>
	나이 : <input type="number" name="age"><br>
		<button>전달</button>	
	</form>
	
	<%=request.getAttribute("name")%> 환영합니다.<br>
	<%=request.getAttribute("age") %> 살
</body>
</html>