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

import dto.Country;
import service.CountryJSONService;


@WebServlet("*.country")
public class CountryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CountryJSONService countryService = new CountryJSONService();   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		System.out.println(uri);
		
		//컨텍스트 패스
		String path = request.getContextPath();
		
		if(uri.contains("jspform")) {
			//iso(오라클) 조회해서 jsp로 이동하기 위한 목적
			List<Map<String, String>> isolist = countryService.selectList_iso();	//전체조회이기 떄문에 매개변수가 없다
			System.out.println(isolist);								//원래는 디티오를 통해 받아오지만 디티오 없이 맵으로 받아오기로 한다.
			
			//countrylist.jsp 이동(같이 가져가기, forward이동)
			request.setAttribute("isolist",isolist);
			request.getRequestDispatcher("/view/countrylist.jsp")			
				.forward(request, response);
			
		}else if(uri.contains("list")) {
			//db에서 조회			
			String iso = request.getParameter("iso");			//iso라는 이름을 갖고 있는 뷰에서 넘어온 파라미터를 가져온다.
			List<Country> clist = countryService.selectList(iso);
			System.out.println(clist);
			List<Map<String, String>> isolist = countryService.selectList_iso();
			System.out.println(isolist);
			
			//forward방식으로 이동
			request.setAttribute("clist",clist);
			request.setAttribute("isolist", isolist);
			request.getRequestDispatcher("/view/countrylist.jsp")			//iso만 조회하고 가는게 목적 1번
				.forward(request, response);
			
		}else if(uri.contains("dbsave")) {
			//파싱 후 db저장하기 위해서
			String iso = request.getParameter("iso");			//셀렉트 네임을 받아온다. 네임이 자동으로 파라미터로 넘어간다.
			System.out.println(iso);
			//파싱 후 db저장
 			int cnt = countryService.countryParsing(iso);

// 			//iso 조회
//			List<Map<String, String>> isolist = countryService.selectList_iso();		//jsp로 가면서 자동으로 가져가기 때문에 넣지 필요없다.
			
			//redirect 방식 :uri를 변경하기 위해 
			String msg = URLEncoder.encode(cnt+"건 저장", "utf-8");
 			response.sendRedirect(path+"/jspform.country?msg="+msg+"&iso=iso");
		
		}else if(uri.contains("detail")){
		//상세조회로 이동
		String sfty_notice_id = request.getParameter("sfty_notice_id");
		System.out.println(sfty_notice_id);
		
		//한건조회
		Country country = countryService.selectOne(sfty_notice_id);
		//반환해주는 값을 왼쪽에 저장한다.
		
		//forward방식 :detail.jsp로 이동
		request.setAttribute("country", country);
		request.getRequestDispatcher("/view/countrydetail.jsp")
				.forward(request, response);
			
		
		
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
