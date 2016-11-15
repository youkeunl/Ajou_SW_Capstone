package weka.classifiers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CallDB {

	private String petMomID = null;

	String DB_address = "jdbc:mysql://localhost";
	String DB_ID = "root";
	String DB_PW = "chang0226";
	String DB_Connection = "\"" + DB_address + "\",\"" + DB_ID + "\",\"" + DB_PW + "\"";

	public CallDB(String petMomID) {
		this.petMomID = petMomID;
	}

	public void getMatchingData() {

		String Query_1 = "select Price_Value, Distance_Value from matchlog where PetMomID = '" + petMomID + "'";

		try {
			Connection con = null;

			con = DriverManager.getConnection(DB_address, DB_ID, DB_PW);

			java.sql.Statement st = con.createStatement();

			st.executeQuery("use priends");
			ResultSet rs = st.executeQuery(Query_1);

			if (st.execute(Query_1)) {
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

		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
		}
	}

	public void insertWeight(Double[] weight) {

		String Query_2 = "select PetMomID from matchweight where PetMomID = '" + petMomID + "'";

		boolean checkValue = false;

		try {
			Connection con = null;

			con = DriverManager.getConnection(DB_address, DB_ID, DB_PW);

			java.sql.Statement st = con.createStatement();

			st.executeQuery("use priends");
			ResultSet rs = st.executeQuery(Query_2);

			if (st.execute(Query_2)) {
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

		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
		}
	}

	public ArrayList<Double> getWeightValue() {

		String Query_3 = "select price_VH, price_H, price_M, price_L, price_VL,"
				+ " distance_VH, distance_H, distance_M, distance_L, distance_VL"
				+ " from matchweight where PetMomID = '" + petMomID + "'";

		ArrayList<Double> weight = new ArrayList<Double>();

		try {
			Connection con = null;

			con = DriverManager.getConnection(DB_address, DB_ID, DB_PW);

			java.sql.Statement st = con.createStatement();

			st.executeQuery("use priends");
			ResultSet rs = st.executeQuery(Query_3);

			if (st.execute(Query_3)) {
				rs = st.getResultSet();
			}

			while (rs.next()) {
				for (int i = 0; i < 10; i++) {
					weight.add(rs.getDouble(i + 1));

				}
			}

		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
		}

		return weight;
	}

	public ArrayList<BoardDTO> getPetSitterAllData() {

		String Query_4 = "select * from petsitter";

		ArrayList<BoardDTO> petsitterList = new ArrayList<BoardDTO>();
		BoardDTO dto = null;

		try {
			Connection con = null;

			con = DriverManager.getConnection(DB_address, DB_ID, DB_PW);

			java.sql.Statement st = con.createStatement();

			st.executeQuery("use priends");
			ResultSet rs = st.executeQuery(Query_4);

			if (st.execute(Query_4)) {
				rs = st.getResultSet();
			}

			while (rs.next()) {
				dto = new BoardDTO();

				dto.setPetSitter_ID(rs.getString("PetSitter_ID"));
				dto.setName(rs.getString("PetSitter_name"));
				dto.setSex(rs.getString("PetSitter_Sex"));
				dto.setJob(rs.getString("PetSitter_Job"));
				dto.setCareer(rs.getInt("PetSitter_Career"));
				dto.setCertification(rs.getString("PetSitter_Certification"));
				dto.setAge(rs.getString("PetSitter_age"));
				dto.setCharacter(rs.getString("PetSitter_Character"));
				dto.setAddress(rs.getString("PetSitter_Address"));
				dto.setPoint_x(rs.getInt("PetSitter_MappointX"));
				dto.setPoint_y(rs.getInt("PetSitter_MappointY"));
				dto.setPrice_day(rs.getInt("Price_Day"));
				dto.setPrice_night(rs.getInt("Price_1Night"));

				petsitterList.add(dto);
			}

		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
		}

		return petsitterList;
	}

	public void Test() {

		String Query_5 = "select * from petsitter";

		try {
			Connection con = null;

			con = DriverManager.getConnection(DB_address, DB_ID, DB_PW);

			java.sql.Statement st = con.createStatement();

			st.executeQuery("use priends");

			for (int i = 3; i < 66; i++) {
				
				if(i%2 == 0){
					st.execute("INSERT INTO calender VALUES ('S" +i+ "','N','Y','N','Y','N','Y','N','Y','N','Y',"
							+ "'N','Y','N','Y','N','Y','N','Y','N','Y','N','Y','N','Y','N','Y','N','Y','N','Y','N')");
				}
				if(i%2 == 1){
					st.execute("INSERT INTO calender VALUES ('S" +i+ "','Y','N','Y','N','Y','N','Y','N','Y','N',"
							+ "'Y','N','Y','N','Y','N','Y','N','Y','N','Y','N','Y','N','Y','N','Y','N','Y','N','Y')");
				}
				
			}
			
		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
		}

	}

}