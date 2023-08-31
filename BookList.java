//-----------------------------------------------------
// Assignment 3
// Part: 3 (Booklist class)
// Written by: Mira Alanzarouti 40195605
//             Noor Kaddoura 40253572
//-----------------------------------------------------
package A3;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

//circular booklist class
/**
 * linked list class to store nodes of type book
 */
public class BookList {

	private class Node {
		private Book b;
		private Node next;

		// Default constructors
		/**
		 * default Node constructor
		 */
		public Node() {
			b = null;
			next = null;
		}

		/**
		 * parametrized Node constructor
		 * @param b
		 */
		public Node(Book b) {
			this.b = b;
		}

		// A copy constructor
		/**
		 * 
		 * @param book
		 */
		public Node(Node book) {
			b = (Book) book.b.clone(); // Deep copy the Car contents of the node
			next = book.next; // Is that okay? Would next = null;
			// conform to a copy constructor operation?

		}

	} // end of inner class Node

	// Attribute of the outer linked list
	private Node head;

	// i
	/**
	 * initializes head to null
	 */
	public BookList() {
		head = null;
	}

	// ii
	/**
	 * A method that adds an node to the start of the list
	 * 
	 * @param b Book to add to start of list
	 */
	public void addToStart(Book b) {
		//if list is empty 
		if(head == null)
		{
			System.out.println("Your list is empty!");
			return;
		}
		Node last = head;		//to store the last node 
		Node n = new Node(b);	//new node to be inserted 
		//finding the last node 
		do
		{
			last = last.next;
		}while(last.next != head);
		
		//adding the book object to the start of the list
		n.next = head;
		//making it as new head 
		head = n;
		//connecting the last node to the new head
		last.next = head;

	}

// methods to help
//---------------------------------------------------------------------------------------------------
	/**
	 * Add to end function for appending the list 
	 * @param b
	 */
	public void addtoEnd(Book b){
		Node n = new Node(b);
		if(head == null) //if list is empty 
		{
			head = n;
			n.next = head;
		}
		else 
		{
			Node current = head;
			while(current.next != head)
			{
				current = current.next;
			}
			//after this while loop we should be at the node just right before the head 
			current.next = n;
			n.next = head;
		}
	}

	/**
	 * finds the first occurance of a year in a list 
	 * @param inyear for the year to search for 
	 * @return the node containing that year else it returns null
	 */
	public Node findYear(int inyear) {
		if(head == null)
		{
			System.out.println("Your list is empty!");
			return null;
		}
		Node n = head;
		do {
			if (n.b.getYear() == inyear)
				return n; 
			n = n.next;
		} while (n != head);
		return null; // value was not found in the list
	}

	/**
	 * A method that indicates whether or not a year is in the list by using the precious
	 * method to check for null
	 * @param inYear for the year to be checked 
	 * @return boolean true if year is contained else false 
	 */
	public boolean contains(int inYear) {
		if (findYear(inYear) != null)
			return true;
		else
			return false;
	}
	//---------------------------------------------------------------------------------------------------v
	// iii
	/**
	 * Finds all the book records based on a given year, and stores them in a file
	 * called yr.txt
	 * If year is not in file, nothing happens 
	 * @param yr to create a text file for 
	 */
	public void storeRecordsByYear(int yr) {
		PrintWriter yearWriter = null;

		if (contains(yr)) 
		{
			try 
			{
				yearWriter = new PrintWriter(new FileOutputStream(yr + ".txt"));
			} 
			catch (FileNotFoundException e) 
			{
				System.out.println("File can not be created.");
				System.exit(0);
			}

			Node t = head;
			do 
			{
				if (t.b.getYear() == yr)
				{
					yearWriter.println(t.b.toString() + "\n");
				}
				t = t.next;
			} while(t != head);
			System.out.printf("The %d.txt file has been successfully created!%n%n", yr);
			yearWriter.close();
		}
		else 
		{
			System.out.println("No books with this year could be found!\n");
		}
	}

	// iv
	/**
	 * Description reference from Assignment pdf:
	 * This method will search the list for the first occurrence of a Node holding a
	 * Book object that has an ISBN equals to the passed isbn value. If such a node
	 * is found, the method will insert a new Node in the list (holding Book b)
	 * before that node and returns true; otherwise it does nothing and returns
	 * false.
	 * 
	 * @param isbn to be searched for
	 * @param b    Book object to be inserted before the isbn passed
	 * @return boolean true if successful else false
	 */
	public boolean insertBefore(long isbn, Book b) {
		if (head == null) {
			System.out.println("The BookList is empty.");
			return false;
		}
		Node current = head;
		Node previous = null;
		do {
			if (current.b.getISBN() == isbn) {
				Node n = new Node(b);
				//placing a node before head and replacing it as a new head
				if (current == head) {
					n.next = head;
					//finding the last node before updating the head 
					while(current.next != head)
					{
						current = current.next;
					}
					//now current will be equal to the last node
					Node last = current;
					//updating head 
					head = n;
					//now we need to update the last node's next to make sure it remains circular 
					last.next = head;	//the old head will become 
				} 
				else 
				{
					n.next = current;
					previous.next = n;
				}
				return true; //return true since at this point the operation has finished
			}
			previous = current;
			current = current.next;
		} while (current != head);
		//if it reaches this point then the isbns are either non existent or not near each other
		System.out.println("The ISBN entered are invalid! Please try again!");
		return false; // ISBN not found in the list
	}

