package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.Covid;

public class CovidDAO {
	public int insert(List<Map<String, String>> covidList) {	//서비스에 있는 맵의 리스트를 매개변수로 받는다(코비드 디티오 대신)
		//여러건의 코비드를 반복문을 이용해서 넣어야 한다.
		//세션을 맺어서 디비컨넥션을 해서 넣는다
		
		//리스트의 맵을 넘기는 거기 떄문에 반복문으로 사용, 굳이 맵이아니라 코비드에 넣어도 마찬가지이다. 리스트를 넘기려면
		//반복문을 돌려야 여러건을 디비에 저장할 수 있다. 
		int cnt=0;	//저장건수
		SqlSession session = MBConn.getSession();		//중괄호 밖에서 session이 일어나야 하기 때문에 밖으로 뺐다	//세션맺기	

			try {
									//(어떤애를 호출할건지, 어떤애를 저장할건지(covid한건한건을 풀어서 담아준다)
				for(Map<String, String> covid:covidList) {			//중복됐을때 오류가 나는것을 방지하기 위해 trycath처리를 했다. try안에 넣으면 한가지에서 오류나면 나머지를
											//전부 백하기 때문에 for를 나누어 담았다. 
				
				try {
				cnt += session.insert("CovidMapper.insert", covid);			//cnt += 을 하면 저장건수를 cnt에 담아준다.		
				} catch (Exception e) {
					System.out.println(covid.get("seq")+"예외발생");	//몇번째에서 예외가 발생하는지 확인 가능하게 작성
					//수정
					cnt += session.update("CovidMapper.update", covid);	
					
					e.printStackTrace();//무슨 예외인지
				}
					}	
						}finally {	//예외가 발생하든 안하든 실행할 문장
							//마이바티스 컨피그 파일의 JDBC
							//여러건 insert후 commit을 한번만
							session.commit();
							session.close();//작업 처리 뒤에 클로즈를 잘 하는지	, 예외가 발생이 되든 안되든 무조건 close가 일어나야 한다.
						}
							return cnt;		//마지막에 리턴값을 잘 반영하는지
						}
	
				//코로나 확진자 리스트
				public List<Covid> selectList(Map<String, String> map){		//startDt, endDt두가지를 받아야 하기 때문에 맵에 받아서 서비스에서 작업
					try(SqlSession session = MBConn.getSession()){
						return session.selectList("CovidMapper.selectList",map);	//호출할 매퍼, 반환형 선택.	이를 서비스에서 호출한다.  
					}
				}
					}
