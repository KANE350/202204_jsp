package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.Member;

public class MemberDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private String sql;
	//한건조회
	public Member selectOne(String userid) {
		Member member=null;
		con = DBConn.getConnection();
		sql = "select * from member\r\n"
				+ "where userid = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				member = new Member(
						rs.getString("userid"),
						rs.getString("passwd"),
						rs.getString("name"),
						rs.getString("email")
				);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return member;
	}
}
