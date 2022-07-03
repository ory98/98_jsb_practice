package mvc2_loginDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import mvc2_loginDto.MyMemberDto;


public class MyMemberDao {
	
	private MyMemberDao() {}
	private static MyMemberDao instance = new MyMemberDao();
	public static MyMemberDao getinstance() {
		return instance;
	}
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs =null;
	
	public Connection getConnection() {
		
		try {
			
			Context initCtx = new InitialContext();
    		Context envCtx = (Context)initCtx.lookup("java:comp/env");
    		DataSource ds = (DataSource)envCtx.lookup("연결명");   
    		conn = ds.getConnection();
    		
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return conn;
	}
	
	public boolean joinMember(MyMemberDto myMemberDto) {
		
		boolean isJoin = false;
		
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM MEMBER WHERE ID =?");
			pstmt.setString(1, myMemberDto.getId());
			rs = pstmt.executeQuery();
			
			if(!rs.next()) {
				pstmt = conn.prepareStatement("INSERT INTO MEMBER(ID,PW,NAME,TEL,EMAIL) VALUES(?,?,?,?,?)");
				pstmt.setString(1, myMemberDto.getId());
				pstmt.setString(2, myMemberDto.getPw());
				pstmt.setString(3, myMemberDto.getName());
				pstmt.setString(4, myMemberDto.getTel());
				pstmt.setString(5, myMemberDto.getEmail());
				pstmt.executeUpdate();
				isJoin = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) 	try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			if (pstmt != null)	try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}
			if (conn != null)	try {conn.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return isJoin;
	}
	
	public boolean login(MyMemberDto myMemberDto) {
		
		boolean isLogin = false;
		
		try {
			
			conn = getConnection();
			
			pstmt = conn.prepareStatement("SELECT * FROM MEMBER WHERE ID = ? AND PW = ?");
			pstmt.setString(1, myMemberDto.getId());
			pstmt.setString(2, myMemberDto.getPw());
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				isLogin = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)    try{rs.close();} catch (SQLException e) {e.printStackTrace();}
			if (pstmt != null) try{pstmt.close();} catch (SQLException e) {e.printStackTrace();}
			if (conn != null)  try{conn.close();} catch (SQLException e) {e.printStackTrace();}	
		}
		return isLogin;	
	}
	
	public MyMemberDto getOneMemberInfo(String id) {
		
		MyMemberDto myMemberDto = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM MEMBER WHERE ID = ?");
			pstmt.setString(1, id);
			rs= pstmt.executeQuery();
			
			if (rs.next()) {
				
				myMemberDto = new MyMemberDto();
				myMemberDto.setId(rs.getString("ID"));
				myMemberDto.setPw(rs.getString("PW"));
				myMemberDto.setName(rs.getString("NAME"));
				myMemberDto.setTel(rs.getString("TEL"));
				myMemberDto.setEmail(rs.getString("TEL"));
				myMemberDto.setField(rs.getString("FIELD"));
				myMemberDto.setSkill(rs.getString("SKILL"));
				myMemberDto.setMajor(rs.getString("MAJOR"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)    try{rs.close();} catch (SQLException e) {e.printStackTrace();}
			if (pstmt != null) try{pstmt.close();} catch (SQLException e) {e.printStackTrace();}
			if (conn != null)  try{conn.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return myMemberDto;
	}

}
