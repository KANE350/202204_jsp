package controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dto.Board;
import dto.Boardfile;
import dto.Reply;
import service.BoardService;
import service.BoardfileService;
import service.ReplyService;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {	//여기 안에 들어있는 파일을 통해서 web.xml에 있는 savedir을 읽어온다 if add참고
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();
	private ReplyService replyService = new ReplyService();
	private BoardfileService boardfileService = new BoardfileService();
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uri = request. getRequestURI();				//uri찍어보기, add 화면을 새로고침했을때 uri가 잘 찍히는지 확인 
		System.out.println(uri);
	
		String path = request.getContextPath();
		
		//게시물 등록(add에서 추가를 눌렀을때 서브밋이 제대로 일어나서 이쪽으로 넘어오는가)
		if(uri.contains("add")){
		//멀티파트 폼데이터는 쪼개서 보낸다 때문에 cos를 활용하여 저장한다. 
		//파일저장경로는 자주 바뀌기 때문에 wep.xml을 통해 저장한다.
		//String saveDirectory = "D:/kgr/savedir"; 이렇게 해도 되지만
		//프로젝트의 web.xml의 context-param의 값을 읽는다 아래와 같이 하는게 더 좋다.
		String saveDirectory = getServletContext().getInitParameter("savedir");
		int size = 1024*1024*20; //20byte
		MultipartRequest multi = 
				new MultipartRequest(request, saveDirectory, size, "utf-8",
						new DefaultFileRenamePolicy());
																//new DefaultFileRenamePolicy()); 파일명이 겹칠것을 방지, 중복되는 파일이 있으면 이름을 다르게 해서 저장한다.
																//여기까지 작성하고 savedir에 파일이 제대로 올라가는지 확인
		//클라이언트 접속 ip정보 
		String ip = request.getRemoteAddr();	//아이피는 리퀘스트 객체한테 달라고 해야하지만
		String userid = multi.getParameter("userid");	//나머지는 멀티로 가져오면 된다.
		String subject = multi.getParameter("subject");
		String content = multi.getParameter("content");
		
		//board dto만들기
		Board board = new Board();
		board.setUserid(userid);
		board.setSubject(subject);
		board.setContent(content);
		board.setIp(ip);
		System.out.println(board);
		
		//실제 올라간 파일의 이름 리스트(파일을 꺼내서 db에 저장했다.)
		List<String> filenames = new ArrayList<>();	//얘를 서비스에 넘긴다
		//파일의 이름의 모음 가져오기
		Enumeration<String> files = multi.getFileNames(); //<>는 제너릭 타입이라는 것을 의미한다.
		//파일네임이 몇개일지 모르기 때문에 와일문을 돌리면서 꺼낸다
		while (files.hasMoreElements()) {					//어떠한 모음에 더 있다면, 즉 다음 자료가 있다면 반복하라 
			String name = files.nextElement();		//파일의 이름을 무조건 가져오기 떄문에 적게 가져와도 콘솔에는 다섯개 다 띄운다.  //name을 가져다가(진짜 올라간 파일이름x add에서 설정한 파일 네임, 엘리먼트의 이름이다.)
			//System.out.println(name);				//파일이 없다면 처리하지 않아야 한다. 이런것들은 서버에서 진행한다. 
			String filename = multi.getFilesystemName(name);	//실제 저장된 파일 이름
			//System.out.println(filename);
			if (filename != null) filenames.add(filename);	//추가된 파일의 이름 리스트 
		}
		System.out.println(filenames);//리스트 반복문으로 출력, 올라간 파일의 이름만 출력해준다.
		
		//service 호출하기 
		boardService.insert(board, filenames);		//잘 찍혀지는지, 데이터베이스에 들어가는지 확인.
		
		//redirect 이동
		response.sendRedirect(path+"/board/list?msg=" 
					+ URLEncoder.encode("추가완료", "utf-8"));	//위에 콘텍스트 패스를 작성해뒀기 떄문에 생략

		}else if(uri.contains("list")){	//구분자를 통해서 무슨 작업을 하는지 알아내기
			//리스트(dao를 거쳐서 최종적으론 boardmapper까지 가는게 목적)
			String findkey = request.getParameter("findkey");
			String findvalue = request.getParameter("findvalue");
			//curpage읽기
			String curPage_s = request.getParameter("curPage");
			int curPage = 1;
			if (curPage_s != null)	//curPage가 null일때 처리
				curPage = Integer.parseInt(curPage_s);	//이문장만 단독 사용하면 curPage가 없을 경우 오류가 난다(공백으로 조회할경우 int는 공백을 숫자로 바꿀 수 없다.)
			System.out.println("현재페이지:" + curPage) ;
			
			//조회조건 Map만들기(이전다음블럭)
			Map<String, Object> findmap = new HashMap<>();
			if (findkey==null || findkey.equals("")) findkey = "userid";		//널 또는 공백처리
			if (findvalue == null) findvalue = "";
			findmap.put("findkey", findkey);
			findmap.put("findvalue", findvalue);
			findmap.put("curPage", curPage);			//만들기는 컨트롤러에서 만들고 넣기는 서비스에서 넣었다. 서비스에서 넣은 데이터가 컨트롤러에 똑같이 들어가는, 컨트롤러에서 호출이 되는 상태
			
			List<Board> blist = boardService.selectList(findmap);	//보드의 리스트를 리턴받기
			System.out.println(blist);	//조회를 눌렀을때 리스트의 값이 잘 넘어왔는지 확인
			//forward방식으로 이동할때 boardlist도 가져가고, findmap도 뷰에 보내서 블럭 제어를 하기 위함.
			//forward방식 이동
			request.setAttribute("blist", blist);
			request.setAttribute("findmap", findmap);	//페이징 처리 결과(페이징처리한 내용들이 들어있다)
			
			
			request.getRequestDispatcher("/view/board/list.jsp")
					.forward(request, response);
			
		}else if(uri.contains("detail")){	//lsit의 링크 확인
			//상세페이지 이동
			int bnum = Integer.parseInt(request.getParameter("bnum"));				//bnum을 파라미터로 받아서 문자열을 int형으로 변경해준다
			System.out.println(bnum);
			
			//조회수 +1
			boardService.update_readcnt(bnum);				//redcnt를 실행하고 이때 bnum을 넘기면 된다
			
			//1)게시물 조회
			Board board = boardService.selectOne(bnum);
			System.out.println(board);
			//2)게시물 파일들 조회					//특정한 보드넘버가 5번이라면, 5번 파일을 가져오고 싶다.
			List<Boardfile> bflist= boardfileService.selectList(bnum);
			System.out.println(bflist);	//속한 파일들이 제대로 출력되는지 확인			
			//3)댓글 조회
			List<Reply> rlist = replyService.selectList(bnum);	//bnum을 알아야 selectlist가 가능하기 떄문이다
			
			
			//forward방식 
			request.setAttribute("board", board);
			request.setAttribute("bflist", bflist);
			request.setAttribute("rlist", rlist);
			
			
			request.getRequestDispatcher("/view/board/detail.jsp")
					.forward(request, response);
		}else if(uri.contains("remove")){
			//삭제
			int bnum = Integer.parseInt(request.getParameter("bnum"));
			System.out.println(bnum);	//해당 게시글 번호가 뜨는지 확인
			//번호를 서비스에 넘겨서 제거해달라고 요청해야 한다.
			boardService.delete(bnum);	//서비스에서 메소드 생성
			
			//redirect방식 이동 (삭제 후 다시 리스트로 돌아가도록)
			response.sendRedirect(path+"/board/list");
			
		}else if(uri.contains("modiform")){
			//수정폼으로 이동
			int bnum = Integer.parseInt(request.getParameter("bnum"));				//bnum을 파라미터로 받아서 문자열을 int형으로 변경해준다

			//board 조회
			
			Board board = boardService.selectOne(bnum);
			
			//boardfile조회
			List<Boardfile> bflist = boardfileService.selectList(bnum);
			
			//forward이동 : modify.jsp로 이동
			request.setAttribute("board", board);
			request.setAttribute("bflist", bflist);
			request.getRequestDispatcher("/view/board/modify.jsp")
				.forward(request, response);
		
		}else if(uri.contains("modify")){
			//수정
			/* 파일저장경로:web.xml에서 읽기 */
			String saveDirectory = getServletContext().getInitParameter("savedir");
			int size = 1024*1024*20; // 20mbyte
			MultipartRequest multi = 
					new MultipartRequest(request,saveDirectory,size,"utf-8", 
							new DefaultFileRenamePolicy());
			
			//클라이언트 접속 ip정보
			String ip = request.getRemoteAddr();
			int bnum = Integer.parseInt(multi.getParameter("bnum"));
			String userid = multi.getParameter("userid");
			String subject = multi.getParameter("subject");
			String content = multi.getParameter("content");
			//board dto만들기
			Board board = new Board();
			board.setBnum(bnum);
			board.setUserid(userid);
			board.setSubject(subject);
			board.setContent(content);
			board.setIp(ip);
			System.out.println(board);
			
			//실제올라간 파일의 이름리스트
			List<String> filenames = new ArrayList<>();
			//파일의 이름의 모음
			Enumeration<String> files = multi.getFileNames();
			while(files.hasMoreElements()) { //다음자료가 있다면
				String name = files.nextElement(); //file엘리먼트의 name
				String filename = multi.getFilesystemName(name); //실제저장된 파일이름
				if (filename != null) filenames.add(filename); //추가된 파일이름 리스트
			}
			System.out.println("추가할 파일 리스트" + filenames);
			
				
			//삭제할 파일번호들
			String[] removefiles = multi.getParameterValues("removefile");	//파일들을 여러개 가져오기 위해 배열로 가져오는 valuse를 이용한다. 
			System.out.println("삭제할 파일 리스트" + Arrays.toString(removefiles));
			
			//서비스 호출
			boardService.update(board, filenames, removefiles);
			//detail로 이동
			//url이 바뀌길 원하기 때문에 redirect이동
			response.sendRedirect(path+"/board/detail?bnum="+bnum);	//자바코드기 때문에 위에서 선언한 path변수를 사용
			
		}else {
			System.out.println("잘못된 uri");	//오타방지
		
		}	

	}
	






	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
