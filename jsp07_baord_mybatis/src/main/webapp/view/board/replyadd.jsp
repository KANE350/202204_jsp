<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/includeFile.jsp" %>			<!--인크루드 파일이 현재 디렉토리 부모에 있기 때문에 ../-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>댓글</h2>		<!--댓글 추가  -->
	<form action="${path}/reply/add" method="post">
		게시물 번호 <input type="text" name="bnum" value="${param.bnum}"><br>	<!--해당 bnum에 대한 댓글을 달겠다  -->
		<input type="hidden" name="restep" value="${param.restep}">
		<input type="hidden" name="relevel" value="${param.relevel}">
		내용 <textarea rows="5" cols="20" name="content"></textarea>
		<button>추가</button>
	</form>
	
</body>
</html>