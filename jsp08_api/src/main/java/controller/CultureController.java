package controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CultureService;

@WebServlet("*.culture")
public class CultureController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CultureService cultureService = new CultureService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		System.out.println(uri);
	
		//컨텍스트 패스
		String path = request.getContextPath();
		
		if(uri.contains("addr")) {
			//파싱 후 viwe에 맵전달
			String name = request.getParameter("name");	//뭘 검색하고 싶은지를 전달해야 하기 떄문에 url의 name을 넘긴다.
			Map<String, Object> map = cultureService.cultureParsing(name);
			System.out.println(map);
			
			//위도, 경도 구하기 
			String addr = request.getParameter("addr");
			Map<String, Double> geomap = cultureService.geocoding((String) map.get("ADDR"));		//지역코딩에는 문자열로 넘겨줘야 하기 때문에 다운캐스팅을 해준다.
			System.out.println(geomap);
			System.out.println("geomap:" + geomap);	//위도경도가 없는 애들은 널로 뜨는 것을 알 수 있다.
			
			//forward방식 이동
			request.setAttribute("geomap", geomap);
			request.setAttribute("map", map);
			request.getRequestDispatcher("/view/cultureaddr.jsp")
					.forward(request, response);
		
		
		}
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
