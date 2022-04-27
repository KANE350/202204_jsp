package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import dto.Covid;

class JunitTest {
	CovidDAO covidDAO = new CovidDAO();
	
	@Test
	void test() {	
		//맵을 만드는데 맵에는 최종적으로 startDt와 endDt를 가지고 메퍼에 보내고 싶다.
		//맵 생성하기
		
		//startDt, endDt넣기
		Map<String, String> map = new HashMap<>();				//dao의 맵과 똑같은 형을 만들어서 넘겨주면 된다.
		map.put("startDt", "20220410");		//맵에 있는 키를넣어야 한다.
		map.put("endDt", "20220419");				
		
		
		 List<Covid> clist = covidDAO.selectList(map);
		 
		System.out.println(clist);
}

}
