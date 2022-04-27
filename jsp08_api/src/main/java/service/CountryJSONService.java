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

import org.apache.ibatis.reflection.ArrayUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import dao.CountryDAO;
import dto.Country;

import java.io.BufferedReader;
import java.io.IOException;

//파싱후 저장으로 변경
public class CountryJSONService {
	private CountryDAO countryDAO = new CountryDAO(); 
    public  int countryParsing(String iso) {	//iso매개변수를 빌더 어펜드에 삽입
    		//예외처리:체크예외(반드시 처리해야한다. 하지 않으면 컴파일에러가 난다.), 언체크예외(RntimeException(실행시체크)을 상속받게되어있다.)   		
    	
	    	//반환값
	    	List<Map<String, String>> clist = new ArrayList<>(); 	//뒤에도 형이 똑같지만 생략된 상태
    	try {
        	//데이터 포털:외교부_국가.지역별 최신 안전 소식(코로나 관련) 
        	String serviceKey="GGPsDIvQktTJYzXbX8JTtbvuogvkf5NyE42z8%2BEe5%2FENPFHmtycQTidoYgRjrPpOWW5nDIph6MOYDkhyjLdhtg%3D%3D";
            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1262000/CountryCovid19SafetyServiceNew/getCountrySafetyNewsListNew"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "="+serviceKey); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("returnType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*XML 또는 JSON*/
            urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("20", "UTF-8")); /*한 페이지 결과 수*/
            urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
            //urlBuilder.append("&" + URLEncoder.encode("cond[country_nm::EQ]","UTF-8") + "=" + URLEncoder.encode("가나", "UTF-8")); /*한글 국가명*/
            urlBuilder.append("&" + URLEncoder.encode("cond[country_iso_alp2::EQ]","UTF-8") + "=" + URLEncoder.encode(iso, "UTF-8")); /*ISO 2자리코드*/ //컨넥션 빌더를 만들어서
            																										//"iso"iso코드값을 조회하라, iso 변수에 있는 값을 꺼내라
            System.out.println(urlBuilder.toString());	//url빌더 제대로 만들어지는지 확인
            
            //conn+ 응답 데이터 문자열 생성
            URL url = new URL(urlBuilder.toString());
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
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();
            System.out.println(sb.toString());		//투스트링은 단순히 문자열 데이터(제이슨 형태의 문자열)이기 때문에 오브젝트(제이슨의 객체형태)로 만들어야 빼낼 수 있다.
//---------------------------------------------------------------------------------------------------------------//conn+ 응답 데이터 문자열 생성부터 여기까지는 어떤 서비스든 동일하다. url하고 서비스키만 변경해주면 된다.            
            //json파싱
            //파싱 라이브러리 :json-simple-1.1.1.jar	  	//다운받아서 lib에 삽입했다
            JSONParser parser = new JSONParser();								//simple parser를 임포트했다. 메소드를 사용하기 위해 객체생성
            JSONObject object =  (JSONObject)parser.parse(sb.toString());		//parsing하기 위해 다운캐스팅을 해서 문자열을 제이슨의 오브젝트(객체)로 변경		//오브젝트이기 떄문에 제이슨의 오브젝트로 반환한 것이다.
            																	//postman에서 {}이면 오브젝트형태 가져오는 키 형태가 []이면 배열의 형태이다. 여기서는 제이슨의 오브젝트, 여기서는 data는 배열로 나와있다.
            JSONArray array = (JSONArray)object.get("data"); //()안에 키를 입력하면 된다. 	//postman에서 왼쪽은 date, 옆의 값은 [를 보아 어레이 형태임을 알 수 있다. 떄문에 다운캐스팅을 해서 맞춰준다.
            System.out.println(array);
            System.out.println("------------------------------------------------------------");
            for(int i=0; i<array.size(); i++) {		//인덱스는 사이즈보다 항상 하나가 작기 때문에 사이즈만큼 반복하면 된다		//i번째 인덱스(하나의 오브젝트, 밑의 keyset)를 맵을 만들어서 안에 넣겠다
            	Map<String , String> map = new HashMap<>();
            	System.out.println(i+"----------------------------------------");
            	object = (JSONObject) array.get(i);	//오브젝트로 저장 2
           	//System.out.println(array.get(i));	 1				//배열이기 때문에 인덱스번호를 넣는다.				//배열의 0번인덱스가 오브젝트이다 (postman참고)		
            //출력해보면 안에 있는 인덱스가 모두 오브젝트로 쌓여있음을 알 수 있다. 떄문에 오브젝트로 저장해야 한다.
//            	//직접 key 하드코딩
//            	String cuntry_mn = (String) object.get("country_mn");								//오브젝트에서 꺼낼때는 키를, 인덱스에서 꺼낼떄는 인덱스 번호를 넣는다.
//            	String wrt_dt = (String) object.get("wrt_dt");										//키를 넣으면 값을 반환해주는데 값이 문자열로 (""로 쌓여있다)되어있기 때문에 스트링을 사용해서 받는다.
//            	System.out.println(cuntry_mn);
//            	System.out.println(wrt_dt);
//            
            	//key의 목록 가져와서 반복문
            	//Set의 자료구조의 특징: 어레이리스트와 달리 중복데이터가 못들어간다(키는 중복데이터를 넣으면 안되니까 셋으로 만들었다)
            	Set<String> kset = object.keySet();
            	for(String key:kset) {
            		System.out.println(key + ":" + object.get(key));		//왼쪽에 있는 것은 키, 오른쪽은 것은 값이 출력된다. ex)country_nm:독일
            		map.put(key, (String)object.get(key));					//맵에 키와 값을 삽입, 오브젝트형이기 떄문에 스트링에 맞지 않아 다운캐스팅을 해준다.
            	}
            //		System.out.println(map); 								//중괄호로 쌓여져 키값이 나오는걸 알 수 있다.
            		clist.add(map);											//위에서 만든 clist에 맵을 삽입한다. 이렇게 반복문 만큼(4번)c리스트에 삽입된다.
            }  
                    
           // System.out.println(clist);		//배열 형태로 clist를 생성했기 떄문에 []안에 담겨있는것을 볼 수 있다.
        
		} catch (Exception e) {	//모든 예외를 처리해준다.
			e.printStackTrace();
		}
    	//dao에 clist 넘기기	(여기서 넘긴다)
    	 int cnt = countryDAO.insert(clist);	//dao의 insert
    	 System.out.println(cnt+"건 저장");
    	 return cnt;
    }

	public List<Map<String, String>> selectList_iso() {	//dto없이 오라클에서 맵을 통해 받아오는 전체조회
		//iso 조회
		return countryDAO.selectList_iso();
	}

	public List<Country> selectList(String iso){
		return countryDAO.selectList(iso);		//컨트롤러로 넘긴다
	}

	public Country selectOne(String sfty_notice_id) {	//한건조회를 할 시에
		return countryDAO.selectOne(sfty_notice_id);	//서비스에서 country를 반환값으로 하여 해당 키를 넘겨주면 된다.
	}
    
}

