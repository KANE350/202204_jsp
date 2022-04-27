<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>EL표현 언어</h2>
	<%=10+20%><br>
	덧셈 : ${10+20}<br> 
	<!-- 	위의 표현식을 $로, EL표현언어로 작성한 것이다.-->	
	
	뺄셈 : ${10-20}<br> 
	곱셈 : ${10*20}<br> 
	나눗셈 : ${10/20}<br> 
	나머지 : ${10%20}<br> 
	나머지 : ${10 mod 3}<br>
	<hr>
	${10==10}<br>
	${10 eq 10}<br>  <!--둘이 같은 식이다 -->
	
	
	<%
		pageContext.setAttribute("title", "EL테스트");
	%>
	
	${title != null}
	${not empty title}
	
	<hr>
	${20>10?"크다":"작다"}
	
	
	
	
</body>
</html>