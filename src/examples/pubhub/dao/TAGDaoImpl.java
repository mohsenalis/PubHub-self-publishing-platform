package examples.pubhub.dao;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;
import examples.pubhub.utilities.DAOUtilities;

public class TAGDaoImpl implements TAGDao {
	
	
	Connection connection = null;	// Our connection to the database
	PreparedStatement stmt = null;	// We use prepared statements to help protect against SQL injection
	

	
	
	
	
	
	@Override
	public List<Tag> getAllTags() {
		
		List<Tag> tags = new ArrayList<>();

		try {
			connection = DAOUtilities.getConnection();	// Get our database connection from the manager
			String sql = "SELECT * FROM booktags";			// Our SQL query
			stmt = connection.prepareStatement(sql);	// Creates the prepared statement from the query
			
			ResultSet rs = stmt.executeQuery();			// Queries the database

			// So long as the ResultSet actually contains results...
			while (rs.next()) {
				// We need to populate a Book object with info for each row from our query result
				Tag tag = new Tag();

				// Each variable in our Book object maps to a column in a row from our results.
				tag.setIsbn13(rs.getString("isbn13"));
				tag.setTag(rs.getString("tagname"));
				
				
				// Finally we add it to the list of Book objects returned by this query.
				tags.add(tag);
				
			}
			
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// We need to make sure our statements and connections are closed, 
			// or else we could wind up with a memory leak
			closeResources();
		}
		
		// return the list of Book objects populated by the DB.
		return tags;
	}

	
	
/*------------------------------------------------------------------------------------------------*/

	//get all tags of book
public List<Tag> BookTags(String isbn13) {
		
		List<Tag> tags = new ArrayList<>();

		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM booktags WHERE isbn13 LIKE ?";	// Note the ? in the query
			stmt = connection.prepareStatement(sql);
			
			// This command populate the 1st '?' with the title and wildcards, between ' '
			stmt.setString(1, "%" + isbn13 + "%");	
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Tag tag = new Tag();

				tag.setIsbn13(rs.getString("isbn13"));
				tag.setTag(rs.getString("tagname"));
			
				
				tags.add(tag);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return tags;
	}

	
	
	
	
/*------------------------------------------------------------------------------------------------*/


@Override
public Tag getBooks(String tagname) {
	Tag tag = null;

	try {
		connection = DAOUtilities.getConnection();
		String sql = "SELECT * FROM Books WHERE tagname = ?";
		stmt = connection.prepareStatement(sql);
		
		stmt.setString(1, tagname);
		
		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			tag = new Tag();
			tag.setIsbn13(rs.getString("isbn13"));
			tag.setTag(rs.getString("tagname"));
				
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		closeResources();
	}
	
	return tag;
}


/*------------------------------------------------------------------------------------------------*/



public Tag getTag(String isbn13, String tagstr) {
	Tag tag = null;

	try {
		connection = DAOUtilities.getConnection();
		String sql = "SELECT * FROM books WHERE isbn13 = ? AND tagname =?";
		stmt = connection.prepareStatement(sql);
		
		stmt.setString(1, isbn13);
		stmt.setString(1, tagstr);
		
		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			tag = new Tag();
			tag.setIsbn13(rs.getString("isbn13"));
			tag.setTag(rs.getString("tagname"));
				
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		closeResources();
	}
	
	return tag;
}


/*------------------------------------------------------------------------------------------------*/


//get all books with tag
public List<Tag> TaggedBooks(String Tag_string) {
	
	List<Tag> tags = new ArrayList<>();

	try {
		connection = DAOUtilities.getConnection();
		String sql = "SELECT * FROM booktags WHERE tagname LIKE ?";	// Note the ? in the query
		stmt = connection.prepareStatement(sql);
		
		// This command populate the 1st '?' with the title and wildcards, between ' '
		stmt.setString(1, "%" + Tag_string + "%");	
		
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			
			Tag tag = new Tag();
			tag.setIsbn13(rs.getString("isbn13"));
			tag.setTag(rs.getString("tagname"));
			
			
		
			
			tags.add(tag);
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		closeResources();
	}
	
	return tags;
}






/*------------------------------------------------------------------------------------------------*/
	@Override
	public boolean addTag(Tag tag) {
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO booktags VALUES (?, ?)"; // Were using a lot of ?'s here...
			stmt = connection.prepareStatement(sql);
			
			// But that's okay, we can set them all before we execute
			stmt.setString(1, tag.getIsbn13());
			stmt.setString(2, tag.getTag());
		
			
			// If we were able to add our book to the DB, we want to return true. 
			// This if statement both executes our query, and looks at the return 
			// value to determine how many rows were changed
			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}


/*------------------------------------------------------------------------------------------------*/




/*------------------------------------------------------------------------------------------------*/


@Override
public boolean deleteTag(String isbn, String tag) {
	try {
		connection = DAOUtilities.getConnection();
		String sql = "DELETE FROM public.booktags WHERE isbn13=? AND tagname=?";
		stmt = connection.prepareStatement(sql);
		
		stmt.setString(1, isbn);
		stmt.setString(2, tag);
		if (stmt.executeUpdate() != 0)
			return true;
		else
			return false;
		
	} catch (SQLException e) {
		e.printStackTrace();
		return false;
	} finally {
		closeResources();
	}
}


/*------------------------------------------------------------------------------------------------*/

// Closing all resources is important, to prevent memory leaks. 
// Ideally, you really want to close them in the reverse-order you open them
private void closeResources() {
	try {
		if (stmt != null)
			stmt.close();
	} catch (SQLException e) {
		System.out.println("Could not close statement!");
		e.printStackTrace();
	}
	
	try {
		if (connection != null)
			connection.close();
	} catch (SQLException e) {
		System.out.println("Could not close connection!");
		e.printStackTrace();
	}
}

}