	/**
	 * inserts a node between two nodes passed by paramter 
	 * @param isbn1 node 1 
	 * @param isbn2 node 2
	 * @param b book object to insert between the two nodes 
	 * @return boolean true if successful false otherwise 
	 */
	public boolean insertBetween(long isbn1, long isbn2, Book b) {
		if (head == null || head.next == head) {
			System.out.println("List is too small to insert between!");
			return false;
		}
		Node previous = null;
		Node current = head;
		do {
			if (current.b.getISBN() == isbn1 && current.next.b.getISBN() == isbn2) {
				Node n = new Node(b);
				previous = current;
				n.next = current.next;
				previous.next = n;
				System.out.println("Successfully added!");
				return true;
			}
			current = current.next;
		} while (current != head);
		//if it reaches this point then the isbns are either non existent or not near each other
		System.out.println("The ISBN entered are invalid/not consecutive! Please try again!");
		return false;
	}

	// vi
	/**
	 * displays the content of the list 
	 */
	public void displayContent() {
		if (head == null) {
			System.out.println("The BookList is empty.\n");
			return;
		}
		Node current = head;
		do {
			// System.out.println(current.b.toString() + "\n");

			System.out
					.println("\"" + current.b.getTitle() + "\", " + current.b.getAuthor() + ", " + current.b.getPrice()
							+ ", " + current.b.getISBN() + ", " + current.b.getGenre() + ", " + current.b.getYear() + " ==>");

			current = current.next;
		} while (current != head );
		System.out.println("===> head\n-------------------------------------------------------------------------------------------------------\n");
	}

	/**
	 * part vii
	 * method that checks for consecutive records and deletes consecutive duplicates 
	 * @return boolean true if deletion was successful else false 
	 */
	public boolean delConsecutiveRepeatedRecords() {
		if (head == null) 
		{
			System.out.println("The BookList is empty.");
			return false;
		} 
		else if (head.next == head) 
		{
			System.out.println("There is only one node in this list!");
			return false;
		} 
		else 
		{
			// pointer to the head of the list
			Node current = head;
			// pointer to the previous node
			Node previous = head;

			do {
				// checking if consecutive nodes are equal
				if (current.b.equals(current.next.b)) {
					// if next is head
					if (current.next == head) {
						previous.next = current.next;
						head = previous.next; // Update head to the next node (consecutive nodes are deleted)
					} else {
						previous.next = current.next;
					}
					// No need to move current pointer here, as the next node has already been set
					// as the current.next in the above steps.
				} else {
					// traversing to the next node
					previous = current;
				}
				current = current.next;
			} while (current != head);

			return true;
		}

	}
	/**
	 * extracts author from string and returns a new booklist with just that author 
	 * @param aut string with author name
	 * @return booklist with each niode only contaiing books of that author 
	 */
	public BookList extractAuthList(String aut) {

		BookList newBookList = new BookList();

		if (head == null) 
		{
			System.out.println("The BookList is empty.");
			return null;
		}

		Node current = head;
		Node first = head;

		do 
		{
			if (aut.equals(current.b.getAuthor())) {
				newBookList.addtoEnd(current.b);
			}

			current = current.next;

		} while (current != first);
		if(newBookList.head == null)
		{
			System.out.println("No record of this author were found!\n");
		}
		return newBookList;

	}

	/**
	 * the method will swap the first pair of nodes of which the first stores a Book record with isbn1
	 *  and the second stores a Book record with isbn2, then returns true.
	 * @param isbn1 to swap with isbn 2
	 * @param isbn2
	 * @return true if successful else false 
	 */
	public boolean swap(long isbn1, long isbn2)
	{
			if (head.next == head || head == null || isbn1 == isbn2) 
			{
				System.out.println("Unable to swap! List is either too small or the isbns entered are equal.");
				return false; // Not enough nodes in the list to swap
			}
		
			Node current = head;
			Node previous = null;
			Node previous1 = null;
			Node previous2 = null;
			Node node1 = null;
			Node node2 = null;
			
			//searching for the isbns passed in parameter 
			do {
				long l = current.next.b.getISBN();
				if (l == isbn1) 
				{
					previous1 = current;
					node1 = current.next;
				} 
				else if (l == isbn2) 
				{
					previous2 = current;
					node2 = current.next;
				}

				//update previous and current 
				previous = current;
				current = current.next;
			} while (current != head);
			//after this point we should have values for the previous and next of each node
			
			// If eitehr of them is null then one/both of them dont exist 
			if (node1 == null || node2 == null) 
			{
				System.out.println("Unable to swap!");
				return false;
			}
		
			// If either node is head, update the head to the other node
			if (node1 == head) 
			{
				head = node2;
			} 
			else if (node2 == head) 
			{
				head = node1;
			}
		
			// Update the next pointers to swap the nodes
			if (previous1 != null) 
			{
				previous1.next = node2;
			}
			if (previous2 != null) 
			{
				previous2.next = node1;
			}
		
			Node temp = node2.next;
			node2.next = node1.next;
			node1.next = temp;
			System.out.println("Swap successful!\n");
			return true;
		}
		
	}
	

