//-----------------------------------------------------
// Assignment 3
// Part: 3 (Book class)
// Written by: Mira Alanzarouti 40195605
//             Noor Kaddoura 40253572
//-----------------------------------------------------
package A3;

/**
 * Book class that has the attributes to store its name, author that wrote it and its price 
 * also has isbn and genre of the book as a string and year 
 */
public class Book {

	protected String title;
	protected String author;
	protected double price;
	protected long ISBN;
	protected String genre;
	protected int year;

	/**
	 * default constructor 
	 */
	public Book() {
		super();
		this.title = "";
		this.author = "";
		this.price = 0.0;
		this.ISBN = 0;
		this.genre = "";
		this.year = 0;
	}

	/**
	 * 
	 * parametrized constructor 
	 * @param title
	 * @param author
	 * @param price
	 * @param ISBN
	 * @param genre
	 * @param year
	 */
	public Book(String title, String author, double price, long ISBN, String genre, int year) {
		super();
		this.title = title;
		this.author = author;
		this.price = price;
		this.ISBN = ISBN;
		this.genre = genre;
		this.year = year;
	}

	/**
	 * Copy constructor
	 * @param book
	 */
	public Book(Book book) {

		setTitle(book.title);
		setAuthor(book.author);
		setISBN(book.ISBN);
		setGenre(book.genre);
		setYear(book.year);

	}

	/**
	 * getter
	 * @return title of the Book
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * sets the title of the book
	 * @param title of type Stirng 
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 
	 * @return the author of the book
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * sets the author of the book
	 * @param author of type String 
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	
	/**
	 * 
	 * @return the price of the book
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * sets the price of the book 
	 * @param price of type double 
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * 
	 * @return the book ISBN 
	 */
	public long getISBN() {
		return ISBN;
	}

	/**
	 * sets the ISBN of the book
	 * @param iSBN of type long 
	 */
	public void setISBN(long iSBN) {
		ISBN = iSBN;
	}

	/**
	 * String 
	 * @return the genre of the book
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * sets the genre of type String 
	 * @param genre of the book
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	/**
	 * 
	 * @return the year of publish
	 */
	public int getYear() {
		return year;
	}

	/**
	 * 
	 * @param year
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * clones the object calling it 
	 */
	public Book clone()
	{
		return new Book(this);	
	}


	@Override
	/**
	 *  @return String representation of the book object
	 */
	public String toString() {
		return "Title: " + title + ".\n Author: " + author + ".\n Price: " + price + ".\n ISBN: " + ISBN + ".\n Genre: " + genre
				+ ".\n Year:" + year;
	}

	@Override
	/**
	 * @param object to check if its equal to 
	 * @return booleajn true if tehyre equal
	 */
	public boolean equals(Object inObj) {
		if (this == inObj) {
			return true;
		}

		if (inObj == null || getClass() != inObj.getClass()) {
			return false;
		}

		Book other = (Book) inObj;
		return this.title.equals(other.title) && this.author.equals(other.author)
				&& Double.compare(this.price, other.price) == 0 && this.ISBN == other.ISBN
				&& this.genre.equals(other.genre) && this.year == other.year;
	}

}
