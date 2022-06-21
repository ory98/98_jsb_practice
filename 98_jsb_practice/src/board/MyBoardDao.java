package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MyBoardDao {
	
	/* 
	 	1. 객체 만들어주기 (싱글턴 패턴)
	 	2. DB와 연결
	 	3. 게시글을 추가하는 DAO
	 	4. 전체 게시글을 조회하는 DAO
	 	5. 하나의 게시글을 조회하는 DAO (조회수 올려주는  + 조회하는 )
	 	
	*/
	
	// 1.  객체 만들어주기 (싱글턴 패턴)
	private MyBoardDao() {}
	private static MyBoardDao instance = new MyBoardDao();
	public static MyBoardDao getInstance() {
		return instance;
	}
	
	// 2. DB와 연결
	private Connection conn 		= null;
	private PreparedStatement pstmt = null;
	private ResultSet rs 			= null;
	
	public Connection getConnection() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/step3_board_ex?serverTimezone=UTC" , "root" , "1234"); 
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return conn;
	}
	
	// 3. 게시글을 추가하는 DAO
	public void myInsertBoard(MyBoardDto myBoardDto) {
		
		try {
			conn = getConnection();
			String sql = "INSERT INTO BOARD(WIRTER, EMAIL, SUBJECT, PASSWORD, REG_DATE, READ_COUNT, CONTNET)";
				   sql += "VALUES(?,?,?,?,NOW(),0,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, myBoardDto.getWriter());
			pstmt.setString(2, myBoardDto.getEmail());
			pstmt.setString(3, myBoardDto.getSubject());
			pstmt.setString(4, myBoardDto.getPassword());
			pstmt.setString(5, myBoardDto.getContent());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}
			try {conn.close();} catch (SQLException e) {e.printStackTrace();}			
		}
		
	}
	
	// 4. 전체 게시글을 조회하는 DAO
	public ArrayList<MyBoardDto> myGetAllboard() {
		
		ArrayList<MyBoardDto> myBoardList = new ArrayList<MyBoardDto>();
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM BOARD");
			rs = pstmt.executeQuery();
			
			while (rs.next()) {

				MyBoardDto myboardDto = new MyBoardDto();
				myboardDto.setNum(rs.getInt("NUM"));
				myboardDto.setWriter(rs.getString("WRITER"));
				myboardDto.setEmail(rs.getString("EMAIL"));
				myboardDto.setSubject(rs.getString("SUBJECT"));
				myboardDto.setPassword(rs.getString("PASSWORD"));
				myboardDto.setRegDate(rs.getDate("REG_DATE"));
				myboardDto.setReadCount(rs.getInt("READ_COUNT"));
				myboardDto.setContent(rs.getString("CONTENT"));
				myBoardList.add(myboardDto);
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}
			try {conn.close();} catch (SQLException e) {e.printStackTrace();}			
		}
		return myBoardList;
	}
	
	// 5. 하나의 게시글을 조회하는 DAO (조회수 올려주는  + 조회하는 )

	
	
	
	
}
