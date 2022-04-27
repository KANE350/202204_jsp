<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includeFile.jsp" %>    
<%-- ${path}를 작성하려면 반드시 위에 인크루드 파일을 작성해야 한다. --%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="../header.jsp" %>
	<h2>회원조회</h2>
<%-- 	${mlist} --%>
	
	<%-- <c:out value="${param.findkey=='addrload'?'selected':''}"/> --%>
	<!--삼항연산자를 사용해 selected가 해당 위치에 있을 시 고정되도록 설정, 벨류에 값을 포함했다.
		어떤걸 조회하느냐에 따라서 탭이 고정되도록 설정. 벨류값이 설정되어 있어야 파라미터를 사용해서
		넣을 수 있다.  -->

	<form action="${path}/list.member">
	<!--contextpath/list.member를 위와 같이 작성했다.  -->
		<select name="findkey">
			<option value="userid"<c:out value="${param.findkey=='userid'?'selected':''}"/>>아이디</option>
			<option value="addrload"<c:out value="${param.findkey=='addrload'?'selected':''}"/>>도로명주소</option>
		</select>
		<input type="text" name="findvalue" value="${param.findvalue}">
		<!-- ${param.findvalue}를 입력하여 검색한 값이 창에 남아있게 한다. -->
		<button>조회</button>
	</form>
	
	<table border="1">
		<tr>
			<th>아이디</th>
			<th>도로명주소</th>
			<th>등록일자</th>			
		</tr>
		<c:forEach var="member" items="${mlist}">
			<tr>
				<td><a href="${path}/info.member?userid=${member.userid}">${member.userid}</a></td><!--getter를 실행해주지만 생략되어 작성한다.  -->
				<td>${member.addrload}</td>
				<td><fmt:formatDate value="${member.regidate}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
			</tr>
		</c:forEach>
	</table>
	
</body>
</html>