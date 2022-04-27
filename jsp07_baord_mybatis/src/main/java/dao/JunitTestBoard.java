package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import dto.Board;

public class JunitTestBoard {
	private BoardDAO bdao = new BoardDAO();

	@Test
	void testSqlSession() {
		MBConn.getSession();
	}
	
	@Test
	void testInsert() {
		MBConn.getSession();
		Board board = new Board();
		board.setUserid("jaba");
		board.setSubject("제목");
		board.setContent("내용1");
		board.setIp("192.168.0.100");
		int cnt = bdao.insert(board);
		System.out.println(cnt + "건 추가");
	}

	
	@Test
	void testUpdate() {
		Board board = new Board();
		board.setBnum(2);
		board.setUserid("park");
		board.setSubject("제목수정2");
		board.setContent("내용수정1");
		board.setIp("192.168.0.200");
		int cnt = bdao.update(board);
		System.out.println(cnt + "건 수정");
	}
	
	@Test
	void testDelete() {
		int cnt = bdao.delete(3);
		System.out.println(cnt + "건 삭제");
	}
	
	@Test 
	void testselectOne() {
		Board board = bdao.selectOne(4);
		System.out.println(board);
	}

	@Test 
	void testselectList() {
		//넘겨줄게 아무것도 없기 때문에 실행하고 리턴받아온 값을 뿌리기만 하면 된다.
		Map<String, Object> findmap = new HashMap<>();
		findmap.put("findkey", "subject");
		findmap.put("findvalue", "2");
		
		List<Board> blist = bdao.selectList(findmap);
		System.out.println(blist);
	}
	
}
