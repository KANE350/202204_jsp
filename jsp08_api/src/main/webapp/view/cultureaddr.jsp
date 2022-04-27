<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>						<!--컨테스트 패스를 쓰기 위해 위의 두줄을 작성하고 라이브러리 tag들을 가져왔다.-->
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	 <script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=xmyrulwcoh"></script>	<!--ncpClientId: 네이버의 map api신청 승인키 해당 키를 넣어라 네이버 라이브러리를 사용할 수 있다.-->
	 <!--지도를 표시하고 싶으면 위의 해당 url을 추가하면 된다.-->
	  <script>
	  //geomap이 null이라면 alert('맵이 없습니다') 띄워주기
		  
	   function mapMake() {
		  
		  //alert('${param.msg}')	//띄워봤을때 메시지가 null이 아니라 공백으로 뜨는 것을 알 수 있다.
		  
		  //geomap이 ''일 경우
		  if ('${geomap}'==''){
				document.getElementById('map').innerHTML = '지도 정보가 없습니다.';
				var x = '0';			//이렇게 작성을 하지 않을 경우 경도위도가 먼저 잡혀서 오류가 나버린다. 때문에 0으로 따로 설정했다. 
				var y = '0';
				return ;
			}else{	//아닐떄는 x,y라는 변수에 기존의 값을 넣는다. 그냥하면 지역변수가 되어서 앞괄호에서 시작된다. var라고 선언하고 해야 후에 선언이 된다. 
				var x = '${geomap.x}';
				var y = '${geomap.y}';
			}
		   	console.log(x);
		   	console.log(y);
		   	
		  //map id에 맵생성
	   		var map = new naver.maps.Map('map', {		//new naver.maps.LatLng네이버에 있는 맵스점 맵
	   		center: new naver.maps.LatLng(y, x),		//클래스 호출maps.LatLng(위도, 경도) //{}맵이라는 아이디에 옵션값으로하나의 쌍을 이룬다. 어디에 주소를 표기할 것인가.
	   	    zoom: 15	//확대번호, 숫자가 높을수록 위치가 자세하게 나온다. 센터
	   	});	//자바스크립트의 오브젝트 자바나 제이슨처럼 키,값이 존재한다.		
	       				//해당값을 넣어서 맵을 만들라고 명령하면, 위의 div사이에 map이 생성된다.
	       //마커생성
	       var marker = new naver.maps.Marker({
	    	   position: new naver.maps.LatLng(y, x),
	   	    map: map	//맵이라는 아이디에 옵션값으로하나의 쌍을 이룬다.
	   	});
	}
	   				
	</script>
</head>
<body onload="mapMake()">	<!--맵이 생성됐을때 mapmake를 호출해라-->
	${geomap}
	<h2>서울시 문화위치정보 명칭 검색</h2>
	<%-- ${map} --%>
	<form action="${path}/addr.culture">
	명칭 <input type="text" name="name" value="${param.name}"><!--폼이 있을때는 반드시 이름이 있어야 넘길 수 있다, 벨류를 쓴 이유는 검색시 검색값이 남아있게 하기 위함이다.-->
	<button>검색</button>
	</form>

		<table border="1">		<!--값은 폼 밖에서 뿌린다. 반복문을 사용하지 않았기 떄문에(리스트가 아니니까) map.으로 뿌리면 된다.-->
	<tr>
		<th>문화공간코드</th>
		<td>${map.FAC_CODE}</td>
	</tr>
	<tr>
		<th>장르분류코드</th>
		<td>${map.SUBJCODE}</td>
	</tr>
	<tr>
		<th>장르분류명</th>
		<td>${map.FAC_NAME}</td>
	</tr>
	<tr>
		<th>문화공간명</th>
		<td>${map.CODENAME}</td>
	</tr>	
	<tr>	
		<th>주소</th>
		<td>${map.ADDR}</td>
	</tr>
	</table>
	<!-- 지도 표시 위치 -->
	<div id="map" style="width:90%; height:400px; margin:auto"></div>	<!--맵을 여기 설정해야 아래에서 작성해서 뿌릴 수 있다. 순서가 중요하다.
															하지만 여기서는 function을 사용해서 위에 올렸다.-->

</body>
</html>