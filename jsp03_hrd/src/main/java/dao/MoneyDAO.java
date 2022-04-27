package dao;
//머니쪽을 접속하는 인서트 업데이트 딜리트 등등(삭제폼은 없지만 한번 넣어보자)

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dto.MoneyList;

public class MoneyDAO {
	private Connection con;
	private PreparedStatement pstmt;
	String sql;
	//매출조회
	public List<Map<String, Object>> selectList(){
		List<Map<String, Object>> mlist = new ArrayList<>();
		//반환하려는 형이 바뀌었기 때문에 밑의 맵의 리스트를 가져다 넣는다
		con = DBConn.getConnection();
		String sql = "select mn.custno, mb.custname, \r\n"
				+ "    decode(mb.grade,'A','VIP','B', '일반', 'C', '직원') grade,\r\n"
				+ "    sum(mn.price) price\r\n"
				+ "from money_tbl_02 mn join member_tbl_02 mb on(mn.custno = mb.custno)\r\n"
				+ "group by mn.custno, mb.custname, mb.grade\r\n"
				+ "order by price desc";
		
		try {
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				int custno = rs.getInt("custno");
				String custname = rs.getString("custname");
				String grade = rs.getString("grade");
				int price = rs.getInt("price");
				//map생성
				Map<String, Object> map = new HashMap<>();
				//money list dto를 생성하는 대신 맵을 만들었다
				map.put("custno", custno);
				map.put("custname", custname);
				map.put("grade", grade);
				map.put("price", price);
				mlist.add(map); //리스트에 추가해야 하기 때문에 
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mlist;
	}
}