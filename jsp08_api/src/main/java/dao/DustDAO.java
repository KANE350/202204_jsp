package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public class DustDAO {
	int cnt = 0;
	public int insert(List<Map<String, String>> list) {
		try(SqlSession session = MBConn.getSession()){
			for(Map<String, String> map:list) {
				try {
					cnt += session.insert("DustMapper.insert", map);			//중복 데이터가 인서트될시 중복되는 부분만 쳐내고 업데이트 하도록 진행한다.
				} catch (Exception e) {											//covid에서 업데이트가 가능했던건 modifydata가 있었기 때문이다.
					System.out.println(map.get("sm") + ":에러");
				}
				
			}
			session.commit();	//반복문이 끝나고 마지막에 커밋
		}//close
		
		return cnt;			//작성 후 서비스로 가서 작성
	}
	public List<Map<String, String>> selectList(String districtName) {
		try(SqlSession session = MBConn.getSession()){
			return session.selectList("DustMapper.selectList", districtName);
		}
		
	}
	
}
