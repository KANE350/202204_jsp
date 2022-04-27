package s02_member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//db를 접속하는 클래스 
public class MemberDAO {
	
	
	
	
	//인서트 메소드 만들기
	//반환형 메소드명(매개변수){}
	//member컨트롤러에서 만들어진 멤버객체를 저장하고 싶기 떄문에 멤버를 매개변수로 담는다. 	
	//db에 insert를 실행해주는 메소드
	int insert(Member member) {
		int cnt=0;//적용건수
		//db접속
		Connection con = DBConn.getConnection();
		String sql="insert into member (userid, passwd, name, email)\r\n"
				+ "values(?, ?, ?, ?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getUserid());
			pstmt.setString(2, member.getPasswd());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getEmail());
			cnt = pstmt.executeUpdate();
			//인서트할때는 업데이트, 조회할때는 쿼리 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	//조회리스트
	List<Member> selectList(){
		List<Member> mlist= new ArrayList<>();
		Connection con = DBConn.getConnection();
		String sql="select*from member";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String userid = rs.getString("userid");
				String passwd = rs.getString("passwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Member member= new Member(userid, passwd, name, email);
				mlist.add(member);
				
				
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return mlist;
	}
	
	//한건조회
	Member selectOne(String userid) {
		Member member=null;
		Connection con = DBConn.getConnection();
		String sql ="SELECT*FROM member\r\n"
				+ "where userid = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				member = new Member();
				member.setUserid(userid);
				member.setPasswd(rs.getString("passwd"));
				member.setName(rs.getString("name"));
				member.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return member;
	}
	
	//수정
	int update(Member member) {
		int cnt=0;//적용건수
		
		Connection con = DBConn.getConnection();
		String sql=" update member\r\n"
				+ "set passwd = ?,\r\n"
				+ "name = ?,\r\n"
				+ "email = ?\r\n"
				+ "where userid =?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getPasswd());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getUserid());
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return cnt;
	}
	
	//삭제 메소드
	int delete(String userid) {
		int cnt = 0;
		Connection con = DBConn.getConnection();
		String sql = "delete from member\r\n"
				+ "where userid = ?";
		PreparedStatement pstmt;
		try {
			pstmt =con.prepareStatement(sql);
			pstmt.setString(1, userid);
			cnt=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block			
			e.printStackTrace();

		}
		return cnt; 
		
		
	}
	
		
}

/* 반환하고 싶은 형을 골라서 생성 후 처리하고 리턴해놓고 진행한다. 조회만 하기 때문에 인서트업데이트가 굳이 필요하지 않아 
 * 배열 클래스로만 생성한다. */
 