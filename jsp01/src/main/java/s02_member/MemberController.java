package s02_member;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//이런식으로 패턴을 임의지정하는 것을 디렉토리 패턴이라 한다.
// /member/로 시작되는 모든 매핑 처리
//(아스트릭스 표기를 하면 멤버 내의 모든 파일이 처리 가능하다)
@WebServlet("/member/*")

public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberDAO mdao = new MemberDAO();	//dao객체 필드로 생성 위에서 한번 만들면 아래에서 반복해서 안 만들어도 된다. 
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//인코딩(utf-8)
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		System.out.println(uri);
		
		//join처리를 위한 문장
		if(uri.contains("join")) {
			//view 데이터 읽기
			String userid = request.getParameter("userid");
			String passwd = request.getParameter("passwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			
			//Member객체 생성하고 초기화(뷰 데이터를 담아가기 위해서, 위의 순서대로 넣어주면 된다)
			Member member = new Member(userid, passwd, name, email);
			System.out.println(member);
			/* 이렇게 작성하고 돔 화면에서 작성 후 클릭했을때 콘솔 멤버에 모든 값이 제대로 담겨 나오는지 확인 */
			//DBConnection
		
			//MememberDAO객체생성하고 dao에 있는 인서트 실행하기멤(멤버 넘겨주기)
//			db의 인서트 실행해서 값이 제대로 들어가는지 확인, pk이인 id를 인식해서 기존 아이디와는 다른 값 넣기
			
			int cnt = mdao.insert(member);
			System.out.println(cnt+"건 추가");
	
			
			//회원가입 완료 메세지 view에 보내기(ridirect방법) 
			//20220329_01_insert.jsp
			String msg = URLEncoder.encode(cnt+"건추가", "utf-8");
			response.sendRedirect("/jsp01/view/db/20220329_01_insert.jsp?msg="+msg);
			System.out.println("가입");
		}else if(uri.contains("selectList")){
			
			//조회리스트
			
			List<Member> mlist = mdao.selectList();
			System.out.println(mlist);
			//20220330_02_selectList.jsp
			//forward이동(대량의 데이터 전송)
			request.setAttribute("mlist", mlist);
			request.getRequestDispatcher("/view/db/20220330_02_selectList.jsp")
				.forward(request, response);
			
			
		}else if(uri.contains("modify")) {
			//수정폼으로 이동
			//한건조회
			String userid = request.getParameter("userid");
			System.out.println(userid);
			
			
			Member member = mdao.selectOne(userid);
			System.out.println(member);
			
			//forward 이동(20220330_03_update.jsp)
			request.setAttribute("member", member);
			request.getRequestDispatcher("/view/db/20220330_03_update.jsp")
				.forward(request, response);
			
		}else if(uri.contains("update")) {
			//업데이트가 있다면 수정해라
			//수정
			String userid = request.getParameter("userid");
			String passwd = request.getParameter("passwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			
			//멤버객체
			Member member = new Member();
			member.setUserid(userid);
			member.setPasswd(passwd);
			member.setName(name);
			member.setEmail(email);
			System.out.println(member);
			int cnt = mdao.update(member);
			System.out.println(cnt+"건 수정");
			
			
			//redirect 이동(조회)
			response.sendRedirect("/jsp01/member/selectList");
			
			
		}else if(uri.contains("remove")){
			//여기서 remove는 update의 로케이션 하이퍼링크의 리무브이다. 
			//삭제
			String userid = request.getParameter("userid");
			//dao클래스에서 설정한 삭제 매개변수 String userid
			int cnt = mdao.delete(userid);
			//dao클래스에서 리턴한 값을 반영
			System.out.println(cnt+"건 삭제");
			
			//조회
			response.sendRedirect("/jsp01/member/selectList");
		}
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
