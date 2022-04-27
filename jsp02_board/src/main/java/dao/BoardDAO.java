package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Board;

public class BoardDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private String sql;
	
	//추가
	public int insert(Board board) {
		//다른 패키지(boardcontroller)에서 접근하기 위해서는 반드시 public을 붙여야 한다.
		int cnt=0;
		con = DBConn.getConnection();
		sql = "INSERT INTO board (seq, writer, subject, content)\r\n"
				+ "VALUES(b_seq.nextval, ?, ?, ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getWriter());
			pstmt.setString(2, board.getSubject());
			pstmt.setString(3, board.getContent());
			cnt=pstmt.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}catch (Exception e) {
			System.out.println("예외발생");
			e.printStackTrace();
		}
		return cnt;
	}
	
	
	//수정
		public int update(Board board) {
			int cnt=0;
			con = DBConn.getConnection();
			sql = "UPDATE board\r\n"
				+ "SET writer = ?,\r\n"
					+ "subject = ?,\r\n"
					+ "content = ?,\r\n"
					+ "modidate = sysdate\r\n"
				+ "WHERE seq=?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, board.getWriter());
				pstmt.setString(2, board.getSubject());
				pstmt.setString(3, board.getContent());
				pstmt.setInt(4, board.getSeq());
				cnt = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}catch (Exception e) {
				System.out.println("예외발생");
				e.printStackTrace();
			}
			return cnt;
		}
		
	//삭제
	public int delete(int seq) {
		int cnt = 0;
		con = DBConn.getConnection();
		String sql="DELETE FROM board\r\n"
				+ "WHERE seq=?";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, seq);
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQLExeption이 발생");
			e.printStackTrace();
		}catch (Exception e) {
			System.out.println("예외발생");
			e.printStackTrace();
		}
		return cnt;
	}

	//리스트
	public List<Board> selectList(String findkey, String findvalue){
		List<Board> blist = new ArrayList<>();
		con = DBConn.getConnection();
		//속도(메모리) 향상을 위해서 StringBuffer사용
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM board ");
		sb.append("WHERE " + findkey +" like '%' || ? || '%' ");
		sb.append("ORDER BY " + findkey +" , seq DESC");


		//띄어쓰기 주의해서 작성
		try {
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, findvalue);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Board board = new Board();
				board.setSeq(rs.getInt("seq"));
				board.setWriter(rs.getString("writer"));
				board.setSubject(rs.getString("subject"));
				board.setContent(rs.getString("content"));
				board.setRegidate(rs.getTimestamp("regidate"));
				board.setModidate(rs.getTimestamp("modidate"));
				blist.add(board);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			System.out.println("예외발생");
			e.printStackTrace();
		}
		//읽어올때는 겟 타임스탬프 사용
		
		return blist;
	}
	
	//한건조회 
	public Board selectOne(int seq) {
		Board board = null;
		sql="SELECT * FROM board\r\n"
				+ "WHERE seq = ?";
		try {
			con = DBConn.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, seq);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				board = new Board();
				board.setSeq(seq);
				board.setWriter(rs.getString("writer"));
				board.setSubject(rs.getString("subject"));
				board.setContent(rs.getString("content"));
				board.setRegidate(rs.getTimestamp("regidate"));
				board.setModidate(rs.getTimestamp("modidate"));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}catch (Exception e) {
			System.out.println("예외발생");
			e.printStackTrace();
		}
		return board;
	}
	
}
