<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<!-- jstl라이브러리 -->    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 컨텍스트패스 변수-->
<c:set var="path" value="${pageContext.request.contextPath}"/>
 
 <script>
	 //파라메터 메시지 창
 	if ('${param.msg}' != ''){
 		alert('${param.msg}');
 	}
	 
 	 //requestScope 메시지 창
 	if ('${msg}' != ''){
 		alert('${msg}');
 	}
 	 
 </script>