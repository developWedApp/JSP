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
String id = request.getParameter("id");
String sql = null;

Connection con = null;		//Connection 타입의 변수 con (참조 변수) 를 생성하였는데 아무것도 참조하지 않고 있다.
Statement st = null;
ResultSet rs = null;

try {
	Class.forName("com.mysql.jdbc.Driver");		//드라이버를 로딩하는 작업
} catch (ClassNotFoundException e){
	out.print(e);
}
//DB를 JAVA와 연결한다.
try {
	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/member?useSSL=false","multi","1234");	
} catch (SQLException e){
	out.print(e);
}
//쿼리를 실행한다.
try {
	st = con.createStatement();
	sql = "delete from woori where id= '" + "'";
	st.executeUpdate(sql);
	st.close();
	con.close();
} catch(SQLException e) {
	out.print(e);
}


%>
<jsp:forward page="select_all.jsp"></jsp:forward>


</body>
</html>