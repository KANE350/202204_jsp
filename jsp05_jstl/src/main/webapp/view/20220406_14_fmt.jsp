<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		Date date = new Date();
		request.setAttribute("date", date);
		
		//SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//String d = sf.format(date);
		
	%>
	
	<h2>날짜 원하는 포맷으로</h2>
	<fmt:formatDate value="${date}" pattern="yyyy-MM-dd HH:mm:ss"/>
</body>
</html>