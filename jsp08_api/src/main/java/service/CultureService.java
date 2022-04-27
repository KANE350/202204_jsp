package service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class CultureService{
	//주소요청하고 파싱하기
public Map<String, Object> cultureParsing(String name) {	//name이라는 변수에 들어있는 값이 노원정보 도서관. 검색값은 언제든지 바꿀 수 있다.
	Map<String, Object> map = new HashMap<>();	//try안에서 선언하면 광범위하게 사용되지 못하기 때문에 밖에서 선언.
	try {//체크예외를 해야 하기 때문에 try catch처리를 했다. 모든 서비스가 동일한 예외처리.
		String serviceKey="46637968524a746837324241436853";
	    StringBuilder sb = new StringBuilder(); 
	    sb.append("http://openAPI.seoul.go.kr:8088/");					//uri를 만드는 것이기 때문에 샘플 url 삽입
	    sb.append(serviceKey);
	    sb.append("/json/");
	    sb.append("SearchCulturalFacilitiesNameService/1/5/");
	    sb.append(name);					//매개변수를 집어넣는다. ""를 붙이면 문자열이기 떄문에 넣지 말아야 한다.
	    System.out.println(sb.toString());
	    
	    //conn요청 + 데이터 받기
	    URL url = new URL(sb.toString());			//위의 sb
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setRequestMethod("GET");
	    conn.setRequestProperty("Content-type", "application/json");
	    //System.out.println("Response code: " + conn.getResponseCode());
	    BufferedReader rd;
	    if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	        rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    } else {
	        rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	    }
	    sb = new StringBuilder();		//값 새로 삽입
	    String line;
	    while ((line = rd.readLine()) != null) {
	        sb.append(line);
	    }
	    rd.close();
	    conn.disconnect();
	    //System.out.println(sb.toString());		//투스트링은 단순히 문자열 데이터(제이슨 형태의 문자열)이기 때문에 오브젝트(제이슨의 객체형태)로 만들어야 빼낼 수 있다.
	   //-----------------------------------------------------------------------------------------------------------------------------
	   //json 파싱 : json-simple-1.1.1.jar
	    JSONParser parser = new JSONParser();
	    JSONObject object =  (JSONObject)parser.parse(sb.toString()); 	//제이슨이 오브젝트로 쌓여있기 때문에 다운캐스팅을 해서 투스트링을 오브젝트에 담아준다
	    //System.out.println(object);			//오브젝트 안에 오브젝트가 싸여있음을 알 수 있다.
	    					//postman을 통해 봤을때, row라는 데이터안에 주소정보가 있다. 이걸 가져와야 한다.
	    object = (JSONObject)object.get("SearchCulturalFacilitiesNameService");					//syso를 통해 잘 가져오는지 확인한뒤 저장한다.
	    JSONArray array = (JSONArray) object.get("row");			//row를 출력했을때 배열에 싸인것을 볼 수 있다. 때문에 어레이제이슨에 저장해준다. 
	    object = (JSONObject) array.get(0);						//배열이긴 하나 하나밖에 없기 때문에 반복문을 사용하지 않고 0번 인덱스로 가져왔다.
	    //System.out.println(object);
	    //object를 map에 넣기
	    													//값에 숫자가 하나 들어있어서 오브젝트로 했다.
	    map.put("FAC_CODE", object.get("FAC_CODE"));
	    map.put("SUBJCODE", object.get("SUBJCODE"));
	    map.put("FAC_NAME", object.get("FAC_NAME"));
	    map.put("CODENAME", object.get("CODENAME"));
	    map.put("ADDR", object.get("ADDR"));
	    
	    //System.out.println("map:" + map);
	    
	    
	} catch (Exception e) {
		e.printStackTrace();			//예외가 생겼을때 사유 출력
	}
		
	return map;
}
	//주소를 이용하여 경도,위도 알아내기 	네이버에서 제공해주는 api가져옴
	public Map<String, Double> geocoding(String addr) { 
		Map<String, Double> map = new HashMap<>();	//try안에서 선언하면 광범위하게 사용되지 못하기 때문에 밖에서 선언.
		try {//체크예외를 해야 하기 때문에 try catch처리를 했다. 모든 서비스가 동일한 예외처리.
		    StringBuilder sb = new StringBuilder(); 
		    sb.append("https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode");					//uri를 만드는 것이기 때문에 샘플 url 삽입
		    sb.append("?query=" + URLEncoder.encode(addr, "utf-8"));			//어디 주소를 조회할 것인지
		    System.out.println(sb.toString());
		    
		    //conn요청 + 데이터 받기
		    URL url = new URL(sb.toString());			//위의 sb
		    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		    conn.setRequestMethod("GET");
		    conn.setRequestProperty("Content-type", "application/json");
		    conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID", "xmyrulwcoh");	//헤더의 키(홈페이지의 요청 아이디, 앱 등록 시 발급받은 Client ID)
		    conn.setRequestProperty("X-NCP-APIGW-API-KEY", "hHx7HGVVkoIcWmaUUSvhIe7CKV55VSdM2ZpsjK1O");	//헤더의 키(홈페이지의 요청 시크릿 아이디, 앱 등록 시 발급 받은 Client Secre) 
		    
		    System.out.println("Response code: " + conn.getResponseCode());
		    BufferedReader rd;
		    if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
		        rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		    } else {
		        rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		    }
		    sb = new StringBuilder();		//값 새로 삽입
		    String line;
		    while ((line = rd.readLine()) != null) {
		        sb.append(line);
		    }
		    rd.close();
		    conn.disconnect();
		    System.out.println(sb.toString());		//응답받은 문자열 데이터
		   //-----------------------------------------------------------------------------------------------------------------------------
		   //json 파싱 : json-simple-1.1.1.jar
		    JSONParser parser = new JSONParser();								//simple parser를 임포트했다. 메소드를 사용하기 위해 객체생성
            JSONObject object =  (JSONObject)parser.parse(sb.toString());		//parsing하기 위해 다운캐스팅을 해서 문자열을 제이슨의 오브젝트(객체)로 변경		//오브젝트이기 떄문에 제이슨의 오브젝트로 반환한 것이다.
            
            //0번인덱스가 해당 값이 없으면 에러난다. 이 에러를 극복하기 위해 파싱을 고쳐보자.
 		   	//address를 읽기 전에 만약 totalCount 0이라면 null을 리턴하도록
            //메타를 먼저 읽고 그 안의 토탈카운트를 읽어서 0이면 리턴하도록 
              JSONObject metaobj = (JSONObject)object.get("meta");	//오브젝트에 저장하게 되어버리면 오브젝트를 가지고 어드레스를 파싱하지 못하기 때문에 널포인트익셉션 오류가 난다. 따로 저장해서 리턴해야 한다.
              long totalCount = (long)metaobj.get("totalCount");
              if(totalCount==0){
            	  return null;	//데이터가 존재하지 않을 시 메소드 종료. 맵은 참조변수기 때문에 널을 리턴해도 괜찮다. 
             } 
         	   JSONArray array = (JSONArray) object.get("addresses");
    		    System.out.println("여기"+array.get(0));		//하나밖에 없기 때문에 반복문을 사용하지 않고 0번인덱스를 출력했다.
    		    object = (JSONObject) array.get(0);			//0번인덱스가 오브젝트에 담겨있기 때문에 오브젝트에 담는다.
    		    
    		   //x값을 다운캐스팅 후 실수형으로 형변환
    		   //postman을 통해 봤을때 ""로 원래 스트링이었음을 알 수 있다. 때문에 다운캐스팅 후 double로 실수형변환을 해준다.
    		   
    		   double x = Double.parseDouble((String)object.get("x")) ;	
    		   double y = Double.parseDouble((String)object.get("y")) ;	
    		   map.put("x", x);			//경도
    		   map.put("y", y);			//위도
            
		} catch (Exception e) {
			e.printStackTrace();			//예외가 생겼을때 사유 출력
		}
			
		return map;
	}











}