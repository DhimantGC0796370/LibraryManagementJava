package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

import dao.BorrowDao;
import dao.BookDao;
import model.Borrow;
import model.Publisher;
import model.Books;

/**
 * Servlet implementation class BookServlet
 */
@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BorrowDao borrowDao;
	private BookDao bookDao;
    
	public void init() {
		borrowDao = new BorrowDao();
		bookDao = new BookDao();
	}
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public BookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Books> book = new ArrayList<Books>();
		try {
			book = bookDao.getBooks();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("book", book);
		response.setContentType("text/html;charset=UTF-8");
		request.getRequestDispatcher("Book.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("issue") != null) {
    		String book_id = request.getParameter("bookId");
    		int bookId = Integer.parseInt(book_id);
    		String member_id = request.getParameter("memberId");
    		int memberId = Integer.parseInt(member_id);
    		String author = request.getParameter("author");
    		String price = request.getParameter("price");
    		String available = request.getParameter("available");
    		String title = request.getParameter("titile");
    		String dueDate = request.getParameter("dueDate");
    		

    		Borrow borrow = new Borrow();
    		borrow.setBook_id(bookId);
    		borrow.setMember_id(memberId);
    		Date date = new Date();
    	    SimpleDateFormat formatter = new SimpleDateFormat("yy/MM/dd");
    	    String issueDate = formatter.format(date);
    		borrow.setIssue_date(issueDate);
    		borrow.setDue_date(dueDate);
    		borrow.setReturn_date(dueDate);

    		try {
    			borrowDao.issue(borrow);
    		} catch (Exception e)
    		{
    			System.out.print(e.getMessage());
    			e.printStackTrace();
    		}
    		response.sendRedirect("Book.jsp?status=yes");//  response goes to view!!
            
        }
        else if (request.getParameter("update") != null) {
        	String book_id = request.getParameter("bookId");
    		int bookId = Integer.parseInt(book_id);
    		String author = request.getParameter("author");
    		String price = request.getParameter("price");
    		boolean available = Boolean.parseBoolean(request.getParameter("available"));
    		String title = request.getParameter("titile");
    		
    		Books book = new Books();
    		book.setBook_id(bookId);
    		book.setAuthor(author);
    		book.setAvailable(available);
    		book.setTitle(title);
    		book.setPrice(bookId);
    		
    		try {
				bookDao.update(book);
			} catch (Exception e) {
				e.printStackTrace();
			}
			response.sendRedirect("BookServlet");// response goes to view!!
        	
        }
        else if(request.getParameter("delete") != null){
        	String book_id = request.getParameter("bookId");
    		int bookId = Integer.parseInt(book_id);
    		
    		Books book = new Books();
    		book.setBook_id(bookId);
    		
    		try {
				bookDao.delete(book);
			} catch (Exception e) {
				e.printStackTrace();
			}
			response.sendRedirect("BookServlet");// response goes to view!!
        }
        else {
        	response.sendRedirect("Book.jsp?status=no");//  response goes to view!!
        	 
        }
		

	}

}
