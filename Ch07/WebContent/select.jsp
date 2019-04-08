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
String password = request.getParameter("password").trim();
String sql = "select * from woori where id=?";

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
	pst = con.prepareStatement(sql);
	pst.setString(1, id);
	rs = pst.executeQuery();
	if(!rs.next()){
		out.print("해당 회원이 없습니다.");
	} else { 
		if(password.equals(rs.getString("password"))){ %>
			사용자 ID가 <%= id %>인 회원 정보는 다음과 같습니다.
			정보를 변경하려면 내용을 입력한 다음 <수정하기>버튼을 누르세요.
			<form name="form1" method="post" action="update.jsp">
				<input type="hidden" name="id" value="<%=id %>">
				<br> 이름 :
				<input type="text" name="name" value="<%=rs.getString("name") %>">
				<br> E-mail :
				<input type="email"name="email" value="<%=rs.getString("email") %>">
				<input type="submit" name="change" value="수정하기">
				<a href="delete.jsp?id=<%=id %>">삭제하기</a>
			
			</form>
			<% } else { %>
			비밀번호가 틀립니다.
			<%}
	}
	rs.close();
	pst.close();
	con.close();
} catch(SQLException e){
	out.println(e);
}
				
				
				%>
			
	
		
	
		

		

[<a href ="main.jsp">main으로 이동</a>]
[<a href ="select.html">조회 페이지로</a>]

</body>
</html>