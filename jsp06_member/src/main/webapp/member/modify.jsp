<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includeFile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function goPopup(){
	// 주소검색을 수행할 팝업 페이지를 호출합니다.
	// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
	var pop = window.open("${path}/member/jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
	
}

//주소팝업 호출 후 실행할 함수
function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn,detBdNmList,bdNm,bdKdcd,siNm,sggNm,emdNm,liNm,rn,udrtYn,buldMnnm,buldSlno,mtYn,lnbrMnnm,lnbrSlno,emdNo){
	// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
	document.frmModify.addrload.value = roadAddrPart1;
	document.frmModify.addrdetail.value = addrDetail;
	document.frmModify.zipcode.value = zipNo;

	
}


function modifyCheck(e) {
	/* 기본 이벤트를 제거하겠다(submit기능 삭제). 펑션 안에 반드시 객체인 매개변수를 넣어야 실행된다. */
	e.preventDefault();
	let userid = frmModify.userid; //설정은 var, let. let으로 설정해도 되나 이렇게 하면 중복선언이 안된다.
	let passwd = frmModify.passwd;
	
	if(userid.value==''){
		alert("아이디를 입력해 주세요");
		userid.focus();
		return;
	}else if(passwd.value==''){
		alert("비밀번호를 입력해 주세요");
		userid.focus();
		return;
	}
	frmModify.submit();
	/* 여기서 서브밋을 작성해야 가입을 눌렀을 때 서브밋 기능이 일어난다. */
}
</script>
</head>
<body>
<%@ include file="../header.jsp" %>
	<h2>회원수정</h2>
	<%-- ${member} --%>
	
	<form name="frmModify" action="${path}/modify.member" method="post" enctype="multipart/form-data">	
	<!--사진을 보내려면 반드시 포스트 방식, 멀티파트 폼 데이터여야만 한다.  -->
	<table border="1">
	<tr>
		<th>아이디</th>
		<td> <input type="text" name="userid" value="${member.userid}" readonly></td>
		<!--아이디는 pk이기 떄문에 redonly처리를 한다.  -->
	</tr>
	<tr>
		<th>기존 비밀번호</th>
		<td> <input type="password" name="passwd" ></td>
	</tr>
		<tr>
		<th>새로운 비밀번호</th>
		<td> <input type="password" name="newpasswd" ></td>
	</tr>
	<tr>
		<th>우편번호</th>
		<td> <input type="text" name="zipcode" size="5" value="${member.zipcode}">
		<button type="button" onclick="goPopup()">검색</button>
		</td>
	</tr>
	<tr>
		<th>도로명주소</th>
		<td> <input type="text" name="addrload" size="30" value="${member.addrload}"></td>
	</tr>
	<tr>
		<th>상세주소</th>
		<td> <input type="text" name="addrdetail" value="${member.addrdetail}"></td>
	</tr> 
		<tr>
		<th>사진</th>
		 <td>
		 <img alt="사진없음" src="/savedir/${member.filename}" width="100"> 
		 <!--서버에서 확인해보기(맨 아래쪽)  -->
		 <input type="hidden" name="filename" value="${member.filename}">
		 <!--hidden눈에는 보이지 않지만 이 값을 넘길때 사용-->
		 ${member.filename}
		 <hr>
		 <input type="file" name="photo">
		 </td>
		<!-- db와는 상관이 없는 애이기 떄문에 겹치지 않기 위해 photo설정 -->
		<!--사진은 이름만 변경해서는 안되기 떄문에 앞에 설정  -->
	</tr>
	<tr>
		<td colspan="2" align="center">
			<button onclick="modifyCheck(event)">수정</button>
			<button type="reset">취소</button>
		</td>
	</tr>  
	</table>
	
	</form>
</body>
</html>