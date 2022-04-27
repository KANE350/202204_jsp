<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includeFile.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.reply{			/*클래스 선택자이기 때문에 /가 아니라 .으로 표기해야 한다.  */
		display: flex;
	}
	
</style>

<!-- fontawesome 아이콘 넣기, css폴더의 로그인에서 가져왔다.-->
<script src="https://kit.fontawesome.com/5bbe282217.js" crossorigin="anonymous"></script>

<script type="text/javascript">
	function removeCheck() {	
		const result = confirm('정말 삭제하시겠습니까?');
		if (result){
			location.href = '${path}/board/remove?bnum=${board.bnum}';	
			/*여기서 성공하면 컨트롤러까지는 넘어간 상태, 컨트롤러에서 작성해준다.*/
			/*bnum은 해당 수가 뷰에서는 유일하기 때문에 삭제를 눌렀을때 무조건 해당 번호가 가진다. 하지만
			댓글 삭제는 어떤 댓글의 삭제를 누를지 모르기 때문에 번호를 매개변수로 넘겨줘야 한다.
			처음부터 하이퍼링크에 인덱스 0번으로 rnum을 뿌려주면 해당 번호가 고정이 되기 때문에 동적으로 삭제해줘야 하는 replyremove
			는 매개변수를 넘겨줘야 한다. 인덱스0번으로 뿌리는 이유는 컨트롤러에서 리스트 계열을 받아왔기 때문이다.*/
		}
		
	}
	
	//댓글 삭제
	function removeReply(rnum) {	//해당 댓글의 번호를 매개변수로 넘긴다.
		/* alert(rnum);				//댓글의 번호가 잘 뜨는지 확인  */
		const result = confirm('댓글을 삭제할까요?');
		if(result){
			location.href = '${path}/reply/remove?bnum=${board.bnum}&rnum='+rnum;		/*삭제확인을 눌렀을때 콘솔에 이 창이 떠야한다. */
		}	//돌아올때 디테일로 돌아오기 때문에 bnum을 통해 돌아오려고 붙였다. 또한 bnum을 고정이기 때문에 $자체를 붙였다.
	}
</script>
</head>
<body>
 	<h2>상세조회</h2>				<!--forward방식으로 컨트롤러로 넘어온 것들을 출력  -->
<%-- 	${board}
	<hr>
	${bflist} --%>	
	
	<table border="1">
		<tr>
			<th>번호</th>
			<td>${board.bnum}</td>
		</tr>
		<tr>
			<th>아이디</th>
			<td>${board.userid}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${board.subject}</td>
		</tr>
		<tr>
			<th>내용</th>
			<td> <pre>${board.content}</pre> </td>
			<!--pre태그로 감싸면 두줄표현(제목내용구분)이 가능해진다  -->
		</tr>
			<tr>
			<th>파일</th>
			<td>
			<c:forEach var="boardfile" items="${bflist}">	<!--반복하고 싶은 것이 ${bflist}  -->
				<!--${boardfile.filename}<br>-->
				<a href="${path}/file/download?filename=${boardfile.filename}">${boardfile.filename}</a><br>
			</c:forEach>
			
			</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${board.readcnt}</td>
		</tr>
		<tr>
			<th>등록일자</th>
			<td><fmt:formatDate value="${board.regidate}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button onclick="location.href='${path}/board/modiform?bnum=${board.bnum}'">수정폼</button>	<!--?는 몇번을 수정할 것인지  -->
				<button onclick="removeCheck()">삭제</button>
				<button onclick="location.href='${path}/view/board/replyadd.jsp?bnum=${board.bnum}&restep=0&relevel=0'">
				댓글</button>	
				<!--이렇게 작성했을 때 reply jsp로 이동하는지 확인-->
				<!--컨트롤러로 갈 필요 없이 bnum을 통해 댓글로만 가면 되기 때문에 바로 jsp로 이동했다.-->
				<button onclick="location.href='${path}/board/list'">리스트</button>
				<!--jsp로 이동하는것과 컨트롤러를 거쳐서 이동하는 것의 차이?..굳이 컨트롤러에서 db를 접속할 필요가 없는 것들은
				(서버에서 뭔가 작업해야할것들이 없는 경우에는 그냥 바로 이동한다)-->
			</td>
		</tr>
	
	</table>
	<hr>
	<!--${rlist}-->
	<c:forEach var="reply" items="${rlist}">		<!--댓글을 밑에 정렬해보았다  -->
		<div class="reply">
			<c:forEach var="i" begin="1"  end="${reply.relevel}">	<!--레벨의 갯수만큼 반복되도록 한다-->
				<div style="width:30px">
					<i class="fab fa-replyd"></i>
				</div>
			</c:forEach>
			<div>
<%-- 			댓글번호 :${reply.rnum} <br>
				restep : ${reply.restep} relevel:${reply.relevel}<br>	 --%>	
				<pre>${reply.content}</pre>
				<fmt:formatDate value="${reply.regidate}" pattern="yyyy-MM-dd HH:mm:ss"/>  <br>	<!--날짜와 시간대 포멧데이터로 보기 좋게 정리  -->
				<button 
				onclick="location.href='${path}/view/board/replyadd.jsp?bnum=${board.bnum}&restep=${reply.restep}&relevel=${reply.relevel}'"
				>댓글</button>															<%-- restep=${reply.restep}&relevel=${reply.relevel}
																							0대신 삽입하여 대댓글이 부모의 순서를 고스란히 따라가게끔 한다.--%>	
				<button onclick="location.href='${path}/reply/modiform?rnum=${reply.rnum}'">수정</button>		<!--댓글 수정폼으로 이동시키기 여기서 컨트롤러로 rnum을 넘겨준다. 뷰 url에서 rnum이 넘어가있는지 확인-->
				<button onclick="removeReply(${reply.rnum})">삭제</button>			<!--rnum으로 가면 된다  -->
				<!--반복할때 매개변수로 넘기면 몇번을 삭제하는지가 뜬다  -->																				
			</div>
	 	</div>
	</c:forEach>
	<hr>
</body>
</html>