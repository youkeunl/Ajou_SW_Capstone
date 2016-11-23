package work.member;

import java.sql.*;
import java.util.ArrayList;





public class MemberDAO {
	
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
	
	public MemberDTO selectOne(String id){
		MemberDTO member = null;


		con = getConnection();
		String sql = null;
		if(con != null) {
			ResultSet rs = null;
			PreparedStatement ps = null;

		try{
			
			sql = "select user_id, password, name from member where user_id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs=ps.executeQuery();
			
			while(rs.next()){
				member = new MemberDTO();
				member.setId(rs.getString("user_id"));
				member.setName(rs.getString("name"));
				member.setPass(rs.getString("password"));
				
		
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				rs.close();
				ps.close();
				con.close();
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
		}
		
		return member;
	}
	
	public int insertOne(MemberDTO member){
		int n =0;
		con = getConnection();
		String sql = null;
		if(con != null) {
			
			PreparedStatement ps = null;

		try{
			
			sql = "insert into member (name,user_id,password) values(?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, member.getName());
			ps.setString(2, member.getId());
			ps.setString(3, member.getPass());
			n=ps.executeUpdate();

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				
				ps.close();
				con.close();
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
		}
		return n;
	}

}
