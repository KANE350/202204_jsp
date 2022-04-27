package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Member;

public class MemberDAO {
	private Connection con;
	private PreparedStatement pstmt;
	String sql;
	//추가
	public int insert(Member member) {
		int cnt = 0;
		con = DBConn.getConnection();
		
		try {
			sql = "insert into member_tbl_02 \r\n"
					+ "values ((select nvl(max(custno)+1,1) from member_tbl_02), \r\n"
					+ "    ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getCustname());
			pstmt.setString(2, member.getPhone());
			pstmt.setString(3, member.getAddress());
			pstmt.setString(4, member.getJoindate());
			pstmt.setString(5, member.getGrade());
			pstmt.setString(6, member.getCity());
			cnt = pstmt.executeUpdate(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	//수정
	public int update(Member member) {
		int cnt = 0;
		con = DBConn.getConnection();
		
		try {
			sql = "update member_tbl_02\r\n"
					+ "set custname = ?,\r\n"
					+ "phone = ?,\r\n"
					+ "address = ?,\r\n"
					+ "grade =?,\r\n"
					+ "city = ?,\r\n"
					+ "joindate = ?\r\n"
					+ "where custno = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getCustname());
			pstmt.setString(2, member.getPhone());
			pstmt.setString(3, member.getAddress());
			pstmt.setString(4, member.getGrade());
			pstmt.setString(5, member.getCity());
			pstmt.setString(6, member.getJoindate());
			pstmt.setInt(7, member.getCustno());
			
			cnt = pstmt.executeUpdate(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	//삭제
	
	
	//한건조회
	public Member selectOne(int custno) {
		Member member = null;
		con = DBConn.getConnection();
		sql="select custno, custname, phone, address, grade, city,\r\n"
				+ "        to_char(joindate,'YYYY-MM-DD') joindate\r\n"
				+ "from member_tbl_02\r\n"
				+ "where custno = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, custno);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String custname = rs.getString("custname");
				String phone = rs.getString("phone");
				String address = rs.getString("address");
				String joindate = rs.getString("joindate");
				String grade = rs.getString("grade");
				String city = rs.getString("city");
				member = new Member(custno, custname, phone, address, joindate, grade, city);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return member;
	}
	
	//리스트
	public List<Member> selectList(){
		List<Member> mlist = new ArrayList<>();
		con = DBConn.getConnection();
		sql = "select custno, custname, phone, address, to_char(joindate,'YYYY-MM-DD') joindate,\r\n"
				+ "    decode(grade, 'A', 'VIP', 'B', '일반', 'C', '직원') grade, city\r\n"
				+ "from member_tbl_02\r\n"
				+ "order by custno";
		try {
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				int custno = rs.getInt("custno");
				String custname = rs.getString("custname");
				String phone = rs.getString("phone");
				String address = rs.getString("address");
				String joindate = rs.getString("joindate");
				String grade = rs.getString("grade");
				String city = rs.getString("city");
				Member member = new Member(custno, custname, phone, address, joindate, grade, city);
				mlist.add(member);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mlist;
	}
	
	
	
}
