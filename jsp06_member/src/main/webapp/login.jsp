<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includeFile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
//메시지창 알림이 인크루드 파일 안에 있기 때문에 작성하지 않아도 된다.	
	//로그인에서 값 입력 후 엔터를 누르면 실행(엔터값을 인식해서 엔터키를 실행) 
	function enterkey() {
		if (window.event.keyCode!=13) return;
		loginCheck();
	}
	

	//로그인 체크
	function loginCheck() {
		const userid = frmLogin.userid;
		const passwd = frmLogin.passwd;
		
		if (userid.value ==''){
			alert('아이디를 입력해 주세요');
			userid.focus();
		}else if (passwd.value ==''){
			alert('비밀번호를 입력해 주세요');
			passwd.focus();
			
		}else{
			frmLogin.action = '${path}/login.member';
			frmLogin.method = 'post';
			frmLogin.submit();
		}
	}



	
</script>
</head>
<body>
<%@ include file="../header.jsp" %>
	<h2>로그인</h2>
	<form name="frmLogin" action="" enctype="application/x-www-form-urlencoded">
		<table>
			<tr>
				<th>아이디</th>
				<!-- 쿠키의 값 세팅-->
				<td><input type="text" name="userid" value="${cookie.userid.value}"> </td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="passwd"> </td>
			</tr>
				<tr>
				<tr>
				<!-- 쿠키에 아이디가 있으면 checked -->
				<td colspan="2">
				아이디 기억하기
				<!--cookei.userid.value가 null이라면  -->
				<!--공백 아니면 체크, empty는 널체크를 사용한다. empt가 되어 있으면 공백  -->
				<input type="checkbox" name="idsave" <c:out value="${empty cookie.userid.value?'':'checked'}"/>> 
				</td>
				
				<!--checked아이디 기억하기가 기본적으로 체크되어있게끔 추가  -->
				<%-- <td><input type="checkbox" name="idsave" <%=!userid.equals("")?"checked":""%>> </td> --%>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<button type="button" onclick="loginCheck()">로그인</button> 
					<button type="reset">취소</button>
				</td>
			</tr>			
			
		</table>
	</form>
</body>
</html>