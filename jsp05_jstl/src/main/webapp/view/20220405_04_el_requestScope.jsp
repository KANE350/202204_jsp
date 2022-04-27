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
		/*request 범위 */
		request.setAttribute("name", "홍길동");
		request.setAttribute("age", 20);
	
	%>
	<h2>jsp 표현식</h2>
	이름:<%=request.getAttribute("name") %><br>
	나이:<%=request.getAttribute("age") %>
	<h2>EL</h2>
	이름: ${requestScope.name}<br> 
	나이: ${requestScope.age}
	<!--requestScope은 el이 만든 객체, 문법이라고 생각하면 된다. 위의 코드를 내부적으로 jsp표현식으로 바꾸어준다.  -->
	
	<h2>EL(생략가능)</h2>
	이름: ${name}<br> 
	나이: ${age}
	<!--리퀘스트 안에 있는 특정한 변수만 읽어오고 싶다면 이렇게 입력해도 된다.-->
</body>
</html>