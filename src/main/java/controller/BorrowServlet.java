package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BorrowDao;
import model.Borrow;

/**
 * @author dhimantgodhani Servlet implementation class BorrowServlet This
 *         servlet will list borrow book details.
 */
@WebServlet("/BorrowServlet")
public class BorrowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BorrowDao bdao; // connection with database for borrow

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BorrowServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response) This method will get the data of borrow in list of array and
	 *      forward the request to borrow.jsp
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<Borrow> borrow = new ArrayList<Borrow>();
		try {
			borrow = bdao.getBorrowDetails(); // get data for borrow book from dao.
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("borrow", borrow);
		response.setContentType("text/html;charset=UTF-8");
		request.getRequestDispatcher("Borrow.jsp").forward(request, response); // create a request dispatcher object to
																				// Borrow.jsp
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
