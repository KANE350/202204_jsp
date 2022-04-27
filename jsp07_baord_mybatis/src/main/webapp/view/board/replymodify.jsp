<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/includeFile.jsp" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function modifyCheck(e) {	//밑에서 보낸 이벤트를 e라는 변수에 저장			//공백방지펑션
		e.preventDefault();	//기본 이벤트가 제거된다.
		const content = document.getElementById('content');			//밑에서 보낸 돔 자체를 돔의 아이디를 통해 설정, 값을 담아준다.
		
		console.log(content.value.trim());
		if (content.value.trim() == ''){			//공백과 같다면 경고창 띄우기
			alert('내용을 입력해 주세요!');				
			return;
		}
		document.getElementById('frmReplyModify').submit();		//사라진 서브밋의 수정기능을 여기서 복구시켰다. 여기에 적은 frmReplyModify는 폼의 아이디이다.
		
	}

</script>
</head>
<body>
	<h2>댓글 수정</h2>
	${reply}
	<form id="frmReplyModify" action="${path}/reply/modify" method="post">			<!--컨텍스트패스는 인쿠르드를 통해 가져왔다  -->
		게시물번호: <input type="text" name="bnum" value="${reply.bnum}" readonly><br><!--컨트롤러에서 파라미터로 읽어들여야 하기 때문에 input때그로 bnum을 따로 넘겼다.-->
		<!--안보이게 하고 싶으면 타입을 hidden으로 설정하면 된다.-->
		댓글번호 : <input type="text" name="rnum" value="${reply.rnum}" readonly><br>
		댓글내용 : <textarea rows="5" cols="20" name="content" id="content" >${reply.content}</textarea> <br>
		<button onclick="modifyCheck(event)">수정</button>		<!--공백 방지 유효성 체크-->
														<!--주의할 점, 얘는 폼안에 있기 때문에 서브밋 기능이 들어있다. 때문에 수정을 누르면 
														공백체크하기도 전에 서브밋이 가버린다. 때문에 이벤트를 이용해서 제거해준다.-->
	</form>
</body>
</html>