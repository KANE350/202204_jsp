package s01;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//contextpath제외
//jsp01/test:url
//디렉토리 패턴
@WebServlet("/J20220328_01") /* 디렉토리 패턴 */
/* j20220328란 애를 맵핑하겠다. 따라서 맵핑란에 테스트를 적주면 된다. /는 절대경로. 경로를 지정해주었다.
 * 여기에는 컨텍스트패스를 적지 않는다.*/
public class J20220328_01 extends HttpServlet{
	/* 소스-오버라이드임플리먼트 메소드 - doget과 dopost만 선택. 나오면 재정의 할 것이기 떄문에 super는 지워준다 */

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		System.out.println(uri);
		/* 이를 작성하고 화면에 클릭시 콘솔에 /jsp01/j20220328(uri)가 뜬다면 잘 연결된 것을 알 수 있다 */
		String name = req.getParameter("name");			//네임을 매핑에서 가져오려면 이렇게 읽으면 된다
		System.out.println(name);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
		//포스트 방식도 겟방식과 작성하는 코드가 동일하다. 때문에 doget처럼 위와 같이 입력하면 둘 다 된다.
		//거꾸로 해도 되긴 하는데, 포스트 방식이 겟을 호출하기 때문에 get에만 써주고 post에는 doget을 작성하면 된다. 
	}
	
}
