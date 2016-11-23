package work.sitter;

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



public class SitterDAO {

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String dburl = "jdbc:mysql://uj64-015.cafe24.com/WebMysql/priends?useUnicode=true&amp;characterEncoding=UTF-8";
	private String dbuser = "dldbrms79";
	private String dbpass = "priends@";

	/*private String dburl = "jdbc:mysql://localhost:3306/priends?useUnicode=true&amp;characterEncoding=UTF-8";
	private String dbuser = "root";
	private String dbpass = "root";
	*/
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
	
	
	// 전체 얻어오기 (원하는 부분만 가져오기) 
	public ArrayList<SitterDTO> selectAll() {
		ArrayList<SitterDTO> list = new ArrayList<SitterDTO>();
		SitterDTO dto = null;
		
		String sql = "select * from petsitter";
		
		try {
			con = getConnection();
			
			ps = con.prepareStatement(sql);
			
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				dto = new SitterDTO();
				
				dto.setName(rs.getString("PetSitter_name"));
				dto.setAge(rs.getInt("PetSitter_age"));
				dto.setAddress(rs.getString("PetSitter_Address"));
				dto.setPrice_day(rs.getInt("Price_Day"));
				dto.setPrice_night(rs.getInt("Price_1Night"));
				
				
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
	

}

	
	
