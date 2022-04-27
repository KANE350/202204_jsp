<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>List 출력</h2>
	<%
		List<String> list = new ArrayList<>();
		list.add("자바");
		list.add("파이썬");
		list.add("오라클");
		request.setAttribute("list", list);
	//리퀘스트 객체에 얘를 만들어서 뷰에 던져줬다고 할때 
	%>
	
	<h2>jstl과 el을 이용해서 list 출력</h2>
	<!-- 리스트를 아이템에 넣고 하나씩 풀어헤쳐서 출력해보기 -->
	<c:set var="list">"자바", "파이썬", "오라클"</c:set>
	<c:forEach var="add" items="${list}" varStatus="status">
	${status.index} ${add}<br>
	
	</c:forEach>

</body>
</html>