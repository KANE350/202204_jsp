<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<h2>상세조회</h2>
	<table border="1"> 
	<tr>
		<th>안전공지 ID</th>
		<td>${country.sfty_notice_id}</td>
	</tr>
	<tr>
		<th>ISO</th>
		<td>${country.country_iso_alp2}</td>
	</tr>
	<tr>
		<th>한글국가명</th>
		<td>${country.country_nm}</td>
	</tr>
	<tr>
		<th>제목</th>
		<td>${country.title}</td>
	</tr>
	<tr>
		<th>파일갯수</th>
		<td>${country.file_cnt}</td>
	</tr>
	<tr>
		<th>파일 다운로드 url</th>
		<td><a href="${country.file_download_url}">${country.file_path}</a></td>
	</tr>
	<tr>
		<th>내용</th>
		<td>${country.html_origin_cn}</td>
	</tr>
	<tr>
		<th>작성일</th>
		<td>${country.wrt_dt}</td>
	</tr>
	</table>
	
	<%-- ${country}  --%>
</body>
</html>