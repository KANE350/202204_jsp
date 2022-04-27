<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>직업선택</h2>
	<form action="">
	<select name="job">
	
	<option> 웹 프로그래머 </option>
	<option> DB관리자 </option>
	<option> 시스템 관리자 </option>
	<option> AI프로그래머 </option>
	</select>
	<button>선택</button>
	</form>
	<hr>
	
	<% 
		String job = request.getParameter("job");
		if(job !=null){					
	%>
		당신이 선택한 직업은 <%=job%>
	<%} 
	%>
		
</body>
</html>