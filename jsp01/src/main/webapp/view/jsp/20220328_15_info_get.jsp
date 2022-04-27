<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		//mag : 계열 메시지 
		//값 불러오기 
		String name = request.getParameter("name");
		String major = request.getParameter("major");
		System.out.println(name);
		System.out.println(major);
		String prefix = major.substring(0,1);
		/* 벨류값의 0번 인덱스부터 1번 인덱스까지 */
		System.out.println(prefix);
		//만약에 prefix가 'A'라면 공학계열이다
		//'B'라면 자연계열이다
		String msg;
		if(prefix.equals("A")){
			msg="공학계열";
		}else if(prefix.equals("B")){
			msg="자연계열";
		}else{
			msg="무 계열";
		}
		System.out.println(msg);
		//redirect 이동
		//자바의 인코딩과 url의 인코딩이 다	르다.
		/* 떄문에 url에 문자를 붙일 경우에는 utf-8로 꼭 변경해서 넣어야 한다. */
		name = URLEncoder.encode(name,"utf-8");
		major = URLEncoder.encode(major,"utf-8");
		msg = URLEncoder.encode(msg,"utf-8");
		/* 문자열을 utf-8로 변경하여 name에 저장 */ 
		response.sendRedirect("20220328_15_result.jsp?name="+name+"&major=" + major);
		
		
		
	%>
</body>
</html>