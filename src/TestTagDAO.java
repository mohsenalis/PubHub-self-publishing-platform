

import java.util.List;
import examples.pubhub.dao.TAGDao;
import examples.pubhub.dao.TAGDaoImpl;
import examples.pubhub.model.Tag;


public class TestTagDAO {

  public static void main(String[] args){
    TAGDao dao = new TAGDaoImpl();
  //  Tag javatag = new Tag("1111111111111","Java");
  //  dao.addTag(javatag);
   // Tag nonfictiontag = new Tag("1111111111111","Non-Fiction");
   // dao.addTag(nonfictiontag);
    
  
  // List<Tag> list = dao.BookTags("1111111111111");
    
//System.out.println("Tags Delete");
 //   for (int i = 0; i < list.size(); i++){
   //   Tag t = list.get(i);
      dao.deleteTag( "1111111111111", "Art");
    }
  ///    List<Tag> list2 = dao.TaggedBooks("Java");
      
  //    System.out.println("Books with tag: Java");
  //        for (int i = 0; i < list2.size(); i++){
 //           Tag t3 = list2.get(i);
 //           System.out.println(t3);
      
      
    }
  
