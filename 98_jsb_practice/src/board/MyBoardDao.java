package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MyBoardDao {

	/*
	 * 1. 객체 만들어주기 (싱글턴 패턴) 2. DB와 연결 3. 게시글을 추가하는 DAO 4. 전체 게시글을 조회하는 DAO 5. 하나의
	 * 게시글을 조회하는 DAO (조회수 올려주는 + 조회하는 ) 6. 비밀번호를 인증하는 DAO 7. 게시글을 수정하는 DAO 8. 게시글을
	 * 삭제하는 DAO
	 */

	// 1. 객체 만들어주기 (싱글턴 패턴)
	private MyBoardDao() {
	}

	private static MyBoardDao instance = new MyBoardDao();

	public static MyBoardDao getInstance() {
		return instance;
	}

	// 2. DB와 연결
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public Connection getConnection() {

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/step3_board_ex?serverTimezone=UTC", "root",
					"8360");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	// 3. 게시글을 추가하는 DAO
	public void myInsertBoard(MyBoardDto myBoardDto) {

		try {
			conn = getConnection();
			String sql = "INSERT INTO BOARD(WRITER, EMAIL, SUBJECT, PASSWORD, REG_DATE, READ_COUNT, CONTENT)";
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
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return myBoardList;
	}

	// 5. 하나의 게시글을 조회하는 DAO (조회수 올려주는 + 조회하는)
	public MyBoardDto myGetOneBoard(int num) {

		MyBoardDto myBoardDto = new MyBoardDto();

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("UPDATE BOARD SET READ_COUNT = READ_COUNT + 1 WHERE NUM = ?");
			pstmt.setInt(1, num);
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement("SELECT * FROM BOARD WHERE NUM = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				myBoardDto.setNum(rs.getInt("NUM"));
				myBoardDto.setWriter(rs.getString("WRITER"));
				myBoardDto.setEmail(rs.getString("EMAIL"));
				myBoardDto.setSubject(rs.getString("SUBJECT"));
				myBoardDto.setPassword(rs.getString("PASSWORD"));
				myBoardDto.setRegDate(rs.getDate("REG_DATE"));
				myBoardDto.setReadCount(rs.getInt("READ_COUNT"));
				myBoardDto.setContent(rs.getString("CONTENT"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return myBoardDto;
	}

	// 6. 비밀번호를 인증하는 DAO
	public boolean myVaildMemberCheck(MyBoardDto myBoardDto) {

		boolean myIsVaildMember = false;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("SELECT * FROM BOARD WHERE NUM = ? AND PASSWORD = ?");
			pstmt.setInt(1, myBoardDto.getNum());
			pstmt.setString(2, myBoardDto.getPassword());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				myIsVaildMember = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return myIsVaildMember;
	}

	// 7. 게시글을 수정하는 DAO
	public boolean myUpdateBoard(MyBoardDto myBoardDto) {

		boolean myIsUpdate = false;

		try {

			if (myVaildMemberCheck(myBoardDto)) {
				conn = getConnection();
				pstmt = conn.prepareStatement("UPDATE BOARD SET SUBJECT = ? , CONTENT = ? WHERE NUM = ?");
				pstmt.setString(1, myBoardDto.getSubject());
				pstmt.setString(2, myBoardDto.getContent());
				pstmt.setInt(3, myBoardDto.getNum());
				pstmt.executeUpdate();
				myIsUpdate = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return myIsUpdate;
	}

	// 8. 게시글을 삭제하는 DAO
	public boolean myDeleteBoard(MyBoardDto myBoardDto) {

		boolean isDelete = false;

		try {

			if (myVaildMemberCheck(myBoardDto)) {
				conn = getConnection();
				pstmt = conn.prepareStatement("DELETE FROM BOARD WHERE NUM = ?");
				pstmt.setInt(1, myBoardDto.getNum());
				pstmt.executeUpdate();
				isDelete = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return isDelete;
	}

}
