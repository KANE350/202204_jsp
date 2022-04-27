package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MoneyDAO;

//매출관리 컨트롤러
@WebServlet("/money/*")
/* 매출은 이쪽 컨트롤에서 처리하도록 컨트롤러를 나눈다 */
public class MoneyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MoneyDAO mdao = new MoneyDAO();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
   		String uri = request.getRequestURI();
   		System.out.println(uri);
   		//회원매출정보를 클릭했을때 콘솔에 uri. 즉 머니 리스트가 뜨는지 확인
   		
   		if(uri.contains("list")) {
   			//회원매출조회
   			//dao 호출
   			List<Map<String, Object>> mlist = mdao.selectList();
   			System.out.println(mlist);
   			
   			//forward 이동
   			request.setAttribute("mlist", mlist);
   			request.getRequestDispatcher("/view/moneyList.jsp")
   				.forward(request, response);
   		}else {
   			System.out.println("잘못된 url");
   			//이렇게 작성해두면 url이 잘못된걸 알 수 있다.
   		}	
	}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
