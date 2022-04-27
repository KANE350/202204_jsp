<%@page import="dto.Board"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	if ('<%=request.getParameter("msg")%>' != 'null')
		alert('<%=request.getParameter("msg")%>');
	/* 수정폼에 aleat창이 뜨기 위한 코드 */

</script>
</head>
<body>
	<% 
		//findkey null일떄 처리
		String findkey = request.getParameter("findkey");
		String findvalue = request.getParameter("findvalue");
		if (findkey == null) findkey = "";
		if (findvalue == null) findvalue = "";
	%>
	<!-- 지시자 include를 이용해서 jsp를 삽입 -->
	<%@include file="../header.jsp" %>
	<!-- 게시물 상단에 헤더를 고정시키기 위해 작성 -->
	<!-- 절대경로를 쓰려면 리얼패스에 있는 애를 찾아가야 하기 때문에 실제 있는 경로를 따지기 때문에 상대경로로 따지는게 좋다. --> 
	
	<h2>게시물 리스트</h2>
	<form action="/jsp02_board/list.board">
	<!-- 맵핑정보는 겹치지 않게 설계해야 한다. -->
		<select name="findkey">
			<option value="writer" <%out.print(findkey.equals("writer")?"selected":"");%>>작성자</option>
			<option value="subject" <%out.print(findkey.equals("subject")?"selected":"");%>>제목</option>
			<option	value="content" <%out.print(findkey.equals("content")?"selected":"");%>>내용</option>
		</select>
		<input type="text" name="findvalue" value="<%=findvalue%>">
		<!-- 벨류값을 이렇게 추가하면 검색 후에도 검색창에 검색명이 남아있게 된다. -->
		<button>조회</button>
	</form>
	<hr>
	
	<!-- 조회리스트 --> 
	<form action="/jsp02_board/remove.board">
	<!-- 폼으로 감싸면 삭제버튼에 서브밋이 들어간다 -->
		<table border="1">
			<tr>
				<th>순번</th>
				<th>작성자</th>
				<th>제목</th>
				<th>내용</th>
				<th>등록일자</th>
				<th>수정일자</th>
				<th><button>삭제</button></th>
			</tr>
			
		
		<%
			List<Board> blist = (List<Board>)request.getAttribute("blist");
			//blist를 자식형으로 다운캐스팅을 해서 가져온다(blist앞에 바꾸려는 형으로 작성)
			if (blist != null){
				for(Board board:blist){
		%>
		
			<tr>
				<td><%=board.getSeq()%></td>
				<td><%=board.getWriter()%></td>
				<td><a href="/jsp02_board/modiform.board?seq=<%=board.getSeq()%>"><%=board.getSubject()%></a></td>
				<!-- 한건조회에 필요한 정보가 seq이므로 seq를 달고 간다 -->
				<td><pre><%=board.getContent()%></pre></td>
				<td><%=board.getRegidate()%></td>
				<td><%=board.getModidate()%></td>
				<td align="center"><input type="checkbox" name="removes" value="<%=board.getSeq()%>"> </td>
				<!-- 벨류를 getseq로 넘겨줘야 값을 인지하고 넘긴다 -->
			</tr>
		<% 		}
			}
		%>		
	</table>
	</form>
</body>
</html>