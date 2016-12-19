package priends.work;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CalenderDAO {

	String DB_address = "jdbc:mysql://localhost/dldbrms79"; 
	String DB_ID = "dldbrms79"; 
	String DB_PW = "priends@"; 
	String use_Query = "use dldbrms79";
	
	public String[] make_Calender(String petsitterID){
		
		String Query = "select * from calender where PetSitterID = '" + petsitterID + "'";

		String [] calender = new String[32];

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
				for(int i = 1; i <= 31; i++){
					calender[i] = rs.getString(i+1);
				}
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

		return calender;
	}
	
	public String update_calender(String [] date, String petsitterID){
		
		String s = "";
		for(int i = 0; i < date.length; i++){
			if(i == date.length-1){
				s = s + "`201612" + date[i] + "` = 'N' ";
			}else{
				s = s + "`201612" + date[i] + "` = 'N' , ";
			}
		}
		String Query = "update calender set " + s +" where PetSitterID = '"+petsitterID+"'";

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
			String error = sqex.getMessage();
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
			return error;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return Query;
	}
	
	public String update_calenderToY(String [] date, String petsitterID){
		
		String s = "";
		for(int i = 0; i < date.length; i++){
			if(i == date.length-1){
				s = s + "`201612" + date[i] + "` = 'Y' ";
			}else{
				s = s + "`201612" + date[i] + "` = 'Y' , ";
			}
		}
		String Query = "update calender set " + s +" where PetSitterID = '"+petsitterID+"'";

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
			String error = sqex.getMessage();
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
			return error;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return Query;
	}
	
public String updatecalender(String [] date, String petsitterID){
		
		String s = "";
		for(int i = 0; i < date.length; i++){
			String j = Integer.toString(i+1);
			if(i < 9){
				j = "0" + j;
			}
			if(i == date.length-1){
				s = s + "`201612" + j + "` = '"+date[i]+"' ";
			}else{
				s = s + "`201612" + j + "` = '"+date[i]+"' , ";
			}
		}
		String Query = "update calender set " + s +" where PetSitterID = '"+petsitterID+"'";

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
			String error = sqex.getMessage();
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
			return error;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return Query;
	}
	
}
