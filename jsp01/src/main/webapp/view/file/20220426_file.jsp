<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>파일 업로드</h2>
	<form action="/jsp01/upload.file" method="post" enctype="multipart/form-data">
		파일 <input type="file" name="file1">
		<button>전송</button>
	</form>
	<hr>
	<h2>파일 다운로드</h2>
	<a href="/jsp01/download.file?filename=${filename}">${filename}</a> 	<!--사용자가 다운로드 할 수 있도록 하이퍼링크를 통해 요청한다.-->
	<!--파일을 클릭했을 때 uri가 찍혀야 컨트롤러에서 파일을 잘 받아들일 수 있는 상황이 됐음을 알 수 있다.-->
	<!--파일네임을 알아야 받을 수 있기 때문에 같이 넘겨준다.-->
</body>
</html>