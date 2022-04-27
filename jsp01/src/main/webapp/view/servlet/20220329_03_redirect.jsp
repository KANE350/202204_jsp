<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h2>계산기</h2>
	<form action="/jsp01/J20220329_03">
		기호 
		<select name="sign">
			<option value="+">더하기</option>
			<option value="-">빼기</option>
			<option value="*">곱하기</option>
			<option value="/">나누기</option>
		</select>
		<hr>
		<input type="number" name="first"><br>
		<input type="number" name="second"><br>
		<button>계산</button>
	</form>
	<hr>
	결과 : <%=request.getParameter("result") %>
	
	
</body>
</html>