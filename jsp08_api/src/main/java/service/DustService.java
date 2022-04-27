package service;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import dao.DustDAO;

import java.io.BufferedReader;
import java.io.IOException;

public class DustService {
	private DustDAO dustDAO = new DustDAO();
	
	public int dustPasing(String year) {
		//맵의 리스트를 생성
		//List list new ArrayList();	기존에는 이런 식으로 만들어줬지만 제너릭 타입(형을 미리 지정)을 사용하여 아래와 같이 만들었다(다형성의 문제점 해결가능)		
		List<Map<String, String>> list = new ArrayList<Map<String,String>>(); 
		//데이터포털:한국환경공단_에어코리아_미세먼지 경보 발령 현황
		try {
			String serviceKey = "uMngqDWghlCUM7FKSQhTMPHD6Pw05QrJz2IoKje4tLozlhVZNfN1V6d78mbWCXI8Pixkrmhtd8vWQiMEwntxEA%3D%3D";
	        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552584/UlfptcaAlarmInqireSvc/getUlfptcaAlarmInfo"); /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "="+serviceKey); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("returnType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*xml 또는 json*/
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); /*한 페이지 결과 수*/
	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
	        urlBuilder.append("&" + URLEncoder.encode("year","UTF-8") + "=" + URLEncoder.encode(year, "UTF-8")); /*측정 연도*///변수를 넣을때는 반드시 따옴표를 빼고 넣어야 한다.
	        urlBuilder.append("&" + URLEncoder.encode("itemCode","UTF-8") + "=" + URLEncoder.encode("PM10", "UTF-8")); /*미세먼지 항목 구분(PM10, PM25), PM10/PM25 모두 조회할 경우 파라미터 생략*/
	        System.out.println(urlBuilder.toString()); //url출력
	        
	        URL url = new URL(urlBuilder.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	        System.out.println("Response code: " + conn.getResponseCode());
	        BufferedReader rd;
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = rd.readLine()) != null) {
	            sb.append(line);
	        }
	        rd.close();
	        conn.disconnect();
	        System.out.println(sb.toString());
			
	        //json 파싱 
	        JSONParser parser = new JSONParser();
	        JSONObject object = (JSONObject)parser.parse(sb.toString());
	        object = (JSONObject) object.get("response");	//postman을 통해 봤을떄 response가 가장 앞에 있다. 이 키를 가져온다.
	        object = (JSONObject) object.get("body");
	        JSONArray array = (JSONArray) object.get("items");
	        System.out.println(array);
	       for(int i=0; i< array.size(); i++) {
	    	   object = (JSONObject) array.get(i);
	    	   System.out.println("--------------------------------------");
	    	   Set<String> kset = object.keySet();
	    	   //맵 만들기
	    	   Map<String, String> map = new HashMap<>();
	    	   for(String key:kset) {
	    		   //키와 값
	    		   System.out.println(key+":" + object.get(key));
	    		   map.put(key, (String)object.get(key));			//키와 값을 삽입한다.
	    	   	}
	    	   System.out.println(map);
	    	   	list.add(map);	//리스트에 맵 넣어서 보내기	
	    	 
	       }
	       //맵으로 어레이를 돌린다는 것은 즉, 리스트를 생성한다는 것이다.
		} catch (Exception e) {
			
		}
	  	System.out.println("리스트:" + list);	//mapper로 간다
		//db에 저장(저장후 조회)
	  	int cnt = dustDAO.insert(list);	//dao로 넘어간다
	  	System.out.println(cnt+"건 저장");//dao에서 넘어왔다.
	  	
	  	return cnt;
	}

	public List<Map<String, String>> selectList(String districtName) {
		return dustDAO.selectList(districtName);
	}
}
