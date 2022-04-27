<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>상품판매</h2>
	<form action="/jsp01/J20220329_04">
		상품명:<input type="text" name="pname"><br>
		단가:<input type="number" name="price"><br>
		판매수량:<input type="number" name="qty"><br>
		<button>계산</button>
	</form>
	<hr>
	판매금액:<%=request.getParameter("amount") %>
	
</body>
</html>