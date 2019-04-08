
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
	st.executeUpdate("create table woori(id char(10) primary key,name char(10))");
} catch(SQLException e) {
	out.print(e);
}
try {
	rs = st.executeQuery("select * from woori");
	ResultSetMetaData rsmd = rs.getMetaData();
	out.print("새로운 테이블이 생성되었습니다.<br>");
	out.print(rsmd.getColumnCount() + " 개의 컬럼을 가지고 있습니다.<br>");
	out.print("첫 번째 칼럼은 " + rsmd.getColumnName(1) + ", 타입은 : " + rsmd.getColumnType(1) + ", 길이" + rsmd.getColumnDisplaySize(1) + "<br>");
	out.print("첫 번째 칼럼은 " + rsmd.getColumnName(2) + ", 타입은 : " + rsmd.getColumnType(2) + ", 길이" + rsmd.getColumnDisplaySize(2) + "<br>");
	
	
} catch(SQLException e) {
	out.print(e);
} finally {
	if(rs !=null) rs.close();
	if(st !=null) st.close();
	if(con !=null) con.close();
}


%>

</body>
</html>