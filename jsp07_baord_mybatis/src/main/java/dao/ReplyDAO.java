package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dto.Reply;

public class ReplyDAO {			
	public int insert(Reply reply) {	//mapper의 insert를 호출하는 메소드 생성
		try(SqlSession session = MBConn.getSession()){
			return session.insert("ReplyMapper.insert", reply);
		}
	}
	
	public int update(Reply reply) {	
		try(SqlSession session = MBConn.getSession()){
			return session.update("ReplyMapper.update", reply);
		}
	}
	
	
	/* 글수정 재수정 */
	public int update_restep(Reply reply) {
		try(SqlSession session = MBConn.getSession()){
			return session.update("ReplyMapper.update_restep",reply);
		}
	}
	
	
	public int delete(int rnum) {		//rnum만 보고 지우면 되기 때문에 int형으로 넘겼다.
		try(SqlSession session = MBConn.getSession()){
			return session.delete("ReplyMapper.delete", rnum);	//때문에 리턴값 또한 rnum이 된다.
		}
	}
	
	//게시물 한 건의 모든 댓글 삭제
	public int delete_bnum(int bnum) {		//rnum만 보고 지우면 되기 때문에 int형으로 넘겼다.
		try(SqlSession session = MBConn.getSession()){
			return session.delete("ReplyMapper.delete_bnum", bnum);	//때문에 리턴값 또한 rnum이 된다.
		}
	}
	
	public Reply selectOne(int rnum) {		
		try(SqlSession session = MBConn.getSession()){
			return session.selectOne("ReplyMapper.selectOne", rnum);	
		}
	}

	public List<Reply> selectList(int bnum) {		//반환형을 반드시 리플라이의 리스트로 해주어야 한다, 리스트형이기 때문.	
		try(SqlSession session = MBConn.getSession()){
			return session.selectList("ReplyMapper.selectList", bnum);	
		}
	}

}
