package service;

import java.util.List;

import dao.ReplyDAO;
import dto.Reply;

public class ReplyService {
	private ReplyDAO replyDAO = new ReplyDAO();
	
	public void insert(Reply reply) {	//컨트롤러에서 보낸 리플라이 가져오기
	
		//댓글의 순서(restep) 최신순으로 나타낸다 +1
		reply.setRestep(reply.getRestep()+1);		//리플라이를 읽어서 +1을 세팅한다 
		//댓글의 레벨(relevel)들여쓰기 정도 +1
		reply.setRelevel(reply.getRelevel()+1);	
		//기존 댓글의 순서를 수정(이후에 인서트를 했을때 댓글 순서가 다시 정렬된다)
		replyDAO.update_restep(reply);
		
		
		int cnt = replyDAO.insert(reply);
		System.out.println("댓글"+cnt+"건 추가");
		
	
	}



	public List<Reply> selectList(int bnum) {
		return replyDAO.selectList(bnum);
	}


	//댓글 삭제 메소드
	public void delete(int rnum) {
		int cnt = replyDAO.delete(rnum);
		System.out.println(cnt+"건 댓글 삭제");
	}


	//댓글 한건조회 메소드
	public Reply selectOne(int rnum) {
		return replyDAO.selectOne(rnum);
	}


	//댓글 수정 메소드
	public void update(Reply reply) {
		int cnt = replyDAO.update(reply);
		System.out.println(cnt+ "건 댓글 수정");
		
	}







	
	//모디폼 이동
	
}