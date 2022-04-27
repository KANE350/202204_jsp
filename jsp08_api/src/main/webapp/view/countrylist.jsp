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
		if('${param.msg}' != ''){	// 파라미터의 메시지가 공백과 같이 않을때만 해당 메시지를 띄워라
			alert('${param.msg}');	
		}
	
		//파싱후 디비 저장
		function parsingSave(e){
			e.preventDefault();	//기본이벤트 삭제
			frmCountry.action = '${path}/dbsave.country';		//자바스크립트 공간이기 때문에 자바코드를 가져올 때에는 항상 ''를 사용해주어야 한다.'
			frmCountry.submit();			/*셀렉트 네임안의 iso를 컨트롤로 가져가겠다*/ 
		}
		
		//조회
		function listCheck(e){
			e.preventDefault();	//기본이벤트 삭제
			frmCountry.action = '${path}/list.country';		//자바스크립트 공간이기 때문에 자바코드를 가져올 때에는 항상 ''를 사용해주어야 한다.'
			frmCountry.submit();
		}
		
	</script>
		
		
</head>
<body>
	<h2>외교부_국가.지역별 최신안전소식(코로나관련)</h2>
	<form name="frmCountry" action="${path}/list.country">
		국가
		<select name="iso">
			<c:forEach var="iso" items="${isolist}">
				<option value="${iso.CODE}"${param.iso==iso.CODE?'selected':''}>${iso.NAME}</option>		<!--dto에 담을때에는 필드명을 소문자로 만들었기 때문에 여기서도 소문자가 되지만, 매퍼에서 맵에 담았기 때문에 오라클의 필드명에 맞게 넣어야 한다.-->
			</c:forEach>		<!--파라미터에 있는 값이 iso.code와 같을때만 셀렉티드가 되도록 설정-->
			</select>			<!--${param.iso}의 iso는 컨트롤러의 url의 iso이다.-->
		<button onclick="listCheck(event)">조회</button>			<!--를 누르면 list가 실행-->
		<button onclick="parsingSave(event)">파싱 후 db저장</button>		<!--를 누르면 dbsave가 실행-->
		
		<table border="1">
		<tr>
			<th>안전공지</th>	
			<th>대륙코드</th>	
			<th>한글대륙명</th>	
			<th>ISO</th>	
			<th>한글국가명</th>	
			<th>제목</th>	
			<th>파일갯수</th>
		 	<th>작성일</th>	
		</tr>
		
		<c:forEach var="country" items="${clist}">
			<tr>
				<td>${country.sfty_notice_id}</td>
				<td>${country.continent_cd}</td>
				<td>${country.continent_nm}</td>
				<td>${country.country_iso_alp2}</td>
				<td>${country.country_nm}</td>
				<td><a href="${path}/detail.country?sfty_notice_id=${country.sfty_notice_id}">${country.title}</a></td>
				<td>${country.file_cnt}</td>
				<td>${country.wrt_dt}</td>
			</tr>
		</c:forEach>
		
		</table>
		
	</form>
		
</body>
</html>