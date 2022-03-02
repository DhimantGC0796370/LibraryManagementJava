package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;

import model.Publisher;

public class PublisherDao {

	/*
	 * Creating a registerStudent method that takes student object as parameter
	 * STUDENT TBL. ID - INT FIRSTNAME - STRING LASTNAME - STRING GRADE - STRING
	 */

	public static ArrayList<Publisher> getPublishers() throws ClassNotFoundException {

		// create sql statement
		String SELECT_SQL = "SELECT * FROM Publisher";
		ArrayList<Publisher> publisherList = new ArrayList<Publisher>();

		Class.forName("com.mysql.cj.jdbc.Driver");

		try{
			
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Library", "root", ""); 
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(SELECT_SQL);
			 while(rs.next())
		     {
				 Publisher publisher = new Publisher();
				 publisher.setPub_id(rs.getInt("Pub_Id"));
				 publisher.setPub_name(rs.getString("Name"));
				 publisher.setPub_address(rs.getString("Address"));
				 publisherList.add(publisher);
		     }
		}

		catch (SQLException e) {
			System.out.print(e.getMessage());
			printSQLException(e); // calling printSQLException function...
		}
		return publisherList;
	}
	
	public static int register(Publisher pub) throws ClassNotFoundException {

		// create sql statement
		String INSERT_USER_SQL = "INSERT INTO Publisher" + "(Name, Address) VALUES " + "(?,?);";

		int result = 0;

		

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Library", "root",
					"rootpassword"); 
			PreparedStatement ps = connection.prepareStatement(INSERT_USER_SQL);
			ps.setString(1, pub.getPub_name());
			ps.setString(2, pub.getPub_address());

			System.out.println(ps);

			result = ps.executeUpdate();
		}

		catch (SQLException e) {
			System.out.print(e.getMessage());
			printSQLException(e); // calling printSQLException function...
		}
		return result;
	}

	public static int update(Publisher pub) throws ClassNotFoundException {

		// create sql statement
		String UPDATE_USER_SQL = "update Publisher " + "set Name=?, Address=? " + "where Pub_Id=?;";

		int result = 0;


		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Library", "root",
					"rootpassword"); 
			PreparedStatement ps = connection.prepareStatement(UPDATE_USER_SQL);
			ps.setInt(3, pub.getPub_id());
			ps.setString(1, pub.getPub_name());
			ps.setString(2, pub.getPub_address());

			System.out.println(ps);

			result = ps.executeUpdate();
		}

		catch (SQLException e) {
			System.out.println(e.getMessage());
			printSQLException(e); // calling printSQLException function...
		}
		return result;
	}
	/*
	 * Exception -function for printing SQL State, Error Code and Message ..
	 */

	private static void printSQLException(SQLException ex) {

		for (Throwable e : ex) {
			if (e instanceof SQLException) {

				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + ((SQLException) e).getMessage());

				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause" + t);
					t = t.getCause();
				}
			}

		}

	}

}
