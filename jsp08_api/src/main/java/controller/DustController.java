package controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.DustService;


@WebServlet("*.dust")
public class DustController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DustService dustService = new DustService();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		System.out.println(uri);
		
		//컨텍스트 패스
		String path = request.getContextPath();
		
		if(uri.contains("pasing")) {	//연결하고 리턴값으로 건수를 받아서 몇건이 저장되었습니다 뿌려주기
			//파싱하고 db에 저장
			String year = request.getParameter("year");
			int cnt = dustService.dustPasing(year);
			
			//하나만 가져가는 거기 때문에 redirect로 이동 
			String msg = URLEncoder.encode(cnt + "건 저장", "utf-8");
			response.sendRedirect(path + "/view/dust.jsp?msg="+msg);	//메시지를 url에 담아 간다
		}else if(uri.contains("list")){
			//조회
			String districtName = request.getParameter("districtName");
			List<Map<String, String>> list = dustService.selectList(districtName);
			System.out.println(list);
			//forward방식으로 보내기
			request.setAttribute("list", list);
			request.getRequestDispatcher("/view/dust.jsp")
				.forward(request, response);
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
