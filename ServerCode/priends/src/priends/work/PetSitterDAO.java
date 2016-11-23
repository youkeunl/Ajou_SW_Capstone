package priends.work;

import java.io.IOException;
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

	public void changeAddress(String petSitterID) throws ClassNotFoundException {

		String Query = "select * from petsitter where PetSitter_ID = '" + petSitterID + "'";

		ArrayList<PetSitterDTO> petsitterList = new ArrayList<PetSitterDTO>();
		PetSitterDTO dto = null;

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

				petsitterList.add(dto);
			}

		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
		}

	}
	
	public ArrayList<PetSitterDTO> getPetSitterAllData() throws ClassNotFoundException {

		String Query = "select * from petsitter";

		ArrayList<PetSitterDTO> petsitterList = new ArrayList<PetSitterDTO>();
		PetSitterDTO dto = null;

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

				petsitterList.add(dto);
			}
			
			rs.close();
			st.close();
			con.close();

		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
		}

		return petsitterList;
	}

	public PetSitterDTO selectOne(String id) {

		PetSitterDTO dto = null;

		String Query = "select PetSitter_ID, PetSitter_Pwd ,PetSitter_name from petsitter where PetSitter_ID = '" + id
				+ "'";

		boolean checkValue = false;

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

			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return dto;
	}
	
	public int insertOne(PetSitterDTO dto){

		String Query_PetSitter = "INSERT INTO petsitter VALUES ('" + dto.getPetSitter_ID() + "', '"+ dto.getPetSitter_Pwd() +"', '" + dto.getName() + "', '"
				+ dto.getSex() + "', '" + dto.getJob() + "', '" + dto.getCareer() + "', '" + dto.getCertification() + "', '" + dto.getAge() + "', '" 
				+ dto.getCharacter() + "', '" + dto.getAddress() + "', '36.23123', '127.2312', '"+ dto.getPrice_day() + "', '" + dto.getPrice_night() + "')";

		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection con = null;

			con = DriverManager.getConnection(DB_address, DB_ID, DB_PW);

			java.sql.Statement st = con.createStatement();

			st.executeQuery(use_Query);
			
			st.execute(Query_PetSitter);

			st.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		
		return 1;
	}

}
