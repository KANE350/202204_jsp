<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/includeFile.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function removeCheck(){
		//세션 체크(세션의 uri와 삭제하려는 uri가 같아야 한다.)
		if ('${sessionScope.userid}' != '${member.userid}'){
			alert('권한이 없습니다');
			return;
		}	
		
		var result = confirm('정말로 회원 탈퇴하시겠습니까?');
		if(result){
				location.href='${path}/remove.member?userid=${member.userid}';
			}
		}	/*멤버 컨트롤러로 넘어가서 작성  */

</script>
</head>
<body>
<%@ include file="../header.jsp" %>	
<!--인쿠르드 헤더는 해당 폼에서 해더가 보이게 하기 위함이다.  -->
	<h2>개인정보</h2>
	<%-- ${member} --%>
	<!--따로 수정하거나 하는거 없이 보기만 하기 떄문에 폼작성을 안했다  -->
	<table border="1">
	<tr>
		<th>아이디</th>
		<td>${member.userid}</td>
	</tr>
	<tr>
		<th>우편번호</th>
		<td>${member.zipcode}</td>
	</tr>
	<tr>
		<th>도로명주소</th>
		<td>${member.addrload}</td>
	</tr>
	<tr>
		<th>상세주소</th>
		<td>${member.addrdetail}</td>
	</tr> 
	<tr>
		<th>사진</th>
		<td>
			<img alt="사진없음" src="/savedir/${member.filename}" width="100">
			${member.filename} <!--사진 파일명 나오게 하기  -->
		</td>
	</tr>
		<tr>
		<th>가입일자</th>
		<td><fmt:formatDate value="${member.regidate}" pattern="yyyy년 MM월 dd일 HH:mm:ss"/> </td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<button onclick="location.href='${path}/modiform.member?userid=${member.userid}'">수정폼</button>
			<%-- ${path}를 컨텍스트 패스로 활용하기 위해서는 맨 위에 인크루드 파일이 있어야 한다. --%>
			<!--수정폼으로 가기 때문에 pk(userid)도 같이 넘긴다.  -->
			<!--수정을 클릭했을때 url이 제대로 나타나는지 http://localhost:8090/jsp06_member/modiform.member?userid=java(java는 userid) 확인하기  -->
			<button onclick="removeCheck()">회원탈퇴</button>
		</td>
	</tr>  
	</table>
</body>
</html>