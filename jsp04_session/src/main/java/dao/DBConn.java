package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//db 컨넥션을 반환
public class DBConn {
	//con객체를 싱글톤패턴으로 : 인스턴스를 하나만 생성하고 재사용
	
	//정적메소드안에서 사용하는 필드는 반드시 정적필드여야 한다
	private static Connection con;
	//static : 정적메소드
	static Connection getConnection() {
		//지역변수:메소드 호출시 생성
		//Connection con=null;  
		//url, user, password
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user ="hr";
		String password ="hr";
		
		try {
			if (con == null || con.isClosed()) {
				//오라클 드라이버 로딩
				Class.forName("oracle.jdbc.driver.OracleDriver");
				//컨넥션 객체 생성
				con = DriverManager.getConnection(url, user, password);
				System.out.println("컨넥션 완료");	
			}
		
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("접속 실패");
			e.printStackTrace();
		}
		
		return con;
	}
}
