package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Borrow;

public class BorrowDao {

	/*
	 * Creating a registerStudent method that takes student object as parameter
	 * STUDENT TBL. ID - INT FIRSTNAME - STRING LASTNAME - STRING GRADE - STRING
	 */

	public static int issue(Borrow borrow) throws ClassNotFoundException {
		
		// create sql statement 
		String INSERT_USER_SQL = "INSERT INTO Borrow_By" +
				"(Book_Id, Member_Id, Issue_Date, Return_Date, Due_Date) VALUES " +
				"(?,?,?,?,?);";
		
		int result = 0;
		
		Class.forName("com.mysql.jdbc.Driver");
		
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "");
		PreparedStatement ps = connection.prepareStatement(INSERT_USER_SQL)){
			
						
			ps.setInt(1, borrow.getBook_id());
			ps.setInt(2, borrow.getMember_id());
			ps.setString(3, borrow.getIssue_date());
			ps.setString(4, borrow.getReturn_date());
			ps.setString(5, borrow.getDue_date());
			
			
			System.out.println(ps);
				
			result = ps.executeUpdate();
		}
				
		catch (SQLException e) {
			System.out.print(e.getMessage());
			printSQLException(e);  // calling printSQLException function...
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