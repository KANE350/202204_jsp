package service;

import java.util.HashMap;
import java.util.Map;

import dao.MemberDAO;
import dto.Member;

//컨트롤러와 dao를 연결
//비지니스 로직 처리
public class LoginService {
	//dao객체 생성
	private MemberDAO mdao = new MemberDAO();
	
	//로그인 체크
	public Map<String, Object> loginCheck(String userid, String passwd) {
		//리턴값: 한개만 리턴(map생성)
		//code : 0(성공), 1(회원미존재), 2(비밀번호불일치)
		//msg : 성공, 회원미존재, 비밀번호불일치
		Map<String, Object> rmap  =  new HashMap<>();
		int code;
		String msg;
		//1)dao호출 : userid를 기준으로 한건조회(selectOne)
		Member member = mdao.selectOne(userid);
		System.out.println("passwd :" + passwd);
		System.out.println(member);
		//2)만약에 리턴값이 null이면 회원이 존재하지 않는다
		if (member == null) {
			code = 1; 
			msg = "회원 미존재";
		}else if (!passwd.equals(member.getPasswd())) {
			//3)passwd가 일치하지 않는다면
			code = 2; 
			msg = "비밀번호 불일치";
		}else {
			//  패스워드가 일치하면 로그인 성공
			code = 0;
			msg = "로그인 성공";
		}
		rmap.put("code", code);
		rmap.put("msg", msg);
		
		return rmap;
	}
	
}
