package entity;

/*
Creates an interface to represent a book from the inventory
*/

public interface IBook {
	
	public String getDescription();
	public void setDescription(String description);
	
	public int getIsbn();
	public void setIsbn(int isbn);
	
	public String getCategory();
	public void setCategory(String category);
	
	public String getAuthor();
	public void setAuthor(String author);
	
	public String getTitle();
	public void setTitle(String title);
	
	public int getEdition();
	public void setEdition(int edition);
	
	public String getPublisher();
	public void setPublisher(String publisher);
	
	public int getYear();
	public void setYear(int year);
	
	public int getQuantity();
	public void setQuantity(int quantity);
	
	public int getThreshold();
	public void setThreshold(int threshold);
	
	public String getPicture();
	public void setPicture(String picture);
	
	public Double getBuyingPrice();
	public void setBuyingPrice(Double buyingPrice);
	
	public Double getSellingPrice();
	public void setSellingPrice(Double sellingPrice);
	
	public int addBook();
	
	public void printBook();
	public int getBookInfo(int isbn);
	public int editBook();
}
