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

	//String DB_address = "jdbc:mysql://localhost/priends";
	//String DB_ID = "root";
	//String DB_PW = "chang0226";
	//String use_Query = "use priends";

	public void getMatchingData(String petMomID) throws ClassNotFoundException {

		String Query = "select Price_Value, Distance_Value, Recommend_Value from matchlog where PetMomID = '" + petMomID
				+ "'";
		
		ForCalculate cal = new ForCalculate();
		ArrayList<String[]> nonselect_values = new ArrayList<String[]>();
		
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
				BufferedWriter out = new BufferedWriter(
						new FileWriter("/home/hosting_users/dldbrms79/www/temp.txt", false));

				out.write("@RELATION temp\n" + "@ATTRIBUTE price {vh=,h=,pm=,l=,vl=}\n"
						+ "@ATTRIBUTE distance {vf=,f=,dm=,n=,vn=}\n" + "@ATTRIBUTE recommend {vg=,g=,rm=,l=,vl=}\n"
						+ "@ATTRIBUTE class {select,nonselect}\n" + "@DATA\n");

				String [] nonselect = new String[3];
				String price = "";
				String distance = "";
				String recommend = "";
				
				while (rs.next()) {
					
					price = rs.getString(1);
					distance = rs.getString(2);
					recommend = rs.getString(3);
					
					out.write(price + "=, " + distance + "=, " + recommend + "=,select");
					out.write("\n");
					
					nonselect = cal.symmetry_Value(price, distance, recommend);
					nonselect_values.add(nonselect);
				}
				
				if(nonselect_values.size() == 1){
					out.write(price + "=, " + distance + "=, " + recommend + "=,select");
					out.write("\n");
					out.write(nonselect_values.get(0)[0] + "=, " + nonselect_values.get(0)[1] + "=, " + nonselect_values.get(0)[2] + "=,nonselect");
					out.write("\n");
				}

				for(int i = 0; i < nonselect_values.size(); i++){
					out.write(nonselect_values.get(i)[0] + "=, " + nonselect_values.get(i)[1] + "=, " + nonselect_values.get(i)[2] + "=,nonselect");
					out.write("\n");
				}

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

	public String insertWeight(Double[] weight, String petMomID) throws ClassNotFoundException {

		String Query = "select PetMomID from matchweight where PetMomID = '" + petMomID + "'";

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
						+ "', `recommend_VG`='" + weight[10] + "', `recommend_G`='" + weight[11] + "', `recommend_M`='"
						+ weight[12] + "', `recommend_L`='" + weight[13] + "', `recommend_VL`='" + weight[14]
						+ "' WHERE `PetMomID`='" + petMomID + "'");

			} else {
				st.execute("INSERT INTO matchweight VALUES ('" + petMomID + "', '" + weight[0] + "', '"
						+ weight[1] + "', '" + weight[2] + "', '" + weight[3] + "', '" + weight[4] + "', '" + weight[5]
						+ "', '" + weight[6] + "', '" + weight[7] + "', '" + weight[8] + "', '" + weight[9] + "', '"
						+ weight[10] + "', '" + weight[11] + "', '" + weight[12] + "', '" + weight[13] + "', '"
						+ weight[14] + "')");
			}

			rs.close();
			st.close();
			con.close();

		} catch (SQLException sqex) {
			String s = sqex.getMessage();
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
			return s;
		}
		
		return "";
	}

	public ArrayList<Double> getWeightValue(String petMomID) throws ClassNotFoundException {

		boolean existWeight = false;
		
		String Query = "select price_VH, price_H, price_M, price_L, price_VL,"
				+ " distance_VH, distance_H, distance_M, distance_L, distance_VL, recommend_VG, recommend_G, recommend_M, recommend_L, recommend_VL"
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
				existWeight = true;
				for (int i = 0; i < 15; i++) {
					weight.add(rs.getDouble(i + 1));

				}
			}
			if(existWeight == false){
				for(int i = 0; i < 15; i++){
					weight.add(0.0);
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

	public ArrayList<PetSitterDTO> getPetSitterAllData(String bitstring, int Start_Date, int End_Date) throws ClassNotFoundException {

		String Query;
		
		if(bitstring.equals("none")){
			Query = "select * from petsitter";
		}else{
			Query = "select * from petsitter, service where petsitter.PetSitter_ID = service.PetSitter_ID and service.bitstring = '"
					+ bitstring + "'";
		}

		ArrayList<PetSitterDTO> petsitterList = new ArrayList<PetSitterDTO>();
		PetSitterDTO dto = null;

		CalenderDAO calender = new CalenderDAO();
		
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
				dto.setRecommend(rs.getDouble("PetSitter_Recommend"));
				dto.setImage(rs.getString("PetSitter_image"));
				dto.setHome1(rs.getString("PetSitter_home1"));
				dto.setHome2(rs.getString("PetSitter_home2"));
				dto.setHome3(rs.getString("PetSitter_home3"));
				dto.setCalender(calender.make_Calender(dto.getPetSitter_ID()));
				
				boolean calender_check = true;
				for(int i = Start_Date; i <= End_Date; i++){
					if(dto.getCalender()[i].equals("N")){
						calender_check = false;
						break;
					}
				}
				
				if(calender_check == true){
					petsitterList.add(dto);
				}
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

	public Double[] getMappointData(String petMomID) {

		String Query = "select PetMom_MappointX, PetMom_MappointY from petmom where PetMom_ID = '" + petMomID + "'";

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
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return mapPoint;
	}

	public PetMomDTO selectOne(String id) {

		PetMomDTO dto = null;

		String Query = "select * from petmom where PetMom_ID = '" + id + "'";

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
				dto.setSex(rs.getString("PetMom_Sex"));
				dto.setJob(rs.getString("PetMom_Job"));
				dto.setAge(rs.getInt("PetMom_age"));
				dto.setCharacter(rs.getString("PetMom_Character"));
				dto.setCaution(rs.getString("PetMom_Caution"));
				dto.setAddress(rs.getString("PetMom_Address"));
				dto.setPoint_x(rs.getDouble("PetMom_MappointX"));
				dto.setPoint_y(rs.getDouble("PetMom_MappointY"));
				dto.setImage(rs.getString("PetMom_image"));
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

		return dto;
	}

	public int insertOne(PetMomDTO dto, PetDTO p_dto) throws ClassNotFoundException {

		PetMomDAO dao = new PetMomDAO();
		int count = dao.countPets();
		count++;

		Geocoding geocoding = new Geocoding();
		Float[] mapPoint = geocoding.geoCoding(dto.getAddress());
		if (mapPoint == null) {
			mapPoint[0] = 37.283363f;
			mapPoint[1] = 127.04651f;
		}

		String Query_PetMom = "INSERT INTO petmom VALUES ('" + dto.getPetMom_ID() + "', '" + dto.getPetMom_Pwd()
				+ "', '" + dto.getName() + "', '" + dto.getSex() + "', '" + dto.getJob() + "', '" + dto.getAge()
				+ "', '' , '' , '" + dto.getAddress() + "', '" + mapPoint[0] + "', '" + mapPoint[1] + "','')";

		String Query_Pet = "INSERT INTO pets VALUES ('" + p_dto.getName() + "', '" + count + "', '', '"
				+ p_dto.getType() + "', '" + p_dto.getSpecies() + "', '" + p_dto.getAge() + "', '"
				+ p_dto.getCharacter() + "', '" + p_dto.getHealth() + "', '', '" + dto.getPetMom_ID() + "','','','','10','')";

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

	public int countPets() {

		String Query = "select count(*) from pets";
		int count = 0;

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
				count = rs.getInt(1);
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

		return count;
	}

	public int insertRequest_info(String petMomID, String petSitterID, String Start_Date, String End_Date) {

		String Query = "update pets set Match_PetSitterID = '" + petSitterID + "', Start_Date = '" + Start_Date
				+ "', End_Date = '" + End_Date + "', State = '0' where Owner_PetMomID = '" + petMomID + "'";

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

	public ArrayList<PetDTO> getPets_info(String petMom_ID) {

		ArrayList<PetDTO> petList = new ArrayList<PetDTO>();
		PetDTO dto = null;

		String Query = "select * from pets where Owner_PetMomID = '" + petMom_ID + "'";

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

				petList.add(dto);
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

		return petList;
	}

	public ArrayList<PetDTO> getRespond_info(String petMom_ID) {

		ArrayList<PetDTO> respondList = new ArrayList<PetDTO>();
		PetDTO dto;

		String Query = "select distinct Match_PetSitterID, State from pets where Owner_PetMomID = '" + petMom_ID + "'";

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
				
				dto.setOwner_PetMomID(petMom_ID);
				dto.setMatch_PetSitterID(rs.getString(1));
				dto.setState(rs.getInt(2));
				
				respondList.add(dto);
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

		return respondList;
	}
	
	public String insertMatchlog(String petMomID, String petSitterID) {

		PetSitterDAO s_dao = new PetSitterDAO();
		PetMomDAO m_dao = new PetMomDAO();
		PetSitterDTO s_dto = s_dao.selectOne(petSitterID);
		PetMomDTO m_dto = m_dao.selectOne(petMomID);
		ForCalculate cal = new ForCalculate();
		
		String price_value = cal.classifier_Price(s_dto.getPrice_day());
		String distance_value = cal.classifier_Distance(cal.distance(m_dto.getPoint_x(), m_dto.getPoint_y(), s_dto.getPoint_x() ,s_dto.getPoint_y()));
		String recommend_value = cal.classifier_Recommend(s_dto.getRecommend());
		
		String Start_Date = null;
		String End_Date = null;
		int Payment = 0;
		
		String Query1 = "select distinct Start_Date, End_Date from pets "
				+ "where Owner_PetMomID = '"+ petMomID +"' and"
				+ " Match_PetSitterID = '"+ petSitterID +"'";
		
		String Query2 = "select PetMomID, PetSitterID from matchlog where PetMomID = '" + petMomID + "' and PetSitterID = '" + petSitterID + "'";
		
		String Query3 = "update pets set State = '2' where Owner_PetMomID = '" + petMomID + "' " + "and Match_PetSitterID = '" + petSitterID + "'";


		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection con = null;

			con = DriverManager.getConnection(DB_address, DB_ID, DB_PW);

			java.sql.Statement st = con.createStatement();

			st.executeQuery(use_Query);
			ResultSet rs = st.executeQuery(Query1);

			if (st.execute(Query1)) {
				rs = st.getResultSet();
			}

			while (rs.next()) {
				Start_Date = rs.getString(1);
				End_Date = rs.getString(2);
			}
			
			rs = st.executeQuery(Query2);

			if (st.execute(Query2)) {
				rs = st.getResultSet();
			}

			if (rs.next()) {
				st.execute("UPDATE matchlog SET `Price_Value`='" + price_value + "', `Distance_Value`='" + distance_value
						+ "', `Recommend_Value`='" + recommend_value + "', `Start_Date`='" + Start_Date + "', `End_Date`='" + End_Date 
						+ "' where PetMomID = '"+petMomID+"' and PetSitterID = '"+petSitterID+"'");

			} else {
				st.execute("INSERT INTO matchlog VALUES ('"+ petMomID +"', '"+ petSitterID +"', '" + price_value + "', '"
						+ distance_value + "', '" + recommend_value + "', '" + Start_Date + "', '"
						+ End_Date + "','', '0', '', '')");
			}
			
			st.executeUpdate(Query3);
			
			rs.close();
			st.close();
			con.close();
			
		} catch (SQLException sqex) {
			String s = "SQLException: " + sqex.getMessage();
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
			return s;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return "";
	}
	
	public int deleteReject_info(String petMomID, String petSitterID) {

		String Query = "update pets set Match_PetSitterID = '', State = '-2', "
				+ " Start_Date = '', End_Date = '' where Owner_PetMomID = '" + petMomID + "' "
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
}