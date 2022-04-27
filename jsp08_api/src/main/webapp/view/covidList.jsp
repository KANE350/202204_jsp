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
	
	
	function valueCheck() {
		
		//유효성 체크				true가 리턴이 됐을때만 서브밋이 진행되도록
		const startDt = frmList.startDt;
		const endDt = frmList.endDt;
		console.log(startDt);
		console.log(endDt);
		
		if(startDt.value==''){
			alert('시작일자가 입력되지 않았습니다.');
			startDt.focuse();
			return false;	//유효성체크 실패	
		}else if(endDt.value==''){
			alert('종료일자가 입력되지 않았습니다.');
			endDt.focuse();
			return false;	
		}
		return true; //유효성 체크 성공	
	}

	//조회를 위한 체크 
	function listCheck(e) {
		e.preventDefault();		/*중괄호가 안붙어있으면 자체를 가져오는 것, 중괄호가 붙어있으면 실행하겠다는 뜻*/
		if(valueCheck()){
			frmList.action = '${path}/list.covid'
			frmList.submit();
			}
		}
	
	//파싱 후 db 저장
	function parsingSave(e) {
		e.preventDefault();		//아래의 기본이벤트 제거 이벤트를 받아온다
		if (valueCheck()){	//valueCheck 수식이 트루가 되면(유효성 체크 성공시) 실행하겠다. 아닐시에는 벨류 앞에 !를 붙이면 된다. 불필요한 수식을 줄이기 위함.
		 	frmList.action = '${path}/dbsave.covid';		//파싱후 디비 저장의 경로
			frmList.submit();		//submit이라는 이벤트 실행, 자바스크립트에서 직접 호출 
		}

 	}
</script>
</head>
<body>
	
	<h2>코로나 확진자 현황</h2>
	<form name="frmList" action="${path}/list.covid">
		기간 <input type="date" name="startDt" value="${param.startDt}"> ~ 		<!--파라미터로 값 남겨두기-->
				<input type="date" name="endDt" value="${param.endDt }">
				<button onclick="listCheck(event)">조회</button>			<!--event는 값, 변수가 아니다. 이를 위에서 e라는 변수에 저장한다.-->
				<button onclick="parsingSave(event)">parsing 후 db저장</button>	
				<%-- ${clist} --%>			<!--컨트롤러에서 옮기기 끝나고 뿌려보기-->
			<table border="1">
				<tr>
					<th>게시물번호</th>				<!--게시글을 작성할때 포스트맨에 url을 입력하고 넘겨온 게시물을 참고해서 작성하자.-->
					<th>기준일</th>
					<th>기준시간</th>
					<th>누적 확진자 수</th>
					<th>일일 확진자 수</th>
					<th>사망자 수</th>
					<th>등록일시분초</th>
					<th>수정일시분초</th>
				</tr>
				<c:forEach var="covid" items="${clist}">						
					<tr>						<!--반복하고 싶은 부분 넣기-->
					<td>${covid.seq}</td>				<!--뷰에 뿌려진 키값을 가져와야 한다-->
					<td>${covid.stateDt}</td>		<!--dto에 있는 getter를 이용해 뿌리기 때문에 필드명이 동일해야 한다.-->
					<td>${covid.stateTime}</td>
					<td>${covid.decideCnt}</td>		<!--누적확진자수-->
					<td>${covid.dailyDecideCnt}</td>		<!--일일확진자수-->
					<td>${covid.deathCnt}</td>		<!-- 일일확진자수-->
					<td>${covid.createDt}</td>
					<td>${covid.updateDt}</td>
				</tr>
				</c:forEach>				
			</table>
					
	</form>
</body>
</html>		