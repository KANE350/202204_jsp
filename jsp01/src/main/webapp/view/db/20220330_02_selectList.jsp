<%@page import="s02_member.Member"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<h2>회원리스트</h2>
	<!-- 전송하는게 없기 때문에 form으로 감싸지 않는다. 떄문에 button에 서블릿도없는 상태 -->
	<button onclick="location.href='/jsp01/member/selectList';">조회</button>	
	<!-- 	/* 원래는 폼의 액션에 들어가는걸 여기에 넣는다고 생각하면 된다 */ -->
	<!--조회 버튼을 누르고 /jsp01/member/selectList문장이 콘솔에 뜨는지. 즉 연결됐는지 확인  -->
	<!-- 함수 호출할시 매개변수공간() 반드시 넣기 -->
	<button onclick="location.href='/jsp01/view/db/20220329_01_insert.jsp'">가입</button>
	<table border="1">
		<tr>
			<th>아이디</th>
			<th>비밀번호</th>
			<th>이름</th>
			<th>이메일</th>
		</tr>
	<%
		//다운캐스팅
		List<Member> mlist = (List<Member>)request.getAttribute("mlist");
		if (mlist != null){
			for(int i=0; i< mlist.size(); i++){
	%>
<tr>
					<td><a href="/jsp01/member/modify?userid=<%=mlist.get(i).getUserid()%>">
						<%=mlist.get(i).getUserid()%></a>
					</td>
					<td><%=mlist.get(i).getPasswd()%></td>
					<td><%=mlist.get(i).getName()%></td>
					<td><%=mlist.get(i).getEmail()%></td>
				</tr>		
	<%		}
		}
	%>
	
	</table>
	
	<%
	
	
	
	%>
	
	
</body>
</html>