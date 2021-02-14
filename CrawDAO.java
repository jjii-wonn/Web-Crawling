package modelFinal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

class CrawDAO {
	private String driver = "oracle.jdbc.driver.OracleDriver";	
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String userid = "scott";
	private String passward = "tiger";
	
	CrawDAO(){
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
			for(int i = 0 ; i<=7 ; i++) {
				System.out.println("DB 접속 실패!!");
			}
		}
	}
	
	public ArrayList<SelectDTO> refinedSelect(){
		Connection conn = null;
		String sql = "select B.NewsTitle, B.sliced_words, count(B.sliced_words) as word_cnt from (select NewsTitle, case when substr(regexp_substr(NewsTitle || ' ' || Contents, '[^ ]+', 1, level), -1) in ('은', '는', '이', '가', '와', '의', '에', '을', '를') then rtrim(regexp_substr(NewsTitle || ' ' || Contents, '[^ ]+', 1, level), substr(regexp_substr(NewsTitle || ' ' || Contents, '[^ ]+', 1, level),-1)) when substr(regexp_substr(NewsTitle || ' ' || Contents, '[^ ]+', 1, level), -2) in ('에서', '에게', '부터', '까지', '같이') then rtrim(regexp_substr(NewsTitle || ' ' || Contents, '[^ ]+', 1, level), substr(regexp_substr(NewsTitle || ' ' || Contents, '[^ ]+', 1, level),-2)) else regexp_substr(NewsTitle || ' ' || Contents, '[^ ]+', 1, level) end as sliced_words from newsDB connect by level <= regexp_count(NewsTitle || ' ' || Contents, '[^ ]+') and prior NewsTitle = NewsTitle and prior dbms_random.value is not null) B group by B.NewsTitle, B.sliced_words order by B.NewsTitle, word_cnt desc";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<SelectDTO> list = new ArrayList<SelectDTO>();
		
		try {
			conn = DriverManager.getConnection(url, userid, passward);
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				SelectDTO dto = new SelectDTO(null, null, 0);
				
				dto.setNewsTitle(rs.getString("NewsTitle"));
				dto.setSliced_Words(rs.getString("Sliced_Words"));
				dto.setWord_cnt(rs.getInt("Word_cnt"));

				list.add(dto);

			}
			
			
			return list;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error!!!");
		}finally {
			try {
				if(rs != null) {rs.close();}
				if(pstmt != null) {pstmt.close();}
				if(conn != null) {conn.close();}
			}catch(SQLException e) {
				System.out.println("Unknown Error: Channel Possibly unable to close");
			}
			
			
		}	
		
		return null;
		
	}
	
	
	public ArrayList<CrawDTO> select(){
		Connection conn = null;
		String sql = "select * from newsDB ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CrawDTO> list = new ArrayList<CrawDTO>();
		
		
		try {
			conn = DriverManager.getConnection(url, userid, passward);
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CrawDTO dto = new CrawDTO(null, null, null, null);
				
				dto.setTitle(rs.getString("NewsTitle"));
				dto.setDate(rs.getString("day"));
				dto.setContents(rs.getString("contents"));
				dto.setUrl(rs.getString("url"));
				
				list.add(dto);

			}
			
			return list;
			
		} catch (SQLException e) {
//			e.printStackTrace();
			System.out.println("Error!!!");
		}finally {
			try {
				if(rs != null) {rs.close();}
				if(pstmt != null) {pstmt.close();}
				if(conn != null) {conn.close();}
			}catch(SQLException e) {
				System.out.println("Unknown Error: Channel Possibly unable to close");
			}
			
		}
		
		return null;
		
	}
	
	
	public void insert(CrawDTO dto) {
		String sql = "insert into newsDB values(?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DriverManager.getConnection(url, userid, passward);
			pstmt = conn.prepareStatement(sql);
		
				
				pstmt.setString(1, dto.getTitle());
				pstmt.setString(2, dto.getDate());
				pstmt.setString(3, dto.getContents());
				pstmt.setString(4,  dto.getUrl());
				
				//System.out.printf("타이틀:%s\n날짜:%s\n내용:%s\n\n", o.getTitle(), o.getDate(), o.getDate());
				
				int result = pstmt.executeUpdate();
				
				if(result == 1) {
					System.out.println("성공!");
				}else {
					System.out.println("실패...");
				}
			
			
		} catch (SQLException e) {
//			e.printStackTrace();
			System.out.println("스택 오버플로우");
		
		}finally {
			try {
			if(pstmt != null) {pstmt.close();}
			if(conn != null) {conn.close();}
		}catch(SQLException e) {
			//
		}
	
		}
	
	}
	
	
	public void delete(){
		String sql = "delete from newsDB ";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DriverManager.getConnection(url, userid, passward);
			pstmt = conn.prepareStatement(sql);
			
			int result = pstmt.executeUpdate();
			
			if(result == 0) {
				System.out.println("Data Successfully Deleted");
			} else {
				System.out.println("Data cannot be Removed!");
			}
			

			
		} catch (SQLException e) {
//			e.printStackTrace();
			System.out.println("Error : Data couldn't be removed!");
		}finally {
			try {
			if(pstmt != null) {pstmt.close();}
			if(conn != null) {conn.close();}
		}catch(SQLException e) {
			//
		}
		
	}
		
	}
}