<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includeFile.jsp" %> <!--컨테스트 패스를 간단하게 사용하기 위함  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#active{
		color:green;
		font-size:30px;
	}
	
}
</style>
</head>
<!-- <script type="text/javascript">
제이쿼리는 내가 스크립트를 작성할때에 어떤 규칙인지만 알면 된다.
$('#name').css();	제이쿼리는 라이브러리를 편하게 쓸 수 있는 언어 중 하나이다.
</script>  이런식으로 -->
<body>
	<!--검색값을 그대로 남겨두고 싶을때 파라미터를 읽어와서 삼항연산자로 고정.  -->
	${param.findkey=='subject'? 'selected':''}
	<%-- ${blist} --%>	<!--컨트롤러에서 forward로 넘긴 리스트가 제대로 들어왔는지 확인  -->
	<h2>게시물 리스트</h2>
	<form action="${path}/board/list">	<!--list에서 조회를 누르면 action으로 경로이동이 된다
										 디렉토리 패턴이기 때문에 /board/*으로 작성된다. -->
		<select name="findkey">
			<option value="userid" <c:out value="${param.findkey=='userid'?'selected':''}"/>>작성자</option>	<!--여기서 벨류값은 mapper키의 벨류값이다  -->
			<option value="subject" <c:out value="${param.findkey=='subject'?'selected':''}"/>>제목</option>
			<option value="content" <c:out value="${param.findkey=='content'?'selected':''}"/>>내용</option>
		</select>
		<input type="text" name="findvalue" >
		<button>조회</button>
		<button type="button" onclick="location.href='${path}/view/board/add.jsp'">추가폼</button>
	</form>
	<hr>
	<table border="1">	
		<tr>
			<th>번호</th>				<!--header-->
			<th>작성자id</th>
			<th>제목</th>
			<th>작성일자</th>
			<th>조회수</th>
		</tr>
		
		<c:forEach var="board" items="${blist}">
			<tr>
				<td>${board.bnum}</td>		<!--body-->
				<td>${board.userid}</td>
				<td><a href="${path}/board/detail?bnum=${board.bnum}">${board.subject}</a></td>			<!--링크 삽입  -->
				<td><fmt:formatDate value="${board.regidate}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
				<!--인쿠르드 파일 상단에 fmt가 있기 때문에 사용 가능하다.-->
				<td>${board.readcnt}</td>
			</tr>
		</c:forEach>
	</table>
	<hr>
	<!--페이징-->
	<%-- ${findmap} --%>
	<hr>
	<c:if test="${findmap.startPage != 1}">	<!--이것이 1하고 같지 않다면 이전버튼을 나타내라-->
	<a href="${path}/board/list?curPage=${findmap.startPage-1}&findkey=${param.findkey}&findvalue=${param.findvalue}">이전</a>	
	</c:if>
	
	
	<c:forEach var="i" begin="${findmap.startPage}" end="${findmap.endPage}">	<!--파인드맵에서 스타트페이지, 엔드패이지를 추출 해서 기준을 잡는다 -->
		<c:if test="${i==findmap.curPage}">		<!--파인드맵의 커페이지이와 같다면 액티브 선택된 색상만 다르게  -->
					<a id="active" href="${path}/board/list?curPage=${i}&findkey=${param.findkey}&findvalue=${param.findvalue}">${i}</a> 
		</c:if>
		<c:if test="${i != findmap.curPage}">		<!--파인드맵의 커페이지이ㅘ 같다면 액티브 선택된 색상만 다르게  -->
					<a href="${path}/board/list?curPage=${i}&findkey=${param.findkey}&findvalue=${param.findvalue}">${i}</a> 
		</c:if>
	</c:forEach> <!--게시물에서 검색한 페이지만 넘기기 위해 파인드키와 파인드벨류를 추가했다.  -->
	
	<c:if test="${findmap.totPage > findmap.endPage}">
		<a href="${path}/board/list?curPage=${findmap.endPage+1}&findkey=${param.findkey}&findvalue=${param.findvalue}">다음</a>	
	</c:if>
	
	
	
</body>
</html>