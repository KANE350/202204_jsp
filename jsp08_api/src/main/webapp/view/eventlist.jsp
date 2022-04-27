<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>		
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style type="text/css">
	th {
		 	width:100px;
		}
</style>
<body>

	<h2>서울시 문화행사 정보</h2>
	<form name="frmList" action="${path}/list.event">
		<select name="findkey">
			<option value="콘서트">콘서트</option>
			<option value="클래식">클래식 </option>
			<option value="뮤지컬/오페라">뮤지컬/오페라</option>
			<option value="연극">연극</option>
			<option value="무용">무용</option>
			<option value="국악">국악</option>
			<option value="전시">전시</option>
			<option value="축제">축제</option>
			<option value="교육">교육</option>
			<option value="기타">기타</option>
		</select>
		<input type="text" name="findvalue" value="${param.findvalue}">
			<button>조회</button>
	</form>
	
	<table border="1">
	<tr>
	<th>분류</th>
	<th>공연/행사명</th>
	<th>장소</th>
	<th>기관명</th>
	<th>이용대상</th>
		<th>이용요금</th>
		<th>출연자정보</th>
		<th>프로그램소개</th>
		<th>기타내용</th>
		<th>홈페이지 주소</th>
		<th>대표이미지</th>
		<th>신청일</th>
		<th>시민/기관</th>
		<th>시작일</th>
		<th>종료일</th>
		<th>테마분류</th>

		<c:forEach var="event" items="${elist}">
		<tr>
			<td>${event.CODENAME}</td>
			<td>${event.TITLE}</td>
			<td>${event.PLACE}</td>
			<td>${event.ORG_NAME}</td>
			<td>${event.USE_TRGT}</td>
			<td>${event.USE_FEE}</td>
			<td>${event.PLAYER}</td>
			<td>${event.PROGRAM}</td>
			<td>${event.ETC_DESC}</td>
			<td>${event.ORG_LINK}</td>
			<td>${event.MAIN_IMG}</td>
			<td>${event.RGSTDATE}</td>
			<td>${event.TICKET}</td>
			<td>${event.STRTDATE}</td>
			<td>${event.END_DATE}</td>
			<td>${event.THEMECODE}</td>
		</tr>
		</c:forEach>
	
	</table>
</body>
</html>