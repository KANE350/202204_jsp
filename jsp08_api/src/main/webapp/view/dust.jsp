<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>						<!--컨테스트 패스를 쓰기 위해 위의 두줄을 작성하고 라이브러리 tag들을 가져왔다.-->
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	if('${param.msg}' != ''){
		alert('${param.msg}');
	}
</script>

</head>
<body>
	<h2>한국환경공단_에어코리아_미세먼지 경보 발령 현황</h2>
	<!--파싱할때는 연도가 필요하고 조회할떄는 지역명이 필요하므로 나눈다.-->
	<form action="${path}/pasing.dust">
		<fieldset>
			<legend>파싱</legend>
			<input type="number" name="year">	<!--year라는 연도를 컨트롤러에 넘기면 컨트롤러는 서비스로 넘긴다. 지역명이 매개변수에 없기 때문에 연도로 한다. 조회는 가능.-->
			<button>파싱</button>
		</fieldset>	
		
	</form>
	<hr>
	<%-- ${list} --%>
	<form action="${path}/list.dust">
		
		지역명 <input type="text" name="districtName" value="${param.districtName}">
			<button>조회</button>
		<c:forEach var="dust" items="${list}">	
		<table border="1">
		<tr>
			<th>일련번호</th>
			<td colspan="3">${dust.sn}</td>
		</tr>
		<tr>
			<th>지역명</th>
			<td>${dust.DISTRICTNAME}</td>
			<th>권역명</th>
			<td>${dust.MOVENAME}</td>
		</tr>
		<tr>
			<th>항목명</th>
			<td>${dust.ITEMCODE}</td>
			<th>경보단계</th>
			<td>${dust.ISSUEGBN}</td>
		<tr>	
			<th>발령일시</th>
			<td>${dust.ISSUEDATE} ${dust.ISSUETIME}</td>
			<th>발령농도</th>
			<td>${dust.ISSUEVAL}</td>
		</tr>
		<tr>	
			<th>해제일</th>
			<td>${dust.CLEARDATE} ${dust.CLEARTIME}</td>
			<th>해제농도</th>
			<td>${dust.CLEARVAL}</td>
		</tr>
		</table>
		</c:forEach>
	</form>
	
	
</body>
</html>