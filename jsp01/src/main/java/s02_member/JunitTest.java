package s02_member;

/*DBConn테스트*/
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.Test;

class JunitTest {

	@Test
	void testDBConn() {
		//db컨넥션 
		/*반환형이 컨넥션형이기 때문에 컨넥션형의 변수를 만든다*/
		Connection con = DBConn.getConnection();
		System.out.println(con);
		//con이 null이 아닐떄 성공이라는 것을 표시
		assertNotNull(con);
		//만약 널일떄 성공한다는 메세지를 넣고 싶다면?
		//assertNull(con);
	}
		@Test
		void testInsert(){
			//Member객체 만들기 
			//멤버를 dao에 매개변수로 넘기기 위해서 만들었다. dto에 다 집어넣은 다음에 멤버만 넘기면 되니까. 
			Member member = new Member();//이렇게 만들어도 되지만 이렇게 하면 값이 널값이기 때문에 세터나 게터를 이용해서 값을 채워야 한다. 
			member.setUserid("hong");
			member.setPasswd("1111");
			member.setName("홍길동");
			member.setEmail("hong@gmail.com");
			System.out.println(member);
			//데이터가 잘 들어갔는지 확인 
			
			//넘겨주기
			MemberDAO mdao = new MemberDAO();			
			int cnt = mdao.insert(member);
			System.out.println(cnt+"건 추가");
		}
			
			
		
		


}
