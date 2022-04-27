<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!--prefix :접두사. 태그로 만들어진 라이브러리이다.-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--지시자 추가. 태그는 어떤 라이브러리를 추가할때 사용한다.  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>jsp변수</h2>
	<%
		String path = request.getContextPath();
		pageContext.setAttribute("path", path); //pageScope
		//얘가 리퀘스트면 두 화면간의 전달이 가능하고 섹션이면 내가 끊을 때까지 가능하다.
		//페이지 스코프는 딱 이 페이지에서만 사용 가능. 만일 path를 사용한다면 path는 이 페이지에서만 사용 가능하다. 
	%>
	<a href="<%=path%>/view/20220405_01_el.jsp">링크</a>
	<!--컨텍스트패스를 담은 <%=path%>는 슬래쉬까지 포함되어 있는 상태다.  -->
	
	<h2>jstl+el을 이용해 표현</h2>
	<!-- pageContext: 웹 컨테이너가 구현한 객체(request객체를 얻기 위해) -->
	<c:set var="path" value="${pageContext.request.contextPath}"/>
	<a href="${path}/view/20220405_01_el.jsp">링크</a><br>
	${pageScope.path} <br>
	
	
	
	<%-- <c:set/>변수를 담을 수 있는 태그 
	패이콘텍스트는 이 페이지 안에 있다는 뜻. 
	때문에 이렇게 작성해도 여기에서 읽어들일수는 있다.   --%>
	
	
</body>
</html>