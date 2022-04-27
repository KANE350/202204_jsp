<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <title>간단한 지도 표시하기</title>
    <script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=xmyrulwcoh"></script>
    <!--지도를 표시하고 싶으면 위의 해당 url을 추가하면 된다.-->
     <script>
    function mapMake() {
    	var map = new naver.maps.Map('map', {
    	    center: new naver.maps.LatLng(37.484746663141095, 126.93006829643338),//클래스 호출mㄹaps.LatLng(위도, 경도)
    	    zoom: 15	//확대번호, 숫자가 높을수록 위치가 자세하게 나온다.
    	});	//자바스크립트의 오브젝트 자바나 제이슨처럼 키,값이 존재한다.		
        				//해당값을 넣어서 맵을 만들라고 명령하면, 위의 div사이에 map이 생성된다.
        //마커생성
        var marker = new naver.maps.Marker({
    	    position: new naver.maps.LatLng(37.3595704, 127.105399),
    	    map: map
    	});
	}
    				
	</script>
</head>
<body onload="mapMake()">		<!--밑의 div가 실행되면 function을 호출해서 실행시킨다.-->	
<div id="map" style="width:100%; height:400px;"></div>	<!--맵을 여기 설정해야 아래에서 작성해서 뿌릴 수 있다. 순서가 중요하다.
															하지만 여기서는 function을 사용해서 위에 올렸다.-->


</body>
</html>