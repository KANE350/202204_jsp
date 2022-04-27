package s02_member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//db 컨넥션을 반환하는 메소드
public class DBConn {
	//static:정적멤버,여기서는 정적메소드를 나타낸다
	//여기에 static을 달면 dao에서 객체를 따로 만들지 않아도 겟 컨넥션을 호출 가능하다.
	static Connection getConnection() {
		Connection con=null;
		//url, user, password	컨넥션 객체생성시 필요한 것들
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String user="hr";
		String password = "hr";
		
		//오라클 드라이버 로딩 
		//오라클 체크예외처리
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("로딩완료");
			//컨넥션 객체 생성
			//컨에 값 삽입
			con = DriverManager.getConnection(url, user, password);
			System.out.println("컨넥션 완료");
			//예외처리가 났을떄 어떻게 할것인지 등등 설정
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("접속 실패");
			e.printStackTrace();
		}
		
		
		
		return con;
	}
}
