

package examples.pubhub.servlets;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.BookDAO;
import examples.pubhub.dao.TAGDao;
import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;
import examples.pubhub.utilities.DAOUtilities;

/**
 * Servlet implementation class DownloadBookServlet
 */
@WebServlet("/DeleteBookTag")
public class DeleteBookTagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String isbn13 = request.getParameter("isbn13");
		String tagname = request.getParameter("tag");
		
		TAGDao dao = DAOUtilities.getTagDAO();
		//Tag tag = dao.getTag(isbn13, tagname);
		//request.setAttribute("Tag", tag);
		
		
		boolean isSuccess = dao.deleteTag(isbn13, tagname);

	
	
	
	if(isSuccess){
		request.getSession().setAttribute("message", "Book tag successfully deleted");
		request.getSession().setAttribute("messageClass", "alert-success");
		response.sendRedirect("bookTags.jsp");
	}else {
		request.getSession().setAttribute("message", "There was a problem deleting this book tag");
		request.getSession().setAttribute("messageClass", "alert-danger");
		request.getRequestDispatcher("bookTags.jsp").forward(request, response);
		
	}}}
		
		
		
		
		

		
		// Always close your stream


