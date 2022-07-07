package mvc2_loginDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import mvc2_loginDto.M2memberDto;

public class M2memberDao {
	
	private M2memberDao() {}
	private static M2memberDao instance = new M2memberDao();
	public static M2memberDao getInstance() {
		return instance;
	}
	
	private Connection conn 		= null;
	private PreparedStatement pstmt = null;
	private ResultSet rs 			= null;
	
	public Connection getConnection() {
		
		try {
			
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource)envCtx.lookup("jdbc/pool1");  
			conn = ds.getConnection();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	// 1. 회원가입 DAO
	public boolean joinMember(M2memberDto m2memberDto) {
		
		boolean isJoin = false;
		
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM MEMBER WHERE ID =?");
			pstmt.setString(1, m2memberDto.getId());
			rs = pstmt.executeQuery();
			
			if(!rs.next()) {
				pstmt =conn.prepareStatement("INSERT INTO MEMBER(ID,PW,NAME,TEL,EMAIL) VALUES(?,?,?,?,?) ");
				pstmt.setString(1, m2memberDto.getId());
				pstmt.setString(2, m2memberDto.getPw());
				pstmt.setString(3, m2memberDto.getName());
				pstmt.setString(4, m2memberDto.getTel());
				pstmt.setString(5, m2memberDto.getEmail());
				pstmt.executeUpdate();
				isJoin = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) 	try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			if (pstmt != null) 	try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}
			if (conn != null) 	try {conn.close();} catch (SQLException e) {e.printStackTrace();}
		}

		return isJoin;
		
	}
	// 2. 로그인 DAO
	// 3. 한명의 회원 정도 조회 DAO 
	// 4. 입사지원 DAO
	// 5. 회원탈퇴 DAO
	// 6. 회원정보 수정 DAO
}

