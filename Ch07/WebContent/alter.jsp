<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
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
	st.executeUpdate("alter table woori modify name char(20) not null");
	st.executeUpdate("alter table woori add email char(30)");
	st.executeUpdate("alter table woori add password integer not null");
	
	// prepareStatement 로 바꿔 주려고 했으나, 바인드 변수를 쓰기에 불편하여 놔두었다.
} catch(SQLException e) {
	out.print(e);
}
try {
	rs = st.executeQuery("select * from woori");
	ResultSetMetaData rsmd = rs.getMetaData();
	out.print("새로운 테이블이 수정되었습니다.<br>");
	for(int i = 1; i<=rsmd.getColumnCount();i++){
		out.print(i + "번째 컬럼은" + rsmd.getColumnName(i));
		out.print("이고 유형은 : " + rsmd.getColumnTypeName(i));
		out.print("이며 크기는 : " + rsmd.getPrecision(i));
		out.print("<br>");
	}
	
	
} catch(SQLException e) {
	out.print(e);
} finally{
	if(rs != null) rs.close();
	if(st != null) st.close();
	if(con != null) con.close();
}


%>
</body>
</html>