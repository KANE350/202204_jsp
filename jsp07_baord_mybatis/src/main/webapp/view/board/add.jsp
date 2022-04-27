<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includeFile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>게시물 등록</h2>
	<form action="${path}/board/add" method="post" enctype="multipart/form-data">
	<table>
		<tr>
			<th>작성자 id</th>
			<td> <input type="text" name="userid"></td>
		</tr>
		<tr>
			<th>제목</th>
			<td> <input type="text" name="subject"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td> <textarea name="content" rows="5" cols="25"></textarea></td>
		</tr>
		<tr>
			<th>파일</th>
		<td> 
			<input type="file" name="file1"><br>
			 <input type="file" name="file2"><br>
			 <input type="file" name="file3"><br>
			 <input type="file" name="file4"><br>
			 <input type="file" name="file5"><br>
		</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button>추가</button>
				<button type="reset">취소</button>
			</td>
		</tr>	
	</table>
	</form>
</body>
</html>