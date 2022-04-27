<%@page import="dto.Member"%>
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
	<%@include file="header.jsp" %>
	<section>
	<%
		List<Member> mlist = (List<Member>)request.getAttribute("mlist");
		/*컨트롤러에 셋어츄리비티 한것을 가져온다.   */
		/*배열이기 때문에 반복문을 돌려서 풀어내면 된다.   */
		/*뷰에 띄우기도 하고 반복문을 돌리기 위한 것. 어차피 반복문을 돌려야 하니까 변수에 저장해서 띄운것.*/
	%>
	<%=mlist %>
	<!-- mlist 찍어봐서 뷰에 잘 나오는지 확인   -->
	
	<!-- 회원조회 -->
	<h2>회원목록조회/수정</h2>
	<table border="1">
		<tr>
			<th>회원번호</th>
			<th>회원성명</th>
			<th>전화번호</th>
			<th>주소</th>
			<th>가입일자</th>
			<th>고객등급</th>
			<th>거주지역</th>
		</tr>
		<% 
			if(mlist != null){
				for(Member member:mlist){
		%>			
		
				<tr>
					<td><a href="/jsp03_hrd/member/modiform?custno=<%=member.getCustno()%>">
								<%=member.getCustno() %></a> </td>
					<!-- 하이퍼링크를 걸어서 클릭하면 수정이 가능하게끔 한다. 멤버라는 디렉토리를 통해 모디폼으로 가려는 동작.
					그런데 한건조회를 해서 가려면 반드시 커스트 넘버를 알아야 한다.
					그리고 다음과 같은 경로는 반드시 큰따옴표 안에 넣어야 한다. 
					작성 후 modiform이 뜨는지 확인-->
					<td><%=member.getCustname() %></td>
					<td><%=member.getPhone() %></td>
					<td><%=member.getAddress()%></td>
					<td><%=member.getJoindate() %></td>
					<td><%=member.getGrade() %></td>
					<td><%=member.getCity() %></td>
				</tr>
		<%		}		
			}
		
		/*오른쪽에 변수의 이름, 왼쪽에 하나하나 들어있는 멤버의 형 작성. if문은 널 방지 처리*/
		
		
		
		%>
	</table>
	</section>
	<%@include file="footer.jsp" %>
</body>
</html>