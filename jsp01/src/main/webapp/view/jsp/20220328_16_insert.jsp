<%@page import="java.net.URLEncoder"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
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
		//db컨넥션
		String url ="jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String password = "hr";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("로딩 성공");
		Connection con = DriverManager.getConnection(url, user, password);
		System.out.println("접속 성공");
		
		
 		String sql = "INSERT INTO member (userid, passwd, name, email)" + 
				"VALUES (?, ?, ?, ?)";
 		/* db에 있는 name을 가져온다 */
 		request.setCharacterEncoding("utf-8");
 		/* post인코딩을 위해 변환한다. */
 		String userid = request.getParameter("userid");
 		String passwd = request.getParameter("passwd");
 		String name = request.getParameter("name");
 		String email = request.getParameter("email");
 		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, userid);
		pstmt.setString(2, passwd);
		pstmt.setString(3, name);
		pstmt.setString(4, email);
		
		int cnt = pstmt.executeUpdate();
		System.out.println(cnt+"건 추가");
		//가입화면으로 이동
		//url 주소를 변경, msg 전달  
		//주소를 변경하는 이유, 새로고침을 하면 주소가 변경된 상태 그대로여서 창이 변경되지 않고 오류가 나기 때문에 
		String msg = URLEncoder.encode(cnt + "건 추가", "utf-8");
		response.sendRedirect("20220328_16_db.jsp?msg=" + msg);
		//response.sendRedirect("20220328_16_db.jsp?이름=" + 값)
		
	%>
</body>
</html>