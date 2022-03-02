package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Books;
import model.Publisher;

public class BookDao {

	
	public static ArrayList<Books> getBooks() throws ClassNotFoundException {

		// create sql statement
		String SELECT_SQL = "SELECT * FROM Books";
		ArrayList<Books> bookList = new ArrayList<Books>();

		Class.forName("com.mysql.jdbc.Driver");

		try{
			
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Library", "root", ""); 
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(SELECT_SQL);
			 while(rs.next())
		     {
				 Books book = new Books();
				 book.setBook_id(rs.getInt("Book_Id"));
				 book.setAuthor(rs.getString("Author"));
				 book.setAvailable(rs.getBoolean("Available"));
				 book.setPrice(rs.getInt("Price"));
				 book.setTitle(rs.getString("Title"));
				
				 bookList.add(book);
		     }
		}

		catch (SQLException e) {
			System.out.print(e.getMessage());
			printSQLException(e); // calling printSQLException function...
		}
		return bookList;
	}
	
	
	
	/*
	 * Creating a registerStudent method that takes student object as parameter
	 * STUDENT TBL. ID - INT FIRSTNAME - STRING LASTNAME - STRING GRADE - STRING
	 */
	
	
	public static int update(Books book) throws ClassNotFoundException {

		// create sql statement
		String UPDATE_BOOK_SQL = "update Books " + "set Author=?, Title=?, Price=?, Available=? " + "where Book_Id=?;";

		int result = 0;
		
		Class.forName("com.mysql.cj.jdbc.Driver");


		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Library", "root", "Hooda@1130"); 
			PreparedStatement ps = connection.prepareStatement(UPDATE_BOOK_SQL);
			ps.setString(1, book.getAuthor());
			ps.setString(2, book.getTitle());
			ps.setInt(3, book.getPrice());
			ps.setBoolean(4, book.isAvailable());
			ps.setInt(5, book.getBook_id());

			System.out.println(ps);

			result = ps.executeUpdate();
		}

		catch (SQLException e) {
			System.out.println(e.getMessage());
			printSQLException(e); // calling printSQLException function...
		}
		return result;
	}
	
	public static int delete(Books book) throws ClassNotFoundException {

		// create sql statement
		String DELETE_BOOK_SQL = "delete from Books " + "where Book_Id=?;";

		int result = 0;


		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Library", "root", "Hooda@1130"); 
			PreparedStatement ps = connection.prepareStatement(DELETE_BOOK_SQL);
			ps.setInt(1, book.getBook_id());

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
		 *  Exception -function for printing SQL State, Error Code and Message .. 
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
