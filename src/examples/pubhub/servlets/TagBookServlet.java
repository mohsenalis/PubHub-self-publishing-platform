package examples.pubhub.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import examples.pubhub.model.Book;
import examples.pubhub.utilities.DAOUtilities;
import examples.pubhub.dao.*;
import examples.pubhub.model.*;
import examples.pubhub.utilities.*;

@MultipartConfig // This annotation tells the server that this servlet has
					// complex data other than forms
// Notice the lack of the @WebServlet annotation? This servlet is mapped the old
// fashioned way - Check the web.xml!
public class TagBookServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("addTag.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	//	String isbn13 = req.getParameter("isbn13");


		
		TAGDao daodata = DAOUtilities.getTagDAO();
		//Tag temptag = daodata.getBooks(isbn13); multiple tags can be added to same book, repeat instances permitted
		
	
			Tag tag = new Tag();
			tag.setIsbn13(req.getParameter("isbn13"));
			tag.setTag(req.getParameter("tag"));

			boolean isSuccess = daodata.addTag(tag);
			
			if(isSuccess){
				req.getSession().setAttribute("message", "Tag successfully added");
				req.getSession().setAttribute("messageClass", "alert-success");

				// We use a redirect here instead of a forward, because we don't
				// want request data to be saved. Otherwise, when
				// a user clicks "refresh", their browser would send the data
				// again!
				// This would be bad data management, and it
				// could result in duplicate rows in a database.
				resp.sendRedirect(req.getContextPath() + "/BookTags");
			}else {
				req.getSession().setAttribute("message", "There was a problem adding the tag");
				req.getSession().setAttribute("messageClass", "alert-danger");
				req.getRequestDispatcher("addTag.jsp").forward(req, resp);
				
			}
		}
	}


