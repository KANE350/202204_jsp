<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>사용 가능한 언어</h2>
	<form action="">
	<fieldset>		
		<input type="checkbox" name="language" value="자바">자바
		<input type="checkbox" name="language" value="파이썬">파이썬
		<input type="checkbox" name="language"value="HTML5">HTML5
		<input type="checkbox" name="language"value="JAVASCRIPT">JAVASCRIPT
		<input type="checkbox" name="language"value="JSP">JSP				
	</fieldset>
	<button>선택</button>
	</form>
	<hr>
	<!-- 여러개를 넘겨야 하므로 벨류를 사용하면 문자열의 배열을 넘겨준다 -->
	
	
	<%
		String[] langs = request.getParameterValues("language");
		if (langs != null){
	%>		
		당신의 사용 가능 언어는
		
	<% 	for(String lang:langs){
	%>		
		<%=lang%>
	<%		}
		}
	
	
	%>
	
	
</body>
</html>