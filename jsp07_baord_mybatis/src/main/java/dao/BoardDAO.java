package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.Board;

public class BoardDAO {	
	public int insert(Board board) {
//		try~with
		//try가 끝나면 sesssion을 자동으로 close 해준다
		try(SqlSession session = MBConn.getSession()){
			//매퍼의 namespase + id를 결합해서 BoardMapper.insert를 만들어야 한다.
			//보드 매퍼에 설정한 아이디와 BoardMapper.insert가 다르면 오류가 난다. 반드시 아이디와 .뒤의 글자가 일치해야 한다.
			return session.insert("BoardMapper.insert", board);	
		}
		
	}
	
	//한건수정
	public int update(Board board) {
		try(SqlSession session = MBConn.getSession()){
			return session.update("BoardMapper.update", board);
		}
	}
	
	//조회수
	public int update_readcnt(int bnum) {
		try(SqlSession session = MBConn.getSession()){
			return session.update("BoardMapper.update_readcnt", bnum);	//이렇게 작성하면 다시 매퍼로 돌아가서 해당 오라클 코드가 실행된다.
		}																//보드 서비스로 넘어가서 코드 작성	
	}	
	
	//한건삭제
	public int delete(int bnum) {
		try(SqlSession session = MBConn.getSession()){
			return session.delete("BoardMapper.delete", bnum);
		}
	}
	
	//한건조회	
	public Board selectOne(int bnum) {/* 보드에 담아서 리턴하겠다 */
		try(SqlSession session = MBConn.getSession()){
			return session.selectOne("BoardMapper.selectOne", bnum);
			/* 보드를 넘겨줄테니 메퍼에서는 세팅한걸 보내라 */
		}
	}
	
	//조건조회
	//두개 이상의 값을 mapper에 넘겨줄떄는 map을 이용한다. map(dto)이용. 지금은 dto가 없으니까 위랑 달리 이렇게 담았다.
	public List<Board> selectList(Map<String, Object> findmap){	 //문자열이기 때문에 String findvalue로 변경해야 하는데 String key, String value를 두개 이상의 값을 매퍼에 넘겨야 하기 때문에 Map에 싸서 보낸다.
		try(SqlSession session = MBConn.getSession()){
			return session.selectList("BoardMapper.selectList", findmap);//아규먼트 넘길게 없어서 안넣음
		}
	}
	
	//게시물수
	//한건만 조회에서 이에 해당하는 페이지까지만 출력
	public int select_totalcnt(Map<String, Object> findmap){	 
		try(SqlSession session = MBConn.getSession()){
			return session.selectOne("BoardMapper.select_totalcnt", findmap);//아규먼트 넘길게 없어서 안넣음
		}
	}
	
}