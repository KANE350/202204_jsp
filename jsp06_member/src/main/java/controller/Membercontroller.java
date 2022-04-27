package controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.tribes.MembershipService;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dto.Member;
import service.Memberservice;

@WebServlet("*.member")
/* 확장자 패턴으로 변경 */
public class Membercontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Memberservice memberService = new Memberservice();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		System.out.println(uri);
		
		//패스 호출
		String path = request.getContextPath();
		
		if(uri.contains("join")) {
			//회원가입
			//saveDirecory : 파일저장경로(서버의 경로)
			//String saveDirectory = "D:/kgr/savedir"; 이렇게 해도 되지만
			//프로젝트의 web.xml의 context-param의 값을 읽는다 아래와 같이 하는게 더 좋다.
			String saveDirectory = getServletContext().getInitParameter("savedir");
			
			
			//size : 업로드 파일 사이즈를 제한해주어야 한다.
			int size = 1024 * 1024 * 10;		//10mbyte
			//DefaultFileRenamePolicy(): 같은 이름의 파일이 있을 때 파일이름변경
			MultipartRequest multi = new MultipartRequest (request, saveDirectory, size, "utf-8", new DefaultFileRenamePolicy());
			/* 유저아이디같은 것들은 멀티파트로 갔기 떄문에 파라미터로 읽어들일 수 없는 상태 때문에 이를 통해서 받아와야 한다 */
			/* 첨부한 파일이 savedrectory에 담기는 걸 확인할 수 있다. */												
			
			//MultipartRequest객체의 메소드를 이용해서 데이터 얻기
			String userid = multi.getParameter("userid");
			String passwd = multi.getParameter("passwd");
			String zipcode = multi.getParameter("zipcode");
			String addrload = multi.getParameter("addrload");
			String addrdetail = multi.getParameter("addrdetail");
			//실제 저장된 파일이름 
			String filename = multi.getFilesystemName("photo");
		
			//Member객체 생성하기(넘기기 위해)
			Member member = new Member();
			member.setUserid(userid);
			member.setPasswd(passwd);
			member.setZipcode(zipcode);
			member.setAddrload(addrload);
			member.setAddrdetail(addrdetail);
			member.setFilename(filename);
			System.out.println(member);
			
			//서비스 호출
			int cnt = memberService.insert(member);
			System.out.println(cnt+"건 추가");
			
			//redirect 이동
			response.sendRedirect(path+"/member/join.jsp?msg=" + URLEncoder.encode(cnt+"건 추가", "utf-8"));
		}else if (uri.contains("login")) {
		//로그인
		String userid = request.getParameter("userid");
		String passwd = request.getParameter("passwd");
		System.out.println(userid);
		System.out.println(passwd);
		//서비스호출
		Map<String, Object> rmap = memberService.loginCheck(userid, passwd);
		//msg : 로그인성공, 회원 미존재, 비밀번호 불일치
		
		//로그인 성공시 세션을 생성
		int code = (int)rmap.get("code");
		if (code==0) {//성공
			//세션객체생성
			 HttpSession session = request.getSession(); //sessionid별로 ssession생성
			 //세션에 값 담기
			 session.setAttribute("userid", userid);
			 session.setAttribute("passwd", passwd);
			 //세션의 유효시간
			 session.setMaxInactiveInterval(60*60*3); //3시간
			 
			 //쿠키에 userid저장
			 //idSave값 읽기
			 String idsave = request.getParameter("idsave");//읽어서 얘가 클릭이 되어있다면 쿠키에 저장
			 System.out.println("idsave"+ idsave);
			 Cookie useridCooke = new Cookie("userid", userid);//쿠키생성 쿠키1개당 1개의 객체 생성, 여기에 userid값을 집어넣는다
			 //useridCooke.setMaxAge(60);//10초후에 사라진다, 0으로 하면 지금 바로 삭제하겠다는 뜻.
			 if(idsave==null) {//기억하지 않기
				 useridCooke.setMaxAge(0);//cookie삭제
			 }
			 
			 response.addCookie(useridCooke);//response를 해야 클라이언트로 가고 클라이언트에서 리스펀스에서 받은 값을 쿠키에 저장한다.
			 
			 
			 
				 //메인으로 이동
				 response.sendRedirect(path +"/main.jsp");
			}else {
				// 로그인 실패시 이동
				String msg = (String)rmap.get("msg");
				response.sendRedirect(path + "/login.jsp?msg="+URLEncoder.encode(msg, "utf-8") );	//로그인 실패 메시지를 가지고 login.jsp로 가져가게 한다.
			}
		}else if (uri.contains("logout")) {
			//로그아웃
			HttpSession session = request.getSession();
			session.invalidate(); //모든 섹션변수 삭제
			response.sendRedirect(path + "/login.jsp?msg="+URLEncoder.encode("로그아웃완료", "utf-8") );
		}else if (uri.contains("info"))/*/header의 info*/  {
			//회원정보 조회 파라메터에서 userid정보를 얻는다
			String userid = request.getParameter("userid");	//userid를 가져오면 어떤 오브젝트를 돌려준다, 얘를 String에 저장
			System.out.println(userid);	//회원정보를 눌렀을때 콘솔에 회원아이디가 뜨는지 확인
			Member member = memberService.selectOne(userid);
			System.out.println(member);//콘솔창에 회원정보가 맞게 뜨는지 확인
			//forward이동
			request.setAttribute("member", member);//requestScope에 넣겠다 
			request.getRequestDispatcher("/member/info.jsp").forward(request, response);//info로 이동해서 작성
			//request는 인포에만 넣고 더 가져갈 일이 없으면 넣고
			//session은 리스트에보내서도 계속 이동하는, 어딜 가든지 계속 따라다녀야 하는 헤더같은 경우에는 sessionScope에 넣어준다
		
		}else if(uri.contains("list")) { //얘를 실행하기 위해서는 dao에서 파인드키, 파인드밸류를 만들어야 한다.
			//조회정보
			String findkey = request.getParameter("findkey");	//list에 있는 키를 읽어와야 품목별로 검색이 가능해진다.
			String findvalue = request.getParameter("findvalue");
			//검색조건이 없을때(null일경우)처리
			//findkey 혹은 findvalue가 널이어도 이퀄스를 사용 가능하게 하기 위해 기본값을 정해줬다.
			//리스트를 누르면 전체화면이 뜨도록 설정
			if (findkey==null) findkey="userid";
			if (findvalue==null) findvalue="";
			
			
			
			Map<String, String> findmap = new HashMap<>();
			findmap.put("findkey", findkey);	//찾고자 하는 키의 값과 값을 findkey(addrload)를 넣는다.
			findmap.put("findvalue", findvalue);	//이 두가지를 넣어서 서비스로 넘겨주면 된다.
			
			List<Member> mlist = memberService.selectList(findmap);	//없어서 자동완성이 안되는 상태. 이런애를 만들어서 리스트에 넣어준다. 자동완성된 메소드가 서비스에 생성된다.
			System.out.println(mlist); //콘솔에 해당 리스트가 전부 조회되면 성공
			
			//forward이동(대량의 정보로 이동할 때에는 반드시 forward 사용)
			request.setAttribute("mlist", mlist);
			request.getRequestDispatcher("/member/list.jsp").forward(request, response);
		
			}else if(uri.contains("modiform")){
			//수정폼으로 이동
			String userid = request.getParameter("userid");
			System.out.println(userid);
			Member member = memberService.selectOne(userid);
			
			request.setAttribute("member", member);
			request.getRequestDispatcher("/member/modify.jsp")
				.forward(request, response);
				
		
		}else if(uri.contains("modify")) {
			//수정
			//멀티파트 폼데이터기 떄문에 파라미터를 읽지 못한다. 
			//프로젝트의 web.xml의 context-param의 값을 읽는다 아래와 같이 하는게 더 좋다.
			String saveDirectory = getServletContext().getInitParameter("savedir");
			
			
			//size : 업로드 파일 사이즈를 제한해주어야 한다.
			int size = 1024 * 1024 * 10;		//10mbyte
			//DefaultFileRenamePolicy(): 같은 이름의 파일이 있을 때 파일이름변경
			MultipartRequest multi = new MultipartRequest (request, saveDirectory, size, "utf-8", new DefaultFileRenamePolicy());
			/* 유저아이디같은 것들은 멀티파트로 갔기 떄문에 파라미터로 읽어들일 수 없는 상태 때문에 이를 통해서 받아와야 한다 */
			/* 첨부한 파일이 savedrectory에 담기는 걸 확인할 수 있다. */												
			
			//MultipartRequest객체의 메소드를 이용해서 데이터 얻기
			String userid = multi.getParameter("userid");
			String passwd = multi.getParameter("passwd");
			String newpasswd = multi.getParameter("newpasswd");	//변경 패스워드
			String zipcode = multi.getParameter("zipcode");
			String addrload = multi.getParameter("addrload");
			String addrdetail = multi.getParameter("addrdetail");
			//실제 저장된 파일이름 
			String sysfilename = multi.getFilesystemName("photo");
			System.out.println("실제 올라간 파일 이름" + sysfilename);
			String filename=null;
			if (sysfilename==null)	//파일을 변경하지 않았을 경우(파일이름이 안바뀌면)
				filename = multi.getParameter("filename");
			else
				filename = sysfilename;
	
		
			//Member객체 생성하기(넘기기 위해)
			Member member = new Member();
			member.setUserid(userid);
			member.setPasswd(passwd);
			member.setNewpasswd(newpasswd);
			member.setZipcode(zipcode);
			member.setAddrload(addrload);
			member.setAddrdetail(addrdetail);
			member.setFilename(filename);
		
			System.out.println(member);
			//서버가 널이라고 뜨면 lib의 web.xml파일 경로명 설정을 제대로 했는지 확인
			Map<String, Object> rmap = memberService.update(member);
			System.out.println(rmap);
			if((int)rmap.get("code") == 0) {
				//redirect 개인정보 이동(수정을 누른 후 개인정보창으로, 메시지 하나만 보내면 되기 떄문에 redirect이동)
				response.sendRedirect(path +"/info.member?userid="+userid);
			}else {//실패일 경우(비밀번호가 틀렸을 경우 수정폼으로 가려고 한다)
				//redirect 수정폼으로 이동
//				response.sendRedirect(path +"/modiform.member?userid="+userid+"&msg="
//						+URLEncoder.encode((String)rmap.get("msg"), "utf-8"));	
			
				//response.sendRedirect방식의 경우 입력하고 넘어갔다가 실패시 당시 입력한 값이 초기화된다. 이게 불편한 사용자를 고려해서 
				//입력했을때 수정값이 멤버에 담긴 상태니 이를 포워드 방식으로 작성하면 입력한 값이 남아있도록 할 수 있다. 
				//forward 방식
				request.setAttribute("member", member);
				request.setAttribute("msg", rmap.get("msg"));
				request.getRequestDispatcher("/member/modify.jsp")
					.forward(request, response);
			}
			
			
			
		}else if (uri.contains("remove")) {
			//삭제(회원탈퇴)
			String userid = request.getParameter("userid");
			int cnt = memberService.delete(userid); //멤버서비스로 넘어가서 메소드 작성
			
			//섹션 지우기
			HttpSession session = request.getSession();
			session.invalidate();
			
			//redirect 방식 이동
			response.sendRedirect(path + "/main.jsp?msg="+URLEncoder.encode("회원탈퇴완료","utf-8"));
		}

}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
