<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>데이터 전송(post)</h2>
	<!-- method="get" : 생략가능 
		 post : request객체에 담아서 전송(url에 담기지 않는다)-->
		
	<form action="" method="post">
	<!-- post는 정보가 url에 보이지 않고 request객체에 담겨서 내부적으로 전송된다 -->
		아이디 <input type="text" name="userid"> <br>
		비밀번호 <input type="password" name="passwd"> <br>
		<button>로그인</button>
	</form>
	<% 
		String	userid = request.getParameter("userid");
		String	passwd = request.getParameter("passwd");
		if (userid!=null){
	%>			
		<fieldset>
			<legend>로그인정보</legend>
			아이디 : <%=userid%><br>
			비밀번호 : <%=passwd%><br>
		</fieldset>	
	<% }
	%>
	
	
	
	
</body>
</html>