<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>리다이렉트</h2>
	<%
		String userid = "java";
		int birthYear = 2006;
		//주소이동
		//주소도 바뀌고 화면도 바뀐다. 
		/* redirect는 실행하면 주소가 이동된 것을 볼 수 있다. html이 아니라 자바코드로 인해서 주소이동 */
		//(데이터를 위와 같이 가져가려면 변경해주어야 한다.)
		//문제점은, 대량의 데이터 전송이 안된다. url로 보내야 하기 때문에 아래와 같이 소량의 문자만 가능하다. 
		response.sendRedirect("20220328_14_result.jsp?userid="+userid + "&birthYear=" + birthYear);
	
	%>
</body>
</html>