<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>회원가입 정보</h2>
	<%
/* 	아이디, 비밀번호, 이메일, 이름, 성별(radio), 가입경로(select), 
	알바가능시간대(checkbox):새벽,오전,오후,저녁
 */		
		//post방식일경우 인코딩 utf-8
		request.setCharacterEncoding("utf-8");
 		String userid = request.getParameter("userid");
		String passwd = request.getParameter("passwd");
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String route = request.getParameter("route");
		/* 가능시간대는 타임이 여러개기 때문에 벨류스로 읽어야 한다. 배열로 가져온다. */
		String[] times = request.getParameterValues("time");
	%>
	아이디: <%=userid%> <br>
	비밀번호: <%=passwd%> <br>
	이메일: <%=email%> <br>
	이름:  <%=name%> <br>
	성별:   <%=gender%> <br>
	가입경로: <%=route%> <br>
	가능시간대:
<%--for문으로 표현할 경우 	
	<%
		for(int i=0; i<times.length; i++){
						
	%>
		<%=times[i]%>
	<% }
	%> --%>
	<!-- NullPointerException 객체가 만들어지지 않았다는 오류-->
	<!-- for-each문으로 표현할 경우 -->
	<%
		for(String time:times){
						
	%>
		<%=time%>
	<% }
	%>
	

</body>
</html>