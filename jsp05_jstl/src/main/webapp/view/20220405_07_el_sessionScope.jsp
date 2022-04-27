<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>SessionScope</h2>
	<%
		/*jsp는 내장객체기 떄문에 객체를 따로 생성할 필요가 없다  */
		//sessionScope : session은 내장객체
		session.setAttribute("email", "hong@gmail.com");
		//requestScepe
		request.setAttribute("email", "java@gmail.com");
	%>
	
	이메일:<%=session.getAttribute("email") %>
	<h3>EL</h3>
	이메일 : ${sessionScope.email}<br>
	<h3>EL(생략 가능)</h3>	
	<!-- requestScepe와 이름이 겹칠때 requestScepe먼저 가져옴 -->
	이메일 : ${email}<br>
	<!--생략이 가능하긴 하나 리퀘스트와 함께 사용시 리퀘스트에 사용된게 먼저 나오므로 생략하지 않고 작성하는 편이 좋다. 남발하지 말자.  -->
	
	
</body>
</html>