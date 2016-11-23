package priends.work;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class PetMomDAO {

	String DB_address = "jdbc:mysql://localhost/dldbrms79";
	String DB_ID = "dldbrms79";
	String DB_PW = "priends@";
	String use_Query = "use dldbrms79";

	public void getMatchingData(String petMomID) throws ClassNotFoundException {

		String Query = "select Price_Value, Distance_Value from matchlog where PetMomID = '" + petMomID + "'";

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

			try {
				BufferedWriter out = new BufferedWriter(new FileWriter("temp.txt", false));
				out.write("@RELATION temp\n" + "@ATTRIBUTE price {vh=,h=,pm=,l=,vl=}\n"
						+ "@ATTRIBUTE distance {vf=,f=,dm=,n=,vn=}\n" + "@ATTRIBUTE class {select,nonselect}\n"
						+ "@DATA\n");

				while (rs.next()) {
					out.write(rs.getString(1) + "=, " + rs.getString(2) + "=,select");
					out.write("\n");
				}

				out.write("vh=,vn=,nonselect\n" + "vh=,f=,nonselect\n" + "h=,f=,nonselect\n" + "pm=,f=,nonselect\n"
						+ "h=,dm=,nonselect\n" + "vh=,vn=,nonselect\n" + "vh=,vn=,nonselect\n" + "pm=,vf=,nonselect");

				out.close();
			} catch (Exception e) {

			}
			
			rs.close();
			st.close();
			con.close();

		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
		}
	}

	public void insertWeight(Double[] weight, String petMomID) throws ClassNotFoundException {

		String Query = "select PetMomID from matchweight where PetMomID = '" + petMomID + "'";

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

			if (rs.next()) {
				st.execute("UPDATE matchweight SET `price_VH`='" + weight[0] + "', `price_H`='" + weight[1]
						+ "', `price_M`='" + weight[2] + "', `price_L`='" + weight[3] + "', `price_VL`='" + weight[4]
						+ "', `distance_VH`='" + weight[5] + "', `distance_H`='" + weight[6] + "', `distance_M`='"
						+ weight[7] + "', `distance_L`='" + weight[8] + "', `distance_VL`='" + weight[9]
						+ "' WHERE `PetMomID`='" + petMomID + "'");

			} else {
				st.execute("INSERT INTO matchweight VALUES ('" + petMomID + "', 'S1', '" + weight[0] + "', '"
						+ weight[1] + "', '" + weight[2] + "', '" + weight[3] + "', '" + weight[4] + "', '" + weight[5]
						+ "', '" + weight[6] + "', '" + weight[7] + "', '" + weight[8] + "', '" + weight[9] + "')");
			}
			
			rs.close();
			st.close();
			con.close();

		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
		}
	}

	public ArrayList<Double> getWeightValue(String petMomID) throws ClassNotFoundException {

		String Query = "select price_VH, price_H, price_M, price_L, price_VL,"
				+ " distance_VH, distance_H, distance_M, distance_L, distance_VL"
				+ " from matchweight where PetMomID = '" + petMomID + "'";

		ArrayList<Double> weight = new ArrayList<Double>();

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
				for (int i = 0; i < 10; i++) {
					weight.add(rs.getDouble(i + 1));

				}
			}
			
			rs.close();
			st.close();
			con.close();

		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
		}

		return weight;
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

	public Double[] getMappointData(String petMomID) throws ClassNotFoundException {

		String Query = "select PetMom_MappointX, PetMom_MappointY from petmom where PetMomID = '" + petMomID + "'";

		Double[] mapPoint = new Double[2];

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
				mapPoint[0] = rs.getDouble(1);
				mapPoint[1] = rs.getDouble(2);
			}
			
			rs.close();
			st.close();
			con.close();

		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
		}

		return mapPoint;
	}

	public PetMomDTO selectOne(String id) {

		PetMomDTO dto = null;

		String Query = "select PetMom_ID, PetMom_Pwd ,PetMom_name from petmom where PetMom_ID = '" + id + "'";

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
				dto = new PetMomDTO();
				dto.setPetMom_ID(rs.getString("PetMom_ID"));
				dto.setPetMom_Pwd(rs.getString("PetMom_Pwd"));
				dto.setName(rs.getString("PetMom_name"));

			}
			
			rs.close();
			st.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return dto;
	}
	
	public int insertOne(PetMomDTO dto, PetDTO p_dto){

		String Query_PetMom = "INSERT INTO petmom VALUES ('" + dto.getPetMom_ID() + "', '"+ dto.getPetMom_Pwd() +"', '" + dto.getName() + "', '"
				+ dto.getSex() + "', '" + dto.getJob() + "', '" + dto.getAge() + "', '" + dto.getCharacter() + "', '" + dto.getCaution()
				+ "', '" + dto.getAddress() + "', '36.23123', '127.2312')";
		
		String Query_Pet = "INSERT INTO pets VALUES ('" + p_dto.getPet_ID() + "', '" + p_dto.getName() + "', '" + p_dto.getSex() + "', '"
				+ p_dto.getType() + "', '" + p_dto.getSpecies() + "', '" + p_dto.getAge() + "', '" + p_dto.getCharacter() + "', '" + p_dto.getHealth()
				+ "', '" + p_dto.getCaution() + "', '" + p_dto.getOwner_PetSitterID() + "', '" + p_dto.getOwner_PetMomID() + "')";

		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection con = null;

			con = DriverManager.getConnection(DB_address, DB_ID, DB_PW);

			java.sql.Statement st = con.createStatement();

			st.executeQuery(use_Query);
			
			st.execute(Query_PetMom);
			st.execute(Query_Pet);

			st.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		
		return 1;
	}

}