package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//db 컨넥션을 반환하는 메소드
public class DBConn {
	//con객체를 싱글톤 패턴으로: 인스턴스를 하나만 생성하고 재사용하게 된다. 
	
	//정적 메소드 안에서 사용하는 필드는 반드시 정적 필드 여야 하므로 static을 붙인다.
	//때문에 얘를 위로 뺸다. 
	private static Connection con;
	//static:정적멤버,여기서는 정적메소드를 나타낸다
	//여기에 static을 달면 dao에서 객체를 따로 만들지 않아도 겟 컨넥션을 호출 가능하다.
	static Connection getConnection() {
		//지역변수: 메소드가 호출시 생성 된다. 
		//Connection con=null;		떄문에 얘를 지역변수로 하면 의미가 없다. 
		//url, user, password	컨넥션 객체생성시 필요한 것들
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String user="hr";
		String password = "hr";
		
		//오라클 드라이버 로딩 
		//오라클 체크예외처리
		try {
			if (con== null || con.isClosed()) {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				System.out.println("로딩완료");
				//컨넥션 객체 생성
				//컨에 값 삽입
				con = DriverManager.getConnection(url, user, password);
				System.out.println("컨넥션 완료");
			}
		
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
