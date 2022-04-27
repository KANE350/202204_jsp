<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>forward방식 이동</h2>
	<%
		String name = "hong gil dong";
		int age = 25;
		//request객체에 정보를 담는다
		//모든 형을 전송 가능(대량의 정보를 전송 가능하다. 주소는 바뀌지 않는다.)
		//(setAttributes는 첫번쨰거는 무조건 문자열, 뒤에 있는 애는 형이 오브젝트기 떄문에 자바형으로 된 애는 무엇이든 담을 수 있다. 무조건 문자형만 되는 파라미터와는 다르다)
		request.setAttribute("name", name);	
		request.setAttribute("age", age);
		
		/*리퀘스트 객체 안에 있는 forward방식으로 이동*/
		//경로변경이 안된다
		//1)이동경로의 정보 획득
		RequestDispatcher rd = request.getRequestDispatcher("20220328_11_result.jsp");
		//2)forward
		/* setAttribute와 request를 합쳐서 아래에서 응답폼을 만들어서 보내준다 */
		rd.forward(request, response);
	%>
</body>
</html>