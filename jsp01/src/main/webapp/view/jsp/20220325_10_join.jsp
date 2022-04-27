<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 주말 과제 -->
<!--입력항목 
 	아이디, 비밀번호, 이메일, 이름, 성별(radio), 가입경로(select), 
	알바가능시간대(checkbox):새벽,오전,오후,저녁
	
	버튼을 눌렀을때 결과창에 가입정보 출력 
 -->	  
	<h2>회원가입</h2>
	<!--action에 20220325_10_result.jsp이렇게 넣은건 html을 통해 이동하는 방식이다.   -->
	<form action="20220325_10_result.jsp" method="post">
		<!-- type은 어떻 형태로 받을 것이냐. name은 데이터를 서버로 보낼 때 안에 있는 데이터들을 톰캣(서버)롭 보내야하는데,보낼떄 값을 보내려면 값에 대한 이름이 있어야 하니 값을 붙인다.  벨류는 폼 내에 하드코딩 되어있는 상태-->
		아이디 <input type="text" name="userid" > <br>
		비밀번호<input type="password" name="passwd" > <br>
		이메일 <input type="email" name="email" > <br>
		이름 <input type="text" name="name" > <br>
		성별 <input type="radio" name="gender" value="woman" >여자 
			 <input type="radio" name="gender" value="man" >남자<br>	
		가입경로 <select name="route">
					<option>사이트검색</option>
					<option>지인추천</option>
					<option>광고</option>
				</select><br>
		가능시간대 <input type="checkbox" name="time" value="새벽">새벽
					<input type="checkbox" name="time" value="오전">오전
					<input type="checkbox" name="time" value="오후">오후
					<input type="checkbox" name="time" value="저녁">저녁
					<br>
		<hr>					
		<button>가입</button>
	</form>
</body>
</html>