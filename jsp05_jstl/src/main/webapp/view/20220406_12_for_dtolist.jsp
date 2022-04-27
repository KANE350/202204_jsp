<%@page import="java.util.ArrayList"%>
<%@page import="dto.Member"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		List<Member> mlist = new ArrayList<>();
		mlist.add(new Member("java", "1111", "박자바", "java@gmail.com"));
		mlist.add(new Member("hong", "2222", "홍길동", "hong@gmail.com"));
		request.setAttribute("mlist", mlist);
		/*얘를 가져가는 셈이다. 밑의 리퀘스트 스코프는 생략된 상태 */
		
	%>
	<%-- <%=mlist%> --%>
	
	<h2>EL(dto의 list)</h2>
	<!-- requestScope범위에 담긴 변수 --> 
	${mlist}
	<hr>
	<table border="1">
		<thead>
			<tr>
				<th>아이디</th>
				<th>비밀번호</th>
				<th>이름</th>
				<th>이메일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="member" items="${mlist}">
				<tr>
					<td>${member.userid}</td>
					<td>${member.passwd}</td>
					<td>${member.name}</td>
					<td>${member.email}</td>
				</tr>
		<!--얘만 쓰면 출력이 안된다. 반드시 내용물을 리퀘스트 스코프에 담아줘야 출력된다. 
		.이라는 도트연산자를 사용해서 게터로 내용만 호출했다  -->
			</c:forEach>
		</tbody>		
	</table>
	
</body>
</html>