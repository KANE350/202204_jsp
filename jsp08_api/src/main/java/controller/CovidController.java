package controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Covid;
import service.CovidXMLService;

@WebServlet("*.covid")
public class CovidController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CovidXMLService covidXMLService = new CovidXMLService(); 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		System.out.println(uri);
		
		//컨텍스트 패스 
		String path = request.getContextPath();
		
		if (uri.contains("list")) {
			//코로나 현황 조회
			String startDt = request.getParameter("startDt");
			String endDt = request.getParameter("endDt");
			System.out.println(startDt);		//어떤 형태로 나오는지 확인용
			System.out.println(endDt);
			
			List<Covid> clist = covidXMLService.selectList(startDt, endDt); 		//서비스에서 컨트롤러로 보낸걸 받은 상태
			//System.out.println("여기가 컨트롤러"+clist);
			
			//forward로 보내기				//셀렉트 리스트 정보를 forward로 보낸 상태
			request.setAttribute("clist", clist);
			request.getRequestDispatcher("/view/covidList.jsp")
				.forward(request, response);
			
//			System.out.println(covidList);
//			//forward방식으로 보내기 
//			request.setAttribute("covidList", covidList);
//			request.getRequestDispatcher("/view/covidList.jsp")
//				.forward(request, response); 			//url이 바뀌는게 아니라 내 프로젝트 안의 정보가 바뀌는 것.
		
		}else if(uri.contains("dbsave")) {		//콘솔에 찍히는 uri에 dbsave가 포함되어 있으면
			//파싱후 db저장
			String startDt = request.getParameter("startDt");		//uri에 찍힌걸 request get파라미터로 읽어오기
			String endDt = request.getParameter("endDt");
		
			int cnt = covidXMLService.covidParsing(startDt, endDt);		//service에서 리턴을 해줘야 서비스에서 값을 인티저 값을 받아다가 넘길 수 있다.
			
			//redirect 리스트로 이동 (리스트로 주소를 바꾸기 때문에)
			//메시지 전송: cnt건 db저장 완료
			String msg = URLEncoder.encode(cnt +"건 db저장 완료","utf-8");
			response.sendRedirect(path+"/view/covidList.jsp?msg=" +msg+"&startDt="+startDt+"&endDt"+endDt);
			//+"&startDt="+startDt+"&endDt"+endDt는 넘긴 값을 유지하고 싶기에 붙였다(설정한 날짜 그대로 유지하기)	
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
