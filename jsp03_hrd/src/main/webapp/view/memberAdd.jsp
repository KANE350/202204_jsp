<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	//메시지 창 띄우기
	if ('<%=request.getParameter("msg") %>' != 'null')
		alert('<%=request.getParameter("msg") %>');


	function joinCheck() {
		var custname = frmJoin.custname;
		var phone = frmJoin.phone;
		var address = frmJoin.address;
		var joindate = frmJoin.joindate;
		var grade = frmJoin.grade;
		var city = frmJoin.city;
		if (custname.value == ''){
			alert('회원성명이 입력되지 않았습니다.');
			custname.focus();
		}else if (phone.value == ''){
			alert('회원전화가 입력되지 않았습니다.');
			phone.focus();			
		}else if (address.value == ''){
			alert('회원주소가 입력되지 않았습니다.');
			address.focus();			
		}else if (joindate.value == ''){
			alert('가입일자가 입력되지 않았습니다.');
			joindate.focus();			
		}else if (grade.value == ''){
			alert('고객등급이 입력되지 않았습니다.');
			grade.focus();			
		}else if (city.value == ''){
			alert('도시코드가 입력되지 않았습니다.');
			city.focus();			
		}else{
			frmJoin.action = '/jsp03_hrd/member/join';
			frmJoin.method = 'post';
			frmJoin.submit();
/*폼의 액션의 경로를 여기서 변경해도 상관없다.   */
			/*컨트롤러의 웹 서블릿이 /member/*이므로 이 형식에 맞추어야 한다.   */
			/*괄호를 붙이면 메소드또는 함수를 호출한다. 괄호를 안붙이면 그냥 변수로 보면 된다.  */
		}
		
	}

</script>
</head>
<body>
	<%@include file="header.jsp" %>
	<section>
		<h2>홈쇼핑 회원 등록</h2>
		<form name="frmJoin" id="frmJoin">
			<table>
				<tr>
					<th>회원번호(자동발생)</th>
					<td> <input type="text" name="custno" readonly> </td>
				</tr>
				<tr>
					<th>회원성명</th>
					<td> <input type="text" name="custname"> </td>
				</tr>
				<tr>
					<th>회원전화</th>
					<td> <input type="tel" name="phone"> </td>
				</tr>
				<tr>
					<th>회원주소</th>
					<td> <input type="text" name="address"> </td>
				</tr>
				<tr>
					<th>가입일자</th>
					<td> <input type="date" name ="joindate"> </td>
				</tr>
				<tr>
					<th>고객등급</th>
					<td>
						<select name="grade">
							<option value="A">A:VIP</option>
							<option value="B">B:일반</option>
							<option value="C">C:직원</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>도시코드</th>
					<td> <input type="text" name="city"> </td>
				</tr>
				<tr>
					<td colspan = "2"> 
						<button type="button" onclick="joinCheck()">등록</button>
						<button>조회</button>
					</td>
				</tr>
			</table>
			
		</form>
		
		
	</section>
	<%@include file="footer.jsp" %>
</body>
</html>