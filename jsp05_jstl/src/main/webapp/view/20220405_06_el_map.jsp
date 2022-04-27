<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
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
		//판매 map
		Map<String, Object> map = new HashMap<>();
		map.put("pname", "새우깡");
		map.put("saleprice", 50000);
		request.setAttribute("map", map);
	%>
	<h2>jsp표현식</h2>
	<%=((Map<String, Object>)request.getAttribute("map")).get("pname") %>
	<%=((Map<String, Object>)request.getAttribute("map")).get("saleprice") %>
	
	<h2>EL 표현식</h2>
	${map.pname}<br>
	${map.saleprice}<br>
	<!--리퀘스트 스코프가 있지만 생략되어 있는 상태 (requestScope.map.pname)  -->
</body>
</html>