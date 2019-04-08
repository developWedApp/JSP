<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%request.setCharacterEncoding("utf-8"); %>
<%
Connection con = null;		
Statement st = null;
ResultSet rs = null;

try {
	Class.forName("com.mysql.jdbc.Driver");		
} catch (ClassNotFoundException e) {
	out.print(e);
}

try {
	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/member?useSSL=false","multi","1234");	
} catch (SQLException e){
	out.print(e);
}

try {
	st = con.createStatement();
	rs = st.executeQuery("select * from woori order by id");


%>

<div align="center">
	<table border="1">
		<tr>
			<th>사용자 ID</th>
			<th>이름</th>
			<th>E-mail</th>
		</tr>
	<%if(!(rs.next())) {%>
		<tr><td colspan="3"> 등록된 회원이 없습니다.</td></tr>
	<%} else {
		do{
			out.println("<tr>");
			out.println("<td>" + rs.getString("id")+"</td>");
			out.println("<td>" + rs.getString("name") + "</td>");
			out.println("<td>" + rs.getString("email") + "</td>");
			out.println("</tr>");
		} while(rs.next());
		
	}
		rs.close();
		st.close();
		con.close();
		
	} catch(SQLException e) {
		out.print(e);
	}
%>
		
	</table>
	<a href="main.jsp">main으로 이동</a> &nbsp; <a href="insert.html">회원 등록 페이지로</a>
</div>
	
</body>
</html>
