package examples.pubhub.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.BookDAO;
import examples.pubhub.model.Book;
import examples.pubhub.dao.TAGDao;
import examples.pubhub.model.Tag;
import examples.pubhub.utilities.DAOUtilities;


/*
 * This servlet will take you to the homepage for the Book Tagging module (level 100)
 */
@WebServlet("/BookTags")
public class BookTagServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Grab the list of Books from the Database
		TAGDao tdao = DAOUtilities.getTagDAO();
		List<Tag> TagList = tdao.getAllTags();

		// Populate the list into a variable that will be stored in the session
		request.getSession().setAttribute("tags", TagList);
		
		request.getRequestDispatcher("/bookTags.jsp").forward(request, response);
	}
}
