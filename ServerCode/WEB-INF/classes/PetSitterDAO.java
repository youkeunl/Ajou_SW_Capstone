package priends.work;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PetSitterDAO {

	String DB_address = "jdbc:mysql://localhost/dldbrms79";
	String DB_ID = "dldbrms79";
	String DB_PW = "priends@";
	String use_Query = "use dldbrms79";
	
	//String DB_address = "jdbc:mysql://localhost/priends";
	//String DB_ID = "root";
	//String DB_PW = "chang0226";
	//String use_Query = "use priends";

	public PetSitterDTO selectOne(String id) {
		
		PetSitterDTO dto = null;

		String Query = "select * from petsitter where PetSitter_ID = '" + id + "'";

		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection con = null;

			con = DriverManager.getConnection(DB_address, DB_ID, DB_PW);

			java.sql.Statement st = con.createStatement();

			st.executeQuery(use_Query);
			ResultSet rs = st.executeQuery(Query);

			if (st.execute(Query)) {
				rs = st.getResultSet();
			}

			while (rs.next()) {
				dto = new PetSitterDTO();
				dto.setPetSitter_ID(rs.getString("PetSitter_ID"));
				dto.setPetSitter_Pwd(rs.getString("PetSitter_Pwd"));
				dto.setName(rs.getString("PetSitter_name"));
				dto.setSex(rs.getString("PetSitter_Sex"));
				dto.setJob(rs.getString("PetSitter_Job"));
				dto.setCareer(rs.getInt("PetSitter_Career"));
				dto.setCertification(rs.getString("PetSitter_Certification"));
				dto.setAge(rs.getInt("PetSitter_age"));
				dto.setCharacter(rs.getString("PetSitter_Character"));
				dto.setAddress(rs.getString("PetSitter_Address"));
				dto.setPoint_x(rs.getDouble("PetSitter_MappointX"));
				dto.setPoint_y(rs.getDouble("PetSitter_MappointY"));
				dto.setPrice_day(rs.getInt("Price_Day"));
				dto.setPrice_night(rs.getInt("Price_1Night"));
				dto.setRecommend(rs.getDouble("PetSitter_Recommend"));
				dto.setImage(rs.getString("PetSitter_image"));
				dto.setHome1(rs.getString("PetSitter_home1"));
				dto.setHome2(rs.getString("PetSitter_home2"));
				dto.setHome3(rs.getString("PetSitter_home3"));
			}

		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return dto;
	}
	
	public int insertOne(PetSitterDTO dto) {

		Geocoding geocoding = new Geocoding();
		Float[] mapPoint = geocoding.geoCoding(dto.getAddress());
		if(mapPoint == null){
			mapPoint[0] = 37.283363f;
			mapPoint[1] = 127.04651f;
		}
		
		String Query_PetSitter = "INSERT INTO petsitter VALUES ('" + dto.getPetSitter_ID() + "', '"+ dto.getPetSitter_Pwd() +"', '" + dto.getName() + "', '"
				+ dto.getSex() + "', '" + dto.getJob() + "', '0', '', '" + dto.getAge() + "', '' , '" + dto.getAddress() + "', '" + mapPoint[0] + "', '" + mapPoint[1] + "', '"
				+ dto.getPrice_day() +"', '"+ dto.getPrice_night() +"','0','','','','')";

		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection con = null;

			con = DriverManager.getConnection(DB_address, DB_ID, DB_PW);

			java.sql.Statement st = con.createStatement();

			st.executeQuery(use_Query);
			
			st.execute(Query_PetSitter);

			st.close();
			con.close();

		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
			return -1;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return 1;
	}
	
	public ArrayList<String> getRequest_info(String petSitter_ID){
		
		ArrayList<String> petmomList = new ArrayList<String>();
		
		String Query = "select distinct Owner_PetMomID from pets where Match_PetSitterID = '"+ petSitter_ID +"' and State = '0'";

		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection con = null;

			con = DriverManager.getConnection(DB_address, DB_ID, DB_PW);

			java.sql.Statement st = con.createStatement();

			st.executeQuery(use_Query);
			ResultSet rs = st.executeQuery(Query);

			if (st.execute(Query)) {
				rs = st.getResultSet();
			}

			while (rs.next()) {
				petmomList.add(rs.getString(1));
			}

		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return petmomList;
	}
	
	public int updateAccept_info(String petMomID, String petSitterID) {

		String Query = "update pets set State = '1' where Owner_PetMomID = '" + petMomID + "' "
				+ "and Match_PetSitterID = '" + petSitterID + "'";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection con = null;

			con = DriverManager.getConnection(DB_address, DB_ID, DB_PW);

			java.sql.Statement st = con.createStatement();

			st.executeQuery(use_Query);

			st.executeUpdate(Query);

			st.close();
			con.close();

		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
			return -1;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return -1;
		}

		return 1;
	}
	
	public int updateReject_info(String petMomID, String petSitterID) {

		String Query = "update pets set State = '-1' where Owner_PetMomID = '" + petMomID + "' "
				+ "and Match_PetSitterID = '" + petSitterID + "'";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection con = null;

			con = DriverManager.getConnection(DB_address, DB_ID, DB_PW);

			java.sql.Statement st = con.createStatement();

			st.executeQuery(use_Query);

			st.executeUpdate(Query);

			st.close();
			con.close();

		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
			return -1;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return -1;
		}

		return 1;
	}
	
	public ArrayList<PetDTO> getReport_info(String petSitter_ID) {

		ArrayList<PetDTO> reportList = new ArrayList<PetDTO>();
		PetDTO dto;

		String Query = "select * from pets where Match_PetSitterID = '" + petSitter_ID + "' and (State = '2' or State = '3')";

		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection con = null;

			con = DriverManager.getConnection(DB_address, DB_ID, DB_PW);

			java.sql.Statement st = con.createStatement();

			st.executeQuery(use_Query);
			ResultSet rs = st.executeQuery(Query);

			if (st.execute(Query)) {
				rs = st.getResultSet();
			}

			while (rs.next()) {
				dto = new PetDTO();
				
				dto.setName(rs.getString("Pet_name"));
				dto.setPet_ID(rs.getInt("Pet_ID"));
				dto.setSex(rs.getString("Pet_Sex"));
				dto.setType(rs.getString("Pet_type"));
				dto.setSpecies(rs.getString("Pet_Species"));
				dto.setAge(rs.getInt("Pet_age"));
				dto.setCharacter(rs.getString("Pet_character"));
				dto.setHealth(rs.getString("Pet_Health"));
				dto.setCaution(rs.getString("PetMom_Caution"));
				dto.setOwner_PetMomID(rs.getString("Owner_PetMomID"));
				dto.setMatch_PetSitterID(rs.getString("Match_PetSitterID"));
				dto.setStart_Date(rs.getString("Start_Date"));
				dto.setEnd_Date(rs.getString("End_Date"));
				dto.setState(rs.getInt("State"));
				dto.setImage(rs.getString("Pet_image"));
				
				reportList.add(dto);
			}
			
			rs.close();
			st.close();
			con.close();

		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return reportList;
	}
	
	public int updateState_Report(String petMomID, String petSitterID, int id) {

		String Query = "update pets set State = '3' where Owner_PetMomID = '" + petMomID + "' "
				+ "and Match_PetSitterID = '" + petSitterID + "' and Pet_ID = '"+ id +"'";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection con = null;

			con = DriverManager.getConnection(DB_address, DB_ID, DB_PW);

			java.sql.Statement st = con.createStatement();

			st.executeQuery(use_Query);

			st.executeUpdate(Query);

			st.close();
			con.close();

		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
			return -1;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return -1;
		}

		return 1;
	}
	
	public int deleteState_Report(String petMomID, String petSitterID, int id) {

		String Query = "update pets set Match_PetSitterID = '', Start_Date = '', End_Date = '', State = '10'  where Owner_PetMomID = '" + petMomID + "' "
				+ "and Match_PetSitterID = '" + petSitterID + "' and Pet_ID = '"+ id +"'";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection con = null;

			con = DriverManager.getConnection(DB_address, DB_ID, DB_PW);

			java.sql.Statement st = con.createStatement();

			st.executeQuery(use_Query);

			st.executeUpdate(Query);

			st.close();
			con.close();

		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
			return -1;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return -1;
		}

		return 1;
	}

}
