package examples.pubhub.dao;

import java.util.List;

import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;

public interface TAGDao {

		
		public List<Tag> getAllTags();
		public boolean addTag(Tag tag);
		//public boolean  removeTag(Tag tag);
	
		public List<Tag> BookTags(String isbn13);
		public List<Tag> TaggedBooks(String Tag_string);
		public Tag getBooks(String tag);
		public Tag getTag(String isbn13,String tag);
		public boolean deleteTag(String isbn, String tag);
	
	
	
}
