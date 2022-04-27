package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Reply;
import service.ReplyService;
//컨트롤러를 생성하면 컴퓨터가 인식하지 못하니 직접 서버를 껐다 켜줘야 한다.
//댓글 처리 콘트롤러 (원래 있던걸 지우고 /reply/*로 맵핑)
@WebServlet("/reply/*")			
public class ReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ReplyService replyService = new ReplyService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		System.out.println(uri);
		String path = request.getContextPath();
		
		if(uri.contains("add")) {
			//파라메터로 읽어서 dto에 넣기
			int bnum = Integer.parseInt(request.getParameter("bnum"));
			int restep = Integer.parseInt(request.getParameter("restep"));			
			int relevel = Integer.parseInt(request.getParameter("relevel"));			
			String content = request.getParameter("content");
			Reply reply = new Reply();
			reply.setBnum(bnum);					//reply의 name을 읽어오는 것이다.
			reply.setRestep(restep);
			reply.setRelevel(relevel);
			reply.setContent(content);
			System.out.println(reply);
			
			replyService.insert(reply);			//서비스를 호출해서 컨트롤러와 연결
			
			//게시물의 상세조회 이동
			response.sendRedirect(path + "/board/detail?bnum=" + bnum);
		}else if (uri.contains("remove")){
			//댓글 삭제
			int rnum = Integer.parseInt(request.getParameter("rnum"));
			replyService.delete(rnum);
			
			//detail로 이동시 필요
			int bnum = Integer.parseInt(request.getParameter("bnum"));

			//redirect board detail로 이동(댓글 삭제 후 창을 여기로 이동)
			response.sendRedirect(path+"/board/detail?bnum="+ bnum);
			
		}else if(uri.contains("modiform")){
			//수정폼으로 이동
			int rnum = Integer.parseInt(request.getParameter("rnum"));
			//댓글 한건 조회
			Reply reply = replyService.selectOne(rnum);		//이렇게 넣으면 이 값을 디티오에 넣어서 반환해준다
			
			//replymodify.jsp로 이동
			request.setAttribute("reply", reply);			//modify의 name값을 통해 받은것
			request.getRequestDispatcher("/view/board/replymodify.jsp")
				.forward(request, response);
			
		}else if(uri.contains("modify")){
			//댓글수정
			int rnum = Integer.parseInt(request.getParameter("rnum"));
			String content = request.getParameter("content");
			Reply reply = new Reply();
			reply.setRnum(rnum);
			reply.setContent(content);				
			System.out.println(reply+"여기");			//수정했을때 수정값이 콘솔에 넘어오는지 확인			
			replyService.update(reply);
			
			//redirect board detail로 이동	
			int bnum = Integer.parseInt(request.getParameter("bnum"));			//detail로 가려면 보드넘버가 필요하기 때문에 받아서 간다.
			response.sendRedirect(path+"/board/detail?bnum="+ bnum);
			
		}else {
			System.out.println("잘못된 uri");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
