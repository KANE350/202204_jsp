package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import dto.Reply;

class JunitTestReply {
	private ReplyDAO replyDAO = new ReplyDAO();
	
	@Test
	void testInsert() {
		Reply reply = new Reply();
		reply.setBnum(306);
		reply.setContent("댓글2");
		reply.setRestep(1);
		reply.setRelevel(1);
		int cnt = replyDAO.insert(reply);
		System.out.println(cnt+"건 추가");
	}

	@Test
	void testUpdate() {
		Reply reply = new Reply();		
		reply.setRnum(2);				//정렬순서는 상관 x 필드상으로 가장 먼저 나오기 때문에 이렇게 정렬했다.
		reply.setContent("댓글1");
		int cnt = replyDAO.update(reply);
		System.out.println(cnt+"건 수정");
	}
	
	@Test
	void testDelete() {
		int cnt = replyDAO.delete(2);				//dao에서 rnum을 반환했기 때문에 여기서는 삭제하려는 숫자만 입력하면 된다. 
		System.out.println(cnt+"건 삭제");
	}
		
	@Test
	void testselectOne() {
		Reply reply = replyDAO.selectOne(3);				
		System.out.println(reply);
	}
	
	@Test
	void testselectList() {
		List<Reply> rlist = replyDAO.selectList(306);				
		System.out.println(rlist);
	}
	
	
	
	
	}


