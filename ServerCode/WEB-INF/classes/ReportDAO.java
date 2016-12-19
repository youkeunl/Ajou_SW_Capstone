package priends.work;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReportDAO {

	String DB_address = "jdbc:mysql://localhost/dldbrms79"; 
	String DB_ID = "dldbrms79"; 
	String DB_PW = "priends@"; 
	String use_Query = "use dldbrms79";

	//String DB_address = "jdbc:mysql://localhost/priends";
	//String DB_ID = "root";
	//String DB_PW = "chang0226";
	//String use_Query = "use priends";
	
	public ArrayList<ReportDTO> getAllReport (String userID, String type) throws ClassNotFoundException {

		String Query;
		
		if(type.equals("mom")){
			Query = "select * from report where PetMom_ID = '"+ userID +"'";
		}else{
			Query = "select * from report where PetSitter_ID = '"+ userID +"'";
		}

		ArrayList<ReportDTO> reportList = new ArrayList<ReportDTO>();
		ReportDTO dto = null;
		
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
				dto = new ReportDTO();
				
				dto.setNumber(rs.getInt("Report_number"));
				dto.setPetSitter_ID(rs.getString("PetSitter_ID"));
				dto.setPetMom_ID(rs.getString("PetMom_ID"));
				dto.setEar(rs.getInt("ear"));
				dto.setEye(rs.getInt("eye"));
				dto.setTeeth(rs.getInt("teeth"));
				dto.setSkin(rs.getInt("skin"));
				dto.setDung(rs.getInt("dung"));
				dto.setEar_script(rs.getString("ear_script"));
				dto.setEye_script(rs.getString("eye_script"));
				dto.setTeeth_script(rs.getString("teeth_script"));
				dto.setSkin_script(rs.getString("skin_script"));
				dto.setDung_script(rs.getString("dung_script"));
				dto.setState(rs.getInt("State"));
				dto.setPet_name(rs.getString("Pet_name"));
				dto.setPet_sex(rs.getString("Pet_sex"));

				reportList.add(dto);
			}

			rs.close();
			st.close();
			con.close();

		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
		}

		return reportList;
	}
	
	public String insertReport(ReportDTO dto) throws ClassNotFoundException {

		ReportDAO dao = new ReportDAO();
		int count = dao.countReports();
		count++;
		
		String Query = "INSERT INTO report VALUES ('" + count + "', '" + dto.getPetSitter_ID() + "', '"
				+ dto.getPetMom_ID() + "', '" + dto.getEar() + "', '" + dto.getEye() + "', '" + dto.getTeeth() + "', '" + dto.getSkin()
				+ "', '" + dto.getDung() + "', '" + dto.getEar_script() + "', '" + dto.getEye_script() + "', '" + dto.getTeeth_script() + "', '"
				+ dto.getSkin_script() + "', '" + dto.getDung_script() + "', '" + dto.getState() + "','"+dto.getPet_name()+"','"+dto.getPet_sex()+"')";

		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection con = null;

			con = DriverManager.getConnection(DB_address, DB_ID, DB_PW);

			java.sql.Statement st = con.createStatement();

			st.execute(Query);

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
	
	public int countReports() {

		String Query = "select count(*) from report";
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
}
