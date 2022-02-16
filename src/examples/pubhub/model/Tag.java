package examples.pubhub.model;


public class Tag {
	private String isbn13;			
	private String tagname;
	
	
	public Tag(String isbn, String tagname) {
		this.isbn13 = isbn;
		this.tagname = tagname;
	}
	
	
//default constructor 
	
	public Tag() {
		this.isbn13 = null;		
		this.tagname =null;
	}

	
	
	
	public String getIsbn13() {
		return isbn13;
		
		
	}

	
	
	
	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
		
	}


	public String getTag() {
		return tagname;
	}
	
	
	

	public void setTag(String tagname) {
		this.tagname = tagname;
	}
	
	
	// @Override
	//    public String toString() { 
	//        return String.format(isbn13 + " | " + tagname); 
	//    } 
	//
	
}
