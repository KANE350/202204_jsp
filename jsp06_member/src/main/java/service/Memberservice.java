package service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.tribes.util.Arrays;

import dao.MemberDAO;
import dto.Member;

public class Memberservice {
	private MemberDAO memberDAO = new MemberDAO();
	public int insert(Member member) {
		//비밀번호 암호화
		String salt = saltmake();//솔트를 구하기
		String secretpw = sha256(member.getPasswd(), salt);
		member.setPasswd(secretpw);
		member.setSalt(salt);

		return memberDAO.insert(member);
	}

	//sha256암호화 방식으로 평문을 암호문으로 변경해서 리턴해주는 메소드 생성(반한형은 문자열)
	String sha256(String passwd,String salt) {
		//StringBuffer : String 대신 사용, 속도(메모리) 효율적
		StringBuffer sb = new StringBuffer();
		try {
			//SHA-256:단방향 암호화 기법, 복호화 불가능하다. 256bit(16진수로 바꾸면 64자리의 문자열 반환하기 때문에 64byte필요) 
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(passwd.getBytes());//문자열이기 떄문에 스트링에 들어있는 겟바이트를 이용해 바이트 배열로 바꿔준다.
			md.update(salt.getBytes()); //솔트를 추가
			
			byte[] data = md.digest(); //암호화된 바이트 배열을 반환(32byte)
			System.out.println("암호화된 바이트 배열" + Arrays.toString(data));
			System.out.println("배열길이" + data.length);
			//10진수를 16진수로 변환해서 sb변수에 추가
			for(byte b:data) {
				sb.append(String.format("%02x", b));
				//b를 f로 하면 실수, b로 하면 정수로 변환, x로 하면 16진수 변환이다. %02x를 넣으면 공백은 0으로 처리되고 두자리로 나타난다.
			}	//sb.append를 붙이면 반복적으로 추가된다.
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		}
		
		return sb.toString();
		/* sb는 암호 저장 변수, tostrig을 사용하면 문자열로 반환해준다 */
	}
	
	//salt를 랜덤하게 만들기 
	String saltmake() {
		String salt = null;
		
		try {
			//난수 생성 알고리즘
			SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
			byte[] bytes = new byte[16];
			sr.nextBytes(bytes);	//빈 배열을 넣어주면 랜덤한 값을 bytes에 만든다.
			System.out.println(Arrays.toString(bytes));
			//정수byte를 String으로 변경
			//Base64인코딩 방식: 아스키 중에서 제어문자, 일부 특수문자를 제외한 64개의 안전한 문자만 사용하기 위해서 바꿨다.
			//16byte -> 24byte
			salt = new String(Base64.getEncoder().encode(bytes));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return salt;
	}

	public Map<String, Object> loginCheck(String userid, String passwd) {
		//로그인 체크 로직(반환값을 맵으로 설정)
		//리턴값: 한개만 리턴(map생성)
		//code : 0(성공), 1(회원미존재), 2(비밀번호불일치)
		//msg : 성공, 회원미존재, 비밀번호불일치
		Map<String, Object> rmap  =  new HashMap<>();
		int code;
		String msg;
		//1)dao호출 : userid를 기준으로 한건조회(selectOne)
		Member member = memberDAO.selectOne(userid);
		System.out.println("passwd :" + passwd);
		System.out.println(member);
		//2)만약에 리턴값이 null이면 회원이 존재하지 않는다
		if (member == null) {
			code = 1; 
			msg = "회원 미존재";
		}else {	//아닐때 수행할 문장, 이 안에 if문이 들어있는 상태
				//평문을 암호문으로 변경해서 비교해야 한다(복호화가 안되니까)
				String secretqw = sha256(passwd, member.getSalt());
				
			if (!secretqw.equals(member.getPasswd())) { /*암호화된 패스워드와 암호화문 (member.getPasswd()))이 같은지 비교 */
				//3)passwd가 일치하지 않는다면
				code = 2; 
				msg = "비밀번호 불일치";
			}else {
				//  패스워드가 일치하면 로그인 성공
				code = 0;
				msg = "로그인 성공";
			
			}
		}

		rmap.put("code", code);
		rmap.put("msg", msg);
		
		return rmap;
	}

	public Member selectOne(String userid) {
		//한건조회
		return memberDAO.selectOne(userid);
	}

	public List<Member> selectList(Map<String, String> findmap) {
		//전체조회
		return memberDAO.selectList(findmap);	//여기서는 전달해주는 중간자적 역할만 한다.
	}

	public Map<String, Object> update(Member member) {
		//수정
		//code : 0정상, 1 :비밀번호 불일치 
		
		Map<String, Object> rmap = new HashMap<>();
		
		// 입력한 비밀번호 암호화를 해서 기존 비밀번호와(db에 있는) 일치하는지 판단
		//기존 비밀번호 읽어오기
		Member dbmember = memberDAO.selectOne(member.getUserid());
		//평문과 salt를 이용해서 암호문 리턴
		String secretpw = sha256(member.getPasswd(), dbmember.getSalt());
		//얘가 데이터베이스에 있는 패스워드와 일치해야 한다.
		if (!secretpw.equals(dbmember.getPasswd())) {
			rmap.put("code", 1);
			rmap.put("msg", "비밀번호가 일치하지 않습니다");
			return rmap;	
		}
		member.setSalt(dbmember.getSalt());//솔트
		member.setPasswd(secretpw);//패스워드
		
		//업데이트 전에 변경 비밀번호가 있다면, 비밀번호 세팅(공백이라면 진행하지 않는다)
		if(!member.getNewpasswd().equals("")) {
			//비밀번호 암호화
			//기존 솔트 그대로 사용
			secretpw = sha256(member.getNewpasswd(), dbmember.getSalt()); 
			//sha256 안에 암호화시킨 평문을 넣어준다(member.getNewpasswd());
			member.setPasswd(secretpw);
			
		}
		
		memberDAO.update(member);
			
			rmap.put("code", 0);
			rmap.put("msg", "수정 완료");
		
			return rmap;
	}

	//삭제 메소드
	public int delete(String userid) {
		return memberDAO.delete(userid);
	}
	
	
	
	
	 
	
}
