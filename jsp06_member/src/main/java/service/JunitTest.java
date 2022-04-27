package service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class JunitTest {
	private Memberservice memberService = new Memberservice();
	
	@Test
	void testSha256() {
		String scpw = memberService.sha256("1111", "111");
		System.out.println(scpw);
	}
	
	@Test
	void testSalt() {
		String salt = memberService.saltmake();
		System.out.println(salt);
		System.out.println(salt.length());
	}

}
