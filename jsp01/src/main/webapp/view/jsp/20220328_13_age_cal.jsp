<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% 
		//성인, 미성년자 체크
		/* 입력받은 값을 인티저로 바꾼다 */
		int age = Integer.parseInt(request.getParameter("age"));
		String result; //성인, 미성년자를 저장할 변수		
		if(age>19){
			result="성인입니다";
		}else{
			result="미성년자입니다";
		}
		//데이터를 result에 담기
		request.setAttribute("age", age);
		request.setAttribute("result", result);
		//결과창으로 이동
		//위의 결과를 요청했는데 응답보내고 싶은 정보(20220318_14_result)의 정보를 획득하여 
		//리스폰스 객체와 리퀘스트객체를 이용하여 응답할 정보를 취합하여 보낸다.  
		request.getRequestDispatcher("20220328_13_result.jsp")
			.forward(request, response);
		
	%>
</body>
</html>