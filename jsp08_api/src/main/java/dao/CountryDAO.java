package dao;

import java.util.List;
import java.util.Map;			//dto나 Map에나 같은 곳에 담겨있기 때문에 경로명을 작성할때 맵으로 대체해도 된다.

import org.apache.ibatis.session.SqlSession;

import dto.Country;

public class CountryDAO {

	public List<Map<String, String>> selectList_iso() {
		try(SqlSession session = MBConn.getSession()){//trywith를 하면 close를 안해도 되기 떄문에 편하다. ㅎㅎㅎ
			return session.selectList("CountryMapper.selectList_iso");		//리턴값에 매퍼이름 작성, 아이디 이름을 selectList_iso로 설정
		}
		
	}

	public int insert(List<Map<String, String>> clist) {
		//파싱한 맵의 리스트를 저장하기 위한 목적(때문에 mapper의 insert호출)
		int cnt =0;				//cnt가 선언이 되어야 cnt반환이 가능해진다
		try(SqlSession session = MBConn.getSession()){		//try~with문 
			for(Map<String, String> map:clist) {
				//한건조회
				Country country = session.selectOne("CountryMapper.selectOne", map.get("sfty_notice_id"));
				//반환값이 컨트리이기 때문에(매퍼가 dto와 연결되어 있다)										//맵에 있기 때문에 맵에 있는 매퍼의 해당 키를 가져온다.
				//만약 데이터가 존재한다면 update를 하겠다 
				if(country!=null)
					cnt += session.update("CountryMapper.update", map);		//map이 섹션에서 넘기면 파라미터가 된다.
				else
				//존재하지 않는다면 insert를 하겠다 
				cnt += session.insert("CountryMapper.insert", map);
			}
			//반복문 종료 후 commit:  mybatisConfig의 transactionManager JDBC로 설정했기 때문에 직접 커밋을 해줘야 한다
			session.commit(); 	
			return cnt; //한건이기 떄문에 리스트가 아니라 clist의 반복문을 써서 이 안의 map을 하나씩 넘겨줘야 한다.
		} 
				
	}
	
	public List<Country> selectList(String iso){	//서비스에 가서 메소드 작성
		try(SqlSession session = MBConn.getSession()){
			return session.selectList("CountryMapper.selectList", iso);
		}
	}

	//한건조회(디테일로 가는 길)
	public Country selectOne(String sfty_notice_id) {
		try(SqlSession session = MBConn.getSession()){
			return session.selectOne("CountryMapper.selectOne", sfty_notice_id);
		}
		
	}
	
}
		
