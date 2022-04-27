<%@page import="java.util.Scanner"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>표현식</h2>
	<%
		int a=10, b=20;
		out.println("<div id='add'>합:"+(a+b)+"</div>");
	%>
	<!-- 간편버전 -->
	<!-- 변수, 값만 넣어야 한다 -->
	<div id="add">
		합:<%=a+b%>
	<hr>
	</div>
<!-- 	age라는 변수에 나이를 저장하고 나이가 20살 이상이면 성인 아니면 미성년자를 dom에 출력(표현식을 이용해서) 
		예: 21은 성인
			18살은 미성년자
<%-- jsp주석(	) --%>
			-->
				
<%-- 			
				<%	int age=21;
				String s=null;	
				if(age>19){
				//out.println(age+"살은 성인");
					s = age+"살은 성인";
				}else{
				//out.println(age+"살은 미성년자");
				s = age+"살은 미성년자";
				}
				%>
				<%=s %>  <!-- out.println없이 돔에 표현식으로 표현한 방법 --> --%>
				
				
				
				<hr>
				<!--표현식으로-->
	<%-- 			<%
				Scanner sc = new Scanner(System.in);
				System.out.print("몇살?");
		 		int age = sc.nextInt();
		 		if (age>19){
			%> 			
		 			<%=age%>살은 성인

		 	<% 	}else{
		 	%>
		 			<%=age%>살은 미성년자
		 	<% 	}
		 	
		 	%> --%>
		 	
		 	<%-- <hr>
		 	<h5>구구단 출력(2단)</h5>		 	
		 	<%	
		 		int dan = 3;
		 		for(int i=1; i<10; i++){
		 	%>
		 		<%=dan %> * <%=i %> =  <%=dan*i %> <br>
		 	<% } 
		 	%> --%>
		 	
		 	<hr>
		 	<%-- 자바코드는 <% %>안에 쓰여야 한다. 화면에 그대로 출력되는게 아니라 식을 나타내는 문법이기 때문에 
		 	<% %>를 통해 출력한다. --%>
		 	<!-- 2단부터 9단까지 이중for문을 사용해서 출력 -->
		 	<!-- 구구단 for문 작성시 바깥 반복문은 단수, 안쪽 반복문은 곱해지는 숫자로 해야한다 -->	
		 	<% 
		 		for(int i=2; i<10; i++){
		 	%>	
		 		<div>★<%=i%>단★</div>
		 	
		 	<% 	for(int j=1; j<10; j++){
		 	%>
		 		<%=i %>*<%=j %> =<%=i*j %><br>
		 	<% 	 }
		 	
		 		}
		 	%>
		 		
				
	
</body>
</html>