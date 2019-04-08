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
<jsp:useBean id="user" class="user.User" scope="session"></jsp:useBean>
<jsp:setProperty property="userId" name="user" param="id"/>
<jsp:setProperty property="userName" name="user" param="name"/>
<jsp:setProperty property="userEmail" name="user" param="email"/>
<jsp:setProperty property="userPasswd" name="user" param="password"/>
<%
String sql = null;
int cnt = 0;
String check_id = null;

Connection con = null;		//Connection 타입의 변수 con (참조 변수) 를 생성하였는데 아무것도 참조하지 않고 있다.
PreparedStatement pst = null;
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
	sql = "insert into woori(id, password, name, email) values(?, ?, ?, ?)";
	pst = con.prepareStatement(sql);
	pst.setString(1, user.getUserId());
	pst.setString(2, user.getUserPasswd());
	pst.setString(3, user.getUserName());
	pst.setString(4, user.getUserEmail());
	cnt = pst.executeUpdate();
	if(cnt>0){ 
		out.print("데이터가 성공적으로 입력되었습니다.");
	} else {
		out.print("데이터가 입력되지 않았습니다.");
	}
	
	pst.close();
	con.close();
	
	out.print("<br>");
} catch(SQLException e) {
	out.print(e);
} finally {
	
}


%>
[<a href ="main.jsp">main으로 이동</a>]<br>
[<a href ="insert.html">insert으로 이동</a>]

</body>
</html>