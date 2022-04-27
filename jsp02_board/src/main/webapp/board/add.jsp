<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	if('<%=request.getParameter("msg")%>' != 'null')
	alert('<%=request.getParameter("msg")%>');
	//따옴표를 붙여줘야 문자열로 인식하기 때문에 반드시 넣어줘야 한다. 
	//내용 입력하고 클릭시 msg(추가완료)를 보여주는 alert창

</script>
</head>
<body>
	<%@include file="../header.jsp" %>
	<h2>게시물 등록</h2>
	<form action="/jsp02_board/add.board"  method="post"> 
	<!-- aaa.board이든 bbb.이든 확장자가 board이면 이쪽으로 가라는 뜻이다 앞의 경로명은 서버에서 확인하기-->
	<table>
		<tr>
			<th>작성자</th>
			<td> <input type="text" name="writer"> </td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="subject"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea name="content" rows="5" cols="25"></textarea></td>
			<!-- 위에서 널 금지처리하려면 이 태그들 사이에 공백을 입력하지 않도록 주의해야 한다.--> 
		</tr>
		<tr>
			<td colspan="2" align="center"> 
					<button onclick="addCheck()">저장</button>
					<button type="reset">취소</button>
				</td>   
		</tr>
	</table>
	
	
	
	</form>
</body>
</html>