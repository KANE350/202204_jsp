package dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public class EventDAO {
	
	public int insert(List<Map<String, Object>> elist) {
		int cnt = 0;
		SqlSession session = MBConn.getSession();
		
		try {
			
			for(Map<String, Object>event:elist) {
				
				try {
				cnt += session.insert("EventMapper.insert", event);
				} catch (Exception e) {
					System.out.println(event.get("title")+"예외발생");
					
					e.printStackTrace();
			}
				}
		
					}finally {//예외가 발생하든 안하든 실행할 문장
						session.commit();
						session.close();
					}
					return cnt;
					}
	
}
