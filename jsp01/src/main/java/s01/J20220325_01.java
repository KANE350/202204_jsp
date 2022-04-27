package s01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*서블릿 클래스*/
//HttpServlet를 상속받는 클래스
//@WebServlet 어노테이션:매핑 정보(url)를 표현한다.
@WebServlet("/servletTest")
//뷰에 서블릿 호출 주소 http://localhost:8081/jsp01/servletTest  (get방식)
public class J20220325_01 extends HttpServlet {//서블릿을 상속받았기 때문에 서블릿이다. 부모에 있는 모든 기능을 사용하기 위해 상속받은 상태.
	private static final long serialVersionUID = 1L;
	//get방식 호출시 실행되는 메소드
       
 

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request:요청객체
		//response:응답객체
		//response.getWriter().append("contextPath").append(request.getContextPath());
		//contextPath과 getContextPath(jsp01)를 출력해서 응답하라는 의미
	
	//1)
	response.setContentType("text/html' charset=UTF-8");
	PrintWriter out = response.getWriter();	//객체생성
	out.println("<html>");
	out.println("<header>");
	out.println("<title>서블릿테스트</title>");
	out.println("body");
	
	int a=11;
	String s = (a%2==0)?"even":"odd";
	out.println(s);
	
	out.println("</body>");
	out.println("</html>");
	out.close();
	//이렇듯 수기로 잔뜩 입력해야 하는 코드들을 양식화시켜서 html에 자바코드랑 넣게 했다. <%안에 자바코드를 작성하면 서블릿이라는 메소드를 자동으로 생성해서 출력해주기로 약속.
	
	
	
	}
	
	//post방식 호출시 실행되는 메소드
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
