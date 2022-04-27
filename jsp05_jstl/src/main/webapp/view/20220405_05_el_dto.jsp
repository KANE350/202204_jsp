<%@page import="dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% 
		Member member = new Member("java", "1111", "박자바", "java@gmail.com");
		request.setAttribute("member", member);
	%>
	<h2>jsp표현식으로 꺼내오기</h2>
	<%=((Member)request.getAttribute("member")).getUserid()%><br>
	<%=((Member)request.getAttribute("member")).getPasswd()%><br>
	<%=((Member)request.getAttribute("member")).getEmail()%><br>
	<%=((Member)request.getAttribute("member")).getName()%><br>
	
	<h2>EL</h2>
	<!-- getter 실행 -->
	${member.userid}<br>
	${member.passwd}<br>
	${member.email}<br>
	${member.name}<br>
	<!--이렇게 쓴다고 해서 게터가 실행이 안되는게 아니다. 그저 이런식으로 변환하여 나타내는 것 뿐이다. 여기서 에러나면 게터가 없다는 것. -->
</body>
</html>