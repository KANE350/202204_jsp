<%@page import="dto.Member"%>
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
	
	function modifyCheck() {
		/*함수이름과 버튼의 이름이 맞지 않으면 호출되지 않는다.  */
		var custname = frmModify.custname;
		/* 폼의 이름과 같아야 한다. */
		var phone = frmModify.phone;
		var address = frmModify.address;
		var joindate = frmModify.joindate;
		var grade = frmModify.grade;
		var city = frmModify.city;
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
			frmModify.action = '/jsp03_hrd/member/modify';
			frmModify.method = 'post';
			frmModify.submit();
		}
		
	}


</script>
</head>
<body>
	<%
		Member member = (Member)request.getAttribute("member");
	%>
	<%
		String grade = member.getGrade();
		//조건?참:거짓
		/*삼항연산자를 이용해서 셀렉트박스에 공백이 들어갈 경우 출력할지 안할지를 선택*/
		//out.print(grade.equals("A")?"selected":"");
	%>
	
	<!-- 회원수정 -->
	<%@include file="header.jsp" %>
	<section>
		<h2>홈쇼핑 회원 정보 수정</h2>
		<form name="frmModify" >
			<table>
				<tr>
					<th>회원번호</th>
					<td> <input type="text" name="custno" readonly value="<%=member.getCustno()%>"> </td>
				</tr>
				<tr>
					<th>회원성명</th>
					<td> <input type="text" name="custname" value="<%=member.getCustname()%>"> </td>
				</tr>
				<tr>
					<th>회원전화</th>
					<td> <input type="tel" name="phone" value="<%=member.getPhone()%>"> </td>
				</tr>
				<tr>
					<th>회원주소</th>
					<td> <input type="text" name="address" value="<%=member.getAddress()%>"> </td>
				</tr>
				<tr>
					<th>가입일자</th>
					<td> <input type="date" name ="joindate" value="<%=member.getJoindate()%>"> </td>
				</tr>
				<tr>
					<th>고객등급</th>
					<td>
						<select name="grade">
							<option value="A" <%out.print(grade.equals("A")?"selected":""); %>>A:VIP</option>
							<option value="B" <%out.print(grade.equals("B")?"selected":""); %>>B:일반</option>
							<option value="C" <%out.print(grade.equals("C")?"selected":""); %>>C:직원</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>도시코드</th>
					<td> <input type="text" name="city" value="<%=member.getCity()%>"> </td>
				</tr>
				<tr>
					<td colspan = "2"> 
						<button type="button" onclick="modifyCheck()">수정</button>
						<button>조회</button>
					</td>
				</tr>
			</table>
			
		</form>
	</section>
	<%@include file="footer.jsp" %>	
</body>
</html>