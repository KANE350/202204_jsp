package controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import dto.Board;
//맵핑정보를 확장자패턴으로 만들었다
@WebServlet("*.board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardDAO bdao = new BoardDAO();


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		System.out.println(uri);
		
		if	(uri.contains("add")) {
			//게시물 등록 
			String writer = request.getParameter("writer");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			
			
			//board객체 생성
			Board board = new Board(writer, subject, content);
			//세 값을 생성자를 이용해서 넣으려면 Board에 세개짜리 생성자를 만들어야 한다.
			System.out.println(board);
			//돔에 값을 입력하고 제대로 들어갔는지 콘솔에서 확인
			
			//dao의 insert 실행
			BoardDAO bdao = new BoardDAO();
			int cnt = bdao.insert(board);
			System.out.println(cnt);
			
			//게시물 등록으로 이동 
			//여기서 겹치지 않게 하기 위해 맨 위를 확장자 패턴으로 달았다.
			String msg= URLEncoder.encode("추가 완료", "utf-8") ;
			//이렇게 하고 새로고침 후 내용을 클릭하면 주소창에 추가+완료 라고 뜬 것을 확인할 수 있다. 
			//자바와는 코드값이 다르기 때문에 메시지를 utf8로 번역하여 보낸다. 
			response.sendRedirect("/jsp02_board/board/add.jsp?msg="+msg);
			
		}else if(uri.contains("list")) {
			//findvalue가 갖고 있는 값을 리턴, dao에 있는 list를 리턴
			String findkey = request.getParameter("findkey");
			//리스트
			//post방식이나 get방식이나 똑같이 파라미터로 읽으면 된다
			String findvalue = request.getParameter("findvalue");
			if (findkey==null) findkey="writer";
			if (findvalue == null) findvalue="";
			System.out.println("findkey:" + findkey);
			System.out.println("findvalue:" + findvalue);
			//list의 버튼용
			List<Board> blist = bdao.selectList(findkey, findvalue);
			
			System.out.println(blist);
			//forward이동
			request.setAttribute("blist", blist);
			request.getRequestDispatcher("/board/list.jsp")
				.forward(request, response);
		}else if(uri.contains("modiform")) {
			//수정폼으로 이동
			int seq = Integer.parseInt(request.getParameter("seq"));
			//dao selectOne호출
			Board board = bdao.selectOne(seq);
			//modify.jsp로 이동(board)
			request.setAttribute("board", board);
			request.getRequestDispatcher("/board/modify.jsp")
				.forward(request, response);
		}else if(uri.contains("modify")){
			int seq = Integer.parseInt(request.getParameter("seq"));
			String writer = request.getParameter("writer");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			
			Board board = new Board();
			board.setSeq(seq);
			board.setWriter(writer);
			board.setSubject(subject);
			board.setContent(content);
			System.out.println(board);
			int cnt = bdao.update(board);
			
			//redirect이동
			response.sendRedirect("/jsp02_board/list.board?msg=" + URLEncoder.encode("수정완료", "utf-8"));
			
		}else if(uri.contains("remove")){
			//삭제
			//checkbox(여러개를) 파라미터로 읽기  
			String[] removes = request.getParameterValues("removes");
			System.out.println(Arrays.toString(removes));
			//여러개를 한꺼번에 넘겨야 하기 때문에 values사용, 문자열의 배열이기 떄문에 String[]에 담는다.
			//그냥 출력하면 주소값만 출력된다. 배열이기 떄문에 다음과 같은 메소드를 사용해 출력한다. 
			
			//반복문을 이용해서 삭제
			for(String seq:removes) {
				bdao.delete(Integer.parseInt(seq));  
				//delete의 seq가 int형이기 떄문에 변환하여 사용하였다. 
			}
			
			//redirect:조회로 이동(list.board 호출)  
			response.sendRedirect("/jsp02_board/list.board?msg=" + URLEncoder.encode("삭제완료", "utf-8"));
		
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
