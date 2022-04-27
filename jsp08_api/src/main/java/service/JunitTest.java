package service;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

	class JunitTest {
		CovidXMLService covidService = new CovidXMLService();
		
		@Test
		void test(){
			//코로나확진자
				 covidService.covidParsing("20220405", "20220413");
		}
		
		CountryJSONService countryService = new CountryJSONService();
		@Test
		//컨트리 테스트
		void testCountry() {	//throws 예외처리
			countryService.countryParsing("GB");		//값을 넘기기 위해 조회하고 싶은 국가코드를 넣어준다. 컨트롤러에서 clist를 void로 돌렸기 때문에 반환값이 사라져서 clist 사용 불가.
			
		}
		
		
		CultureService cultureservice = new CultureService();
		@Test
		//문화위치정보
		void testCulture(){				//자바코드까지 서비스로 받아오면 url만 출력되던 것이 내용까지 출력되는것을 볼 수 있다.
			Map<String, Object> map = cultureservice.cultureParsing("서울연극센터");
			System.out.println("map:"+ map);
		}
		
		@Test
		//지오코딩 테스트
		void testCultureGeocoding(){				//자바코드까지 서비스로 받아오면 url만 출력되던 것이 내용까지 출력되는것을 볼 수 있다.
			//cultureservice.geocoding("서울 종로구 사직동 사직로9길 7");
			Map<String, Double> map = cultureservice.geocoding("서울 종로구 사직동 사직로9길 7");
			System.out.println(map);
		}
		
		EventService eventService = new EventService();
		//@Test
		//문화행사정보	
		//void testEvent(){
			//List<Map<String, Object>>  map = eventService.eventParsing("콘서트","제이유나");
			//System.out.println("여기는 제이유닛"+map);
		//}
		
		//미세먼지 테스트
		DustService dustService = new DustService();
		@Test
		void testDust() {
			dustService.dustPasing("2022");
		}
		

	}
	