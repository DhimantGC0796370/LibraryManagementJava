package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDao;
import model.Member;
import model.Publisher;

/**
 * @author aayushrishi Servlet implementation class MemberServlet This servlet
 *         will add, update and list Member details.
 */
@WebServlet("/MemberServlet")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberDao mdao; // connection with database for member

	public void init() {
		mdao = new MemberDao();
	}

	public MemberServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response) This method will get the data of member in list of array and
	 *      forward the request to Member.jsp
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Member> memberList = new ArrayList<Member>();
		try {
			memberList = mdao.getMembers(); // get data for members from dao.
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("memberList", memberList);
		response.setContentType("text/html;charset=UTF-8");
		request.getRequestDispatcher("Member.jsp").forward(request, response); // create a request dispatcher object to
																				// member.jsp
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response) This method will create or update the data of member and
	 *      forward the request to MemberServlet
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("register") != null) { // create a new member
			String memName = request.getParameter("memName");
			String memid = request.getParameter("memId");
			int memId = Integer.parseInt(memid);
			String memAddress = request.getParameter("memAddress");
			String memType = request.getParameter("memType");
			String memStartdate = request.getParameter("memStartdate");
			String memExpirydate = request.getParameter("memExpirydate");

			Member mem = new Member();

			mem.setMem_name(memName);
			mem.setMem_id(memId);
			mem.setMem_address(memAddress);
			mem.setMem_type(memType);
			mem.setMem_date(memStartdate);
			mem.setExpiry_date(memExpirydate);

			try {
				mdao.register(mem); // send data for members to dao.
			} catch (Exception e) {
				System.out.print(e.getMessage());
				e.printStackTrace();
			}
			response.sendRedirect("MemberServlet"); // response goes to MemberServlet!!

		} else if (request.getParameter("update") != null) { // update an existing member
			String memName = request.getParameter("memName");
			String memid = request.getParameter("memId");
			int memId = Integer.parseInt(memid);
			String memAddress = request.getParameter("memAddress");
			String memType = request.getParameter("memType");
			String memStartdate = request.getParameter("memStartdate");
			String memExpirydate = request.getParameter("memExpirydate");

			Member mem = new Member();

			mem.setMem_name(memName);
			mem.setMem_id(memId);
			mem.setMem_address(memAddress);
			mem.setMem_type(memType);
			mem.setMem_date(memStartdate);
			mem.setExpiry_date(memExpirydate);

			try {
				mdao.update(mem); // send data for members to dao.
			} catch (Exception e) {
				e.printStackTrace();
			}
			response.sendRedirect("MemberServlet"); // response goes to MemberServlet!!
		}

		else {
			response.sendRedirect("MemberServlet"); // response goes to MemberServlet!!

		}

	}

}
