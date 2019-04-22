package com.springbook.biz.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//설정 변경이 많을 것 같지 않다. 그래서 Web.xml에 선언적으로 처리하기 보다는 Annotation 으로 처리해주면 좋을 것 같다.


public class JDBCUtil {

	
	public static Connection getConnection() {
		//JDBC 드라이버 로딩 리턴 값으로 커넥션 생성
		try {
			String driverName = "oracle.jdbc.driver.OracleDriver";
			String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
			String dbUser = "scott";
			String dbPass = "tiger";
			
			Class.forName(driverName);
			return DriverManager.getConnection(jdbcURL, dbUser, dbPass);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void close(PreparedStatement pstmt, Connection conn) {
		
		if(pstmt != null) {
			try {
				if(!pstmt.isClosed()) pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pstmt = null;
			}
		}
		
		if(conn != null) {
			try {
				if(!conn.isClosed()) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				conn = null;
			}		
		}
	}
	
	public static void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		
		if(rs != null) {
			try {
				if(!rs.isClosed()) rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				rs = null;
			}
		}
		
		if(pstmt != null) {
			try {
				if(!pstmt.isClosed()) pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pstmt = null;
			}
		}
		
		if(conn != null) {
			try {
				if(!conn.isClosed()) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				conn = null;
			}		
		}
		
	}
	
	
	
	
	
	
	
	
}
