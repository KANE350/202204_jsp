package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dto.Board;
import dto.Boardfile;

public class BoardfileDAO {
	public int insert(Boardfile boardfile) {
		try(SqlSession session = MBConn.getSession()){
			return session.insert("BoardfileMapper.insert", boardfile);
		}
	}
	
	public int update(Boardfile boardfile) {
		try(SqlSession session = MBConn.getSession()){
			return session.update("BoardfileMapper.update", boardfile);
		}
	}
	
	
	public int delete(int bfnum) {
		try(SqlSession session = MBConn.getSession()){
			return session.delete("BoardfileMapper.delete", bfnum);
		}
	}

	//bnum에 해당하는 boardfile을 삭제한다
	public int delete_bnum(int bnum) {		//메소드가 아니라 아이디 delete_bnum을 호출하겠다.
		try(SqlSession session = MBConn.getSession()){
			return session.delete("BoardfileMapper.delete_bnum", bnum);
		}
	}
	
	public Boardfile selectOne(int bfnum) {
		try(SqlSession session = MBConn.getSession()){
			return session.selectOne("BoardfileMapper.selectOne", bfnum);
		}
	}
	
	//파일의 리스트는 게시물의 파일들을 조회하는게 업무에 적용했을시 맞다.
	public List<Boardfile> selectList(int bnum) {
		try(SqlSession session = MBConn.getSession()){
			return session.selectList("BoardfileMapper.selectList", bnum);
		}
	}
}

