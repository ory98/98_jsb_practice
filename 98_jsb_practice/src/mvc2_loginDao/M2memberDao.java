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
			pstmt = conn.prepareStatement("SELECT * FROM MEMBER WHERE ID = ?");
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
	
	public boolean login(M2memberDto m2memberDto) {
		
		boolean isLogin = false;
		
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM MEMBER WHERE ID = ? AND PW = ?");
			pstmt.setString(1, m2memberDto.getId());
			pstmt.setString(2, m2memberDto.getPw());
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				isLogin = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) 	try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			if (pstmt != null) 	try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}
			if (conn != null) 	try {conn.close();} catch (SQLException e) {e.printStackTrace();}
		}
		
		return isLogin;
	}
	// 3. 한명의 회원 정도 조회 DAO 
	public M2memberDto getOneMemberInfo(String id) {
		
		M2memberDto m2memberDto = null;
		
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM MEMBER WHERE ID = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				
				m2memberDto = new M2memberDto();
				m2memberDto.setId(rs.getString("ID"));
				m2memberDto.setPw(rs.getString("PW"));
				m2memberDto.setName(rs.getString("NAME"));
				m2memberDto.setTel(rs.getString("TEL"));
				m2memberDto.setEmail(rs.getString("EMAIL"));
				m2memberDto.setField(rs.getString("FIELD"));
				m2memberDto.setSkill(rs.getString("SKILL"));
				m2memberDto.setMajor(rs.getString("MAJOR"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			if (pstmt != null) try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}
			if (conn != null) try {conn.close();} catch (SQLException e) {e.printStackTrace();}
		}
		
		return m2memberDto;
		
	}
	
	// 4. 입사지원 DAO
	public void apply(M2memberDto m2memberDto) {
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("UPDATE MEMBER SET FIELD = ?, SKILL = ?, MAJOR = ? WHERE ID = ?");
			pstmt.setString(1, m2memberDto.getField());
			pstmt.setString(2, m2memberDto.getSkill());
			pstmt.setString(3, m2memberDto.getMajor());
			pstmt.setString(4, m2memberDto.getId());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}
			if (conn != null) try {conn.close();} catch (SQLException e) {e.printStackTrace();}
		}
	}
	
	// 5. 회원탈퇴 DAO
	public void deleteMember(String id) {
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("DELETE FROM MEMBER WHERE ID = ?");
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}
			if (conn != null) try {conn.close();} catch (SQLException e) {e.printStackTrace();}
		}
		
	}
	
	
	// 6. 회원정보 수정 DAO
	public void updateMember(M2memberDto m2memberDto) {
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("UPDATE MEMBER SET PW = ?, NAME = ?, TEL = ?, EMAIL = ?, FIELD = ?, SKILL =? , MAJOR = ? WHERE ID = ?");
			pstmt.setString(1, m2memberDto.getName());
			pstmt.setString(2, m2memberDto.getName());
			pstmt.setString(3, m2memberDto.getTel());
			pstmt.setString(4, m2memberDto.getEmail());
			pstmt.setString(5, m2memberDto.getField());
			pstmt.setString(6, m2memberDto.getSkill());
			pstmt.setString(7, m2memberDto.getMajor());
			pstmt.setString(8, m2memberDto.getId());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}
			if (conn != null) try {conn.close();} catch (SQLException e) {e.printStackTrace();}
		}
	}
}

