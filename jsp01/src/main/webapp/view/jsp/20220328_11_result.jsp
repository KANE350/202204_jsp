<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>forward 결과창</h2>
	<%
		//request객체의 정보 읽기 
		String name = (String)request.getAttribute("name");
		int age = (int)request.getAttribute("age");
		/* 바로 자동형변환이 되기 때문에 굳이 레퍼클레스를 사용하지 않아도 된다. */
		/*오브젝트와 자식부모관계이기 떄문에 가능하다 */
	%>
	이름:<%=name %> <br>
	나이:<%=age %>
</body>
</html>