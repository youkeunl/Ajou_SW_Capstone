package work.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class BoardDAO {

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String dburl = "jdbc:mysql://localhost:3306/review?useUnicode=true&amp;characterEncoding=UTF-8";
	private String dbuser = "root";
	private String dbpass = "webclass";

	


	
	public  String getDbUrl() {
		return dburl;
	}
	
	public  String getDbUser() {
		return dbuser;
	}
	
	public  String getDbPass () {
		return dbpass;
	}

	
	public Connection getConnection() {
		
		Connection con = null;
		try {
			con = DriverManager.getConnection(getDbUrl(),getDbUser(),getDbPass());			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public int insertOne(BoardDTO dto) {
	int n=0;
	String sql = "insert into board (writer, title, content, count, category, img_addr) values(?,?,?,0,?,?)";
                                                                            {
		con = getConnection();
		ps = con.prepareStatement(sql);
		
		ps.setString(1, dto.getWriter());
		ps.setString(2, dto.getTitle());
		ps.setString(3, dto.getContent());
		ps.setString(4, dto.getCategory());
		ps.setString(5, dto.getImg_addr());
	
		n = ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return n;
	}
	
	public ArrayList<BoardDTO> selectAll(String category, String search) {
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		BoardDTO dto = null;
		String sql = null;
		
				if(category.equals("all")){
				sql = "select * from board";
				try {
					
					con = getConnection();
					
					ps = con.prepareStatement(sql);
					rs = ps.executeQuery();
					
					while(rs.next()) {
						dto = new BoardDTO();
						
						dto.setNum(rs.getInt("num"));
						dto.setWriter(rs.getString("writer"));
						dto.setTitle(rs.getString("title"));
						dto.setContent(rs.getString("content"));
						dto.setCategory(rs.getString("category"));
						dto.setCount(rs.getInt("count"));
						dto.setImg_addr(rs.getString("img_addr"));
						
						list.add(dto);
					} 
				} catch(Exception e) {
					e.printStackTrace();
				} finally {
					try {
						rs.close();
						ps.close();
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
				}}
				else{
					sql = "select * from board where category = ?";
					try {
						
						con = getConnection();
						
						ps = con.prepareStatement(sql);
						ps.setString(1, category);
						rs = ps.executeQuery();
						
						while(rs.next()) {
							dto = new BoardDTO();
							
							dto.setNum(rs.getInt("num"));
							dto.setWriter(rs.getString("writer"));
							dto.setTitle(rs.getString("title"));
							dto.setContent(rs.getString("content"));
							dto.setCategory(rs.getString("category"));
							dto.setCount(rs.getInt("count"));
							dto.setImg_addr(rs.getString("img_addr"));
							
							list.add(dto);
						} 
					} catch(Exception e) {
						e.printStackTrace();
					} finally {
						try {
							rs.close();
							ps.close();
							con.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						
					}
				}
			
		
		return list;
	}
	
	public BoardDTO selectNum(int num) {
		BoardDTO dto = null;
		String sql = "select * from board where num=?";
		
		try {
			con =getConnection();
			
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, num);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				dto = new BoardDTO();
				
				dto.setNum(rs.getInt("num"));
				dto.setWriter(rs.getString("writer"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setCount(rs.getInt("count"));
				dto.setRegdate(rs.getDate("regdate"));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return dto;
	}
	
	// 전체 글 개수 가져오기
	public int getTotal() {
		int tot = 0;
		String sql = "select count(*) as count from board";
		// 총 개수 얻어내는 sql 명령문
		// count(*) 결과행의 수를 구한다
		                   
		try {
			con = getConnection();
			
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			rs.next();
			tot = rs.getInt("count");
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return tot;
	}
	
	// 전체 얻어오기 (원하는 부분만 가져오기) 
	public ArrayList<BoardDTO> selectAll(int start, int end) {
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		BoardDTO dto = null;
		
		String sql = "select * from(select rownum as r, num, writer, title, "
				+ "content, count, regdate from(select * from board order by num desc))"
				+ "where r >=? and r <=?";
		
		try {
			con = getConnection();
			
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, start);
			ps.setInt(2, end);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				dto = new BoardDTO();
				
				dto.setNum(rs.getInt("num"));
				dto.setWriter(rs.getString("writer"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setCount(rs.getInt("count"));
				dto.setRegdate(rs.getDate("regdate"));
				
				list.add(dto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return list;
	}
	
	public int updateOne(int num, String title, String content) {
		int n = 0;
		String sql = "update board set title=?, content=? where num=?";
		
		try {
			con = getConnection();
			
			ps = con.prepareStatement(sql);
			
			ps.setString(1, title);
			ps.setString(2, content);
			ps.setInt(3, num);
			
			n = ps.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return n;
	}
	
	public void countUp(int num) {
		String sql = "update board set count=count+1 where num=?";
		
		try {
			con = getConnection();
			
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, num);
			
			ps.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public int deleteOne(int num) {
		int n=0;
		String sql = "delete board where num=?";
		
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, num);
			
			n = ps.executeUpdate();

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return n;
	}
}

	
	
