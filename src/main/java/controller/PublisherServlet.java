package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PublisherDao;
import model.Publisher;

/**
 * @author dhimantgodhani Servlet implementation class PublisherServlet This
 *         servlet will add, update and list publisher details.
 */
@WebServlet("/PublisherServlet")
public class PublisherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PublisherDao pdao; // connection with database for publisher

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PublisherServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response) This method will get the data of publisher in list of array
	 *      and forward the request to publisher.jsp
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<Publisher> publisher = new ArrayList<Publisher>();
		try {
			publisher = pdao.getPublishers(); // get data for publishers from dao.
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		request.setAttribute("publisher", publisher);
		response.setContentType("text/html;charset=UTF-8");
		request.getRequestDispatcher("Publisher.jsp").forward(request, response); // create a request dispatcher object
																					// to publisher.jsp
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response) This method will create or update the data of publisher and
	 *      forward the request to PublisherServlet
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("register") != null) { // create a new publisher
			String pubName = request.getParameter("pubName");
			String pubAddress = request.getParameter("pubAddress");

			Publisher pub = new Publisher();

			pub.setPub_name(pubName);
			pub.setPub_address(pubAddress);

			try {
				pdao.register(pub); // send data for publishers to dao.
			} catch (Exception e) {
				System.out.print(e.getMessage());
				e.printStackTrace();
			}
			response.sendRedirect("PublisherServlet");// response goes to PublisherServlet!!

		} else if (request.getParameter("update") != null) { // update an existing publisher
			String pubName = request.getParameter("pubName");
			String pub_Id = request.getParameter("pubId");
			int pubId = Integer.parseInt(pub_Id);
			String pubAddress = request.getParameter("pubAddress");

			Publisher pub = new Publisher();

			pub.setPub_name(pubName);
			pub.setPub_id(pubId);
			pub.setPub_address(pubAddress);

			try {
				pdao.update(pub); // send data for publishers to dao.
			} catch (Exception e) {
				e.printStackTrace();
			}
			response.sendRedirect("PublisherServlet");// response goes to PublisherServlet!!
		}

		else {
			response.sendRedirect("PublisherServlet");// response goes to PublisherServlet!!

		}
	}

}
