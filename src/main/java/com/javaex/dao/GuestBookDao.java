package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.GuestBookVo;

public class GuestBookDao {

	//필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";
	//생성자
	
	//메소드 gs
	
	//메소드 일반
	
	public void getConnecting() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);
			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 	
	}
	
	public void Close() {
		try {
			if (rs != null) {
				rs.close();
			} 
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	public int bookInsert(GuestBookVo guestVo) {
		int count = -1;

		try {
			this.getConnecting();
			
			// 3. SQL문 준비 / 바인딩 / 실행
			//SQL문 준비
			String query = "";
			query += " insert into guestbook ";
			query += " values(SEQ_GUESTBOOK_NO.nextval,?,?,?,sysdate) ";
			//바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, guestVo.getName());
			pstmt.setString(2, guestVo.getPassword());
			pstmt.setString(3, guestVo.getContent());
			
			//실행
			count = pstmt.executeUpdate();
			
			// 4.결과처리
			System.out.println(count+ "건 등록되었습니다");
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		this.Close();
		
		return count;
	}

	
	public int bookDelete(int no, String password) {
		int count = -1;

		
		try {
			
			this.getConnecting();
			// 3. SQL문 준비 / 바인딩 / 실행
			//SQL문 준비
			String query = "";
			query += " delete from guestbook ";
			query += " where no = ? ";
			query += " and password = ? ";
			//바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			pstmt.setString(2, password);
			//실행
			
			count = pstmt.executeUpdate();
			
			// 4.결과처리
			System.out.println(count+ "건 삭제되었습니다");
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		this.Close();
		return count;
	}
	
	public List<GuestBookVo> getGuestList() {
		List<GuestBookVo> guestList = new ArrayList<GuestBookVo>();
	
		try {
			
			this.getConnecting();
			
			// 3. SQL문 준비 / 바인딩 / 실행
			//SQL문 준비
			String query ="";
			query += " select  no ";
			query += "         ,name ";
			query += "         ,password ";
			query += "         ,content ";
			query += "         ,to_char(sysdate, 'YYYY-MM-DD HH:MI:SS') reg_date ";
			query += " from guestbook ";
			
			//바인딩
			pstmt = conn.prepareStatement(query);
			//실행
			rs = pstmt.executeQuery();
			// 4.결과처리
			while(rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String content = rs.getString("content");
				String regDate = rs.getString("reg_date");
				
				GuestBookVo guestVo = new GuestBookVo(no, name, password, content, regDate);
				
				guestList.add(guestVo);
				
				System.out.println(guestVo.toString());
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		this.Close();

		return guestList;
	}
	
	public GuestBookVo getGuest(int no) {
		GuestBookVo guestVo = null;
		try {
			
			this.getConnecting();
			
			// 3. SQL문 준비 / 바인딩 / 실행
			//SQL문 준비
			String query ="";
			query += " select  no ";
			query += "         ,name ";
			query += "         ,password ";
			query += "         ,content ";
			query += "         ,to_char(sysdate, 'YYYY-MM-DD HH:MI:SS') reg_date ";
			query += " from guestbook ";
			query += " where no = ? ";
			
			//바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			//실행
			rs = pstmt.executeQuery();
			// 4.결과처리
			while(rs.next()) {
				no = rs.getInt("no");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String content = rs.getString("content");
				String regDate = rs.getString("reg_date");
				
				guestVo = new GuestBookVo(no, name, password, content, regDate);
				System.out.println(guestVo.toString());
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		this.Close();

		return guestVo;
	}
	
}
	
	


