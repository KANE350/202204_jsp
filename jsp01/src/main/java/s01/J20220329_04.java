package s01;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class J20220329_04
 */
@WebServlet("/J20220329_04")
public class J20220329_04 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//post방식이기 때문에 인코딩
		String uri = request.getRequestURI();
		System.out.println(uri);
		//여기까지 적고 돔에서 키를 눌렀을때 콘솔에 잘 출력되는지 확인 
		
		String pname = request.getParameter("pname");
		int price = Integer.parseInt(request.getParameter("price"));
		int qty = Integer.parseInt(request.getParameter("qty"));
		System.out.println(pname);
		System.out.println(price);
		System.out.println(qty);
		
		int amount = price * qty; //판매금액
		//경로가 변경
		response.sendRedirect("/jsp01/view/servlet/20220329_04_sales.jsp?amount" +amount);
	}
	



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
