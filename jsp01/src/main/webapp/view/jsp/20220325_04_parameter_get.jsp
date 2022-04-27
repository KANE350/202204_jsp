<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>요청 파라미터 정보(get)</h2>
<!-- 	전송 메소드 : get (데이터 전송을 usl에 담아서)
	 		   post (request내부 객체에 담아서) 
-->
	 		   
		<form action="" method="get">
		<!-- 메소드 안에 아무것도 넣지 않으면 디폴트가 겟방식이다 -->	
		이름: <input type="text" name="name"><br>
		나이: <input type="number" name="age"><br>
		<!--form submit기능을 가진 버튼 누르면, action값을 요청한다:url(공백이면 자기 자신의 폼을 요청한다) 
			form안의 name의 정보를 request(get일때는 url에 담아 보내고,post일때는 객체에 담는다)객체에 담아서 보낸다-->
		<button>전송</button>
		</form>


	<% 
		//스크립틀릿(servlet):서버
		/* 홈페이지에 입력한 이름을 읽어서 반환 */
		String name = request.getParameter("name");
		/* 홈페이지에 입력한 나이를 읽어서 반환 저쪽에선 숫자로 인식을 못하기 때문에 스트링으로 입력받는다*/
		String age = request.getParameter("age");
	%>
	<!-- 홈페이지에 뿌리기 -->
	이름 : <%=name%> <br>
	나이 : <%=age%> <br>
	
	</body>
	</html>