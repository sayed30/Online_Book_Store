package object;

import java.util.Date;

/*
Creates a report entry object
*/

public class ReportEntry {

  Date orderDate;
  int isbn;
  int qty;
  double total;
  String authorName;
  String title;
  
  /*
  Parameters: Date orderDate, int isbn, int qty, double total, String authorName, String title
  Return Value: Constructor
  Description: Creates Object
  */

  public ReportEntry(Date orderDate, int isbn, int qty, double total, String authorName, String title) {
    this.orderDate = orderDate;
    this.isbn = isbn;
    this.qty = qty;
    this.total = total;
    this.authorName = authorName;
    this.title = title;
  }
  
  /*
  Parameters: None
  Return Value: Date
  Description: gets orderDate
  */

  public Date getOrderDate() {
    return orderDate;
  }
  
  /*
  Parameters: None
  Return Value: int
  Description: gets isbn
  */

  public int getIsbn() {
    return isbn;
  }
  
  /*
  Parameters: None
  Return Value: int
  Description: gets qty
  */

  public int getQty() {
    return qty;
  }
  
  /*
  Parameters: None
  Return Value: double
  Description: gets total
  */

  public double getTotal() {
    return total;
  }
  
  /*
  Parameters: None
  Return Value: String
  Description: gets authorName
  */

  public String getAuthorName() {
    return authorName;
  }
  
  /*
  Parameters: None
  Return Value: String
  Description: gets title
  */

  public String getTitle() {
    return title;
  }


}
