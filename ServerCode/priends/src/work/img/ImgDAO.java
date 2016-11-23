package work.img;

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

import work.board.BoardDTO;
import work.member.MemberDTO;

public class ImgDAO {

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
	
	public int insertOne(ImgDTO dto) {
		int n = 0;
		
		String sql = "insert into img (info, link, addr) values("
				+ " ?, ?, ?)";
		try {
			con = getConnection();
			ps =con.prepareStatement(sql);
			
			ps.setString(1, dto.getInfo());
			ps.setString(2, dto.getLink());
			ps.setString(3, dto.getAddr());
			
			n = ps.executeUpdate();

			ps.close();
			con.close();
		} catch(Exception e) {
			e.printStackTrace();
		} 
		return n;
	}
	
	public ArrayList<ImgDTO> selectAll() {
		ArrayList<ImgDTO> list = new ArrayList<ImgDTO>();
		ImgDTO dto = null;
		
		String sql = "select * from img order by num desc";
		
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				dto = new ImgDTO();
				
				dto.setNum(rs.getInt("num"));
				dto.setInfo(rs.getString("info"));
				dto.setLink(rs.getString("link"));
				
				list.add(dto);
				
			}
			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}
	
	public ImgDTO selectOne(String link) {
		
		ImgDTO dto = null;
		
		String sql = "select * from img where link = ?";
		
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, link);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				dto = new ImgDTO();
				
				dto.setNum(rs.getInt("num"));
				dto.setInfo(rs.getString("info"));
				dto.setLink(rs.getString("link"));
			
				
			}
			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return dto;
	}
	
	public String getFileAddress(int num) {
		String str = "";
		String sql = "select * from img where num=?";
		
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, num);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				str = rs.getString("addr");
			}
			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	
	public String getLink(int num) {
		String str = "";
		String sql = "select * from img where num=?";
		
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, num);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				str = rs.getString("link");
			}
			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	
	public int deleteOne(int num) {
		int r = 0;
		String sql = "delete from img where num= ?";
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, num);
			
			r = ps.executeUpdate();
			
			ps.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}
}













