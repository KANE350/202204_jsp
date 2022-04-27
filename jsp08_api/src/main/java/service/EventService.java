package service;

import java.io.InputStreamReader;
import java.lang.reflect.Array;
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

import com.sun.jdi.event.Event;

import dao.EventDAO;

import java.io.BufferedReader;
import java.io.IOException;

public class EventService {
	private EventDAO eventDAO = new EventDAO();
	public int eventParsing (String codename, String title) {
		 List<Map<String, Object>> elist = new ArrayList<>();
		try {
			
			StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /*URL*/
			urlBuilder.append("/" +  URLEncoder.encode("734154546f726b763635417965566b","UTF-8") ); /*인증키 (sample사용시에는 호출시 제한됩니다.)*/
			urlBuilder.append("/" +  URLEncoder.encode("json","UTF-8") ); /*요청파일타입 (xml,xmlf,xls,json) */
			urlBuilder.append("/" + URLEncoder.encode("culturalEventInfo","UTF-8")); /*서비스명 (대소문자 구분 필수입니다.)*/
			urlBuilder.append("/" + URLEncoder.encode("1","UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
			urlBuilder.append("/" + URLEncoder.encode("500","UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
			urlBuilder.append("/" + URLEncoder.encode(codename,"UTF-8")); /* 서비스별 추가 요청인자들*/
			urlBuilder.append("/" + URLEncoder.encode(title,"UTF-8")); /* 서비스별 추가 요청인자들*/
			// 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.
			System.out.println(urlBuilder.toString());
			
			// 서비스별 추가 요청 인자이며 자세한 내용은 각 서비스별 '요청인자'부분에 자세히 나와 있습니다.
			
			
			URL url = new URL(urlBuilder.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/
			BufferedReader rd;

			// 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
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

//---------------------------------------------------------------------------------------------------------------//conn+ 응답 데이터 문자열 생성부터 여기까지는 어떤 서비스든 동일하다. url하고 서비스키만 변경해주면 된다.            
		//json파싱
		JSONParser parser = new JSONParser();
		JSONObject object = (JSONObject)parser.parse(sb.toString());
		object = (JSONObject)object.get("culturalEventInfo");
		JSONArray array = (JSONArray) object.get("row");
		System.out.println(array);
		for(int i=0; i<array.size(); i++) {
			Map<String , Object> map = new HashMap<>();
			System.out.println(i+"------------------------------------------------");
			object = (JSONObject) array.get(i);
			System.out.println(object);	//배열을 벗긴 상태, 현 오브젝트이다.
			
			//object를 map에 넣기
			//key의 목록 가져와서 반복문
			Set<String> kset = object.keySet();
			for(String key:kset) {
				System.out.println(key + ":" + object.get(key));
				map.put(key, object.get(key));
			}	
			System.out.println("여기는"+map);
			elist.add(map);
		}
		
		} catch (Exception e) {
			e.printStackTrace();
			
			int cnt = eventDAO.insert(elist);
			System.out.println(cnt+"건 저장");
			return cnt;
		}
		return eventDAO.insert(elist);
		
	}
	
}
