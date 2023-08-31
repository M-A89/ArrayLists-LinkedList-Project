//-----------------------------------------------------
// Assignment 3
// Part: 3 (Driver class)
// Written by: Mira Alanzarouti 40195605
//             Noor Kaddoura 40253572
//-----------------------------------------------------
package A3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Vector;
import java.util.Scanner;

/**
 * Driver class that contains the main method
 */
public class Driver {

	public static void main(String[] args) {
		//Scanner object
		Scanner in = new Scanner(System.in);

		// initializing and declaring the ArrayList and BookList of type Book
		ArrayList<Book> arrLst = new ArrayList<Book>();
		BookList bkLst = new BookList();

		// initializing and declaring variables to be used in reading from the text file
		String line = "";
		BufferedReader reader = null;

		double tempDouble = 0;
		long tempLong = 0;
		int tempInteger = 0;
		boolean errorsExist = false;
		String address = "Books.txt";

		try 
		{
			reader = new BufferedReader(new FileReader(address));
		}

		catch (FileNotFoundException e) 
		{
			// If file is not found, end program
			System.out.println("The file " + address + " does not exist.");
			System.exit(0);
		}
		try 
		{
			while ((line = reader.readLine()) != null) 
			{

				// split line read from file into string array, separated by ","
				String[] row = line.split(",", -1);
				// trim every element in row
				for (int i = 0; i < row.length; i++) 
				{
					row[i] = row[i].trim();
				}
				// checking if the record has 6 fields
				if (row.length == 6) 
				{
					// parse the non string values and add them to constructor
					try 
					{
						tempDouble = Double.parseDouble(row[2]);
						tempLong = Long.parseLong(row[3]);
						tempInteger = Integer.parseInt(row[5]);
					} 
					catch (NumberFormatException nfe) 
					{
						System.out.println("Cannot add to Constructor! ");
						continue;
					}
					// checking if year value is valid, if not, add the incorrect records to arrLst
					if (tempInteger > 2023 || tempInteger < 1900) 
					{
						arrLst.add(new Book(row[0], row[1], tempDouble, tempLong, row[4], tempInteger));
						// set errorsExist to true, meaning an error does exist in the text file
						errorsExist = true;
					} 
					else 
					{
						// if the year is valid, add the record to the BookList
						bkLst.addtoEnd((new Book(row[0], row[1], tempDouble, tempLong, row[4], tempInteger)));

					}

				}

			}
		} 
		catch (IOException e) 
		{
			// If file is not found, end program
			System.out.println("Cannot find file. Ending program");
			System.exit(0);
		}

		// if error exists, YearErr.txt is created and written to
		if (errorsExist) 
		{
			PrintWriter semErrorWriter = null;

			try 
			{
				semErrorWriter = new PrintWriter(new FileOutputStream("YearErr.txt"));

			} 
			catch (FileNotFoundException e) 
			{
				System.out.println("File not found");
			}

			// write the contents of arrLst to the error file named YearErr.txt, using the
			// toString method on each Book
			for (int i = 0; i < arrLst.size(); i++)
			{
				semErrorWriter.print(arrLst.get(i).toString() + "\n");

			}
			// close the writer
			semErrorWriter.close();
		}

		// initializing and declaring variables that will be used in the menu
		int userChoice = 0;
		int year = 0;
		long aISBN = 0;
		long bISBN = 0;
		long cISBN = 0;
		String name = "";

		String optionTitle = "";
		String optionAuthor = "";
		double optionPrice = 0;
		long optionISBN = 0;
		String optionGenre = "";
		int optionYear = 0;

		Book userInputtedBook = null;

		// if user does not enter 7, loop will keep running
		while (userChoice != 7) 
		{
			display_menu();
			System.out.print("Enter Choice: ");
			try 
			{
				userChoice = in.nextInt();
				in.nextLine();
			} 
			catch (InputMismatchException e) 
			{
				in.nextLine();
				System.out.println("Error! Please enter an option presented by the Menu!\n");
				continue;
			}

			//option 1
			if (userChoice == 1) 
			{
				System.out.print("Please enter Year: ");
				try 
				{
					year = in.nextInt();
					in.nextLine();
					if(year > 2023 || year < 1900)
					{
						System.out.println("Error! Please enter a valid Year.\n");
						continue;
					}
				} 
				catch (InputMismatchException e) 
				{
					in.nextLine();
					System.out.println("Error! Please enter a valid Year.\n");
					continue;
				}
				bkLst.storeRecordsByYear(year);

				System.out.println("Displaying contents of main list: ");
				bkLst.displayContent();

			} 
			//option 2
			else if (userChoice == 2) 
			{
				System.out.println("Deleting consecutive records now...\n");
				bkLst.delConsecutiveRepeatedRecords();

				System.out.println("Displaying contents of main list: ");
				bkLst.displayContent();

			} 
			//option 3
			else if (userChoice == 3) 
			{

				System.out.print("Enter Author's Name: ");
				name = in.nextLine();

				System.out.println("Here is your Authors List");
				BookList authorsList = bkLst.extractAuthList(name);

				System.out.println("Displaying new list with the records of author " + name);
				authorsList.displayContent();

				System.out.println("Displaying contents of main list: ");
				bkLst.displayContent();

			} 
			//option 4 
			else if (userChoice == 4) 
			{

				try {
					System.out.print("Enter ISBN of book you want to enter before: ");
					aISBN = in.nextLong();
					in.nextLine();

				} 
				catch (InputMismatchException e) 
				{
					in.nextLine();
					System.out.println("Error! Please enter a valid ISBN!\n");
					continue;
				}

				try 
				{
					System.out.println("Entering Book Information: \n");

					System.out.print("Enter Title: ");
					optionTitle = in.nextLine();

					System.out.print("Enter Author: ");
					optionAuthor = in.nextLine();

					System.out.print("Enter Price: ");
					optionPrice = in.nextDouble();
					in.nextLine();
					System.out.println();

					System.out.print("Enter ISBN: ");
					optionISBN = in.nextLong();
					in.nextLine();
					System.out.println();

					System.out.print("Enter Genre: ");
					optionGenre = in.nextLine();
					System.out.println();

					System.out.print("Enter Year: ");
					optionYear = in.nextInt();
					in.nextLine();
					// if year is out of range 
					if(optionYear > 2023 || optionYear < 1900)
					{
						System.out.println(year);
						System.out.println("Error! Please enter a valid Yearrr.\n");
						continue;
					}
					System.out.println();

				} 
				//catch if inputmismatch is thrown
				catch (InputMismatchException e) 
				{
					in.nextLine();
					System.out.println("Error! Please enter correct Book information!");
					continue;
				}
				// add user inputed information to book constructor and insertBefore method
				userInputtedBook = new Book(optionTitle, optionAuthor, optionPrice, optionISBN, optionGenre,
						optionYear);

				bkLst.insertBefore(aISBN, userInputtedBook);

				System.out.println("Displaying contents of main list: ");
				bkLst.displayContent();

			} 
			//choice 5
			else if (userChoice == 5) 
			{

				try 
				{
					System.out.print("Enter first ISBN: ");
					bISBN = in.nextLong();
					in.nextLine();

					System.out.print("Enter second ISBN: " );
					cISBN = in.nextLong();
					in.nextLine();
				} 
				catch (InputMismatchException e) 
				{
					in.nextLine();
					System.out.println("Error! Please enter a valid ISBN\n");
					continue;
				}
				try 
				{
					System.out.println("Entering Book Information: \n");

					System.out.print("Enter Title: ");
					optionTitle = in.nextLine();

					System.out.print("Enter Author: ");
					optionAuthor = in.nextLine();

					System.out.print("Enter Price: ");
					optionPrice = in.nextDouble();
					System.out.println();

					System.out.print("Enter ISBN: ");
					optionISBN = in.nextLong();
					in.nextLine();

					System.out.print("Enter Genre: ");
					optionGenre = in.nextLine();

					System.out.print("Enter Year: ");
					optionYear = in.nextInt();
					in.nextLine();
					// if year is out of range 
					if(optionYear > 2023 || optionYear < 1900)
					{
						System.out.println("Error! Please enter a valid Year.\n");
						continue;
					}
					System.out.println();

				} 
				catch (InputMismatchException e) 
				{
					in.nextLine();
					System.out.println("Error! Please enter correct Book information!\n");
					continue;
				}
				// add user inputed information to book constructor and insertBetween method
				userInputtedBook = new Book(optionTitle, optionAuthor, optionPrice, optionISBN, optionGenre,
						optionYear);
				bkLst.insertBetween(bISBN, cISBN, userInputtedBook);

				System.out.println("Displaying contents of main list: ");
				bkLst.displayContent();

			} 
			//choice 6
			else if (userChoice == 6) 
			{
				try 
				{
					System.out.print("Enter first ISBN: ");
					bISBN = in.nextLong();
					in.nextLine();	//extrtacting newline

					System.out.print("Enter second ISBN: ");
					cISBN = in.nextLong();
					in.nextLine();
				} 
				catch (InputMismatchException e) 
				{
					in.nextLine();
					System.out.println("Error! Please enter a valid ISBN!\n");
					continue;
				}

				bkLst.swap(bISBN, cISBN);

				System.out.println("Displaying contents of main list: ");
				bkLst.displayContent();
			}

			//choice 7
			else if (userChoice == 7) 
			{
				System.out.println("Displaying contents of main list: ");
				bkLst.displayContent();
				break;

			} 
			else 
			{
				System.out.println("Please enter an option presented by the Menu\n");
				continue;
			}

		}

		System.out.println("End of Program");
	}

	/**
	 * Method that displays menu to user
	 */
	public static void display_menu() {

		System.out.println(
				"1)  Give me a year # and I would extract all records of that year and store them in a file for that year;\r\n"
						+ "2)  Ask me to delete all consecutive repeated records;\n"
						+ "3)  Give me an author name and I will create a new list with the records of this author and display them;\r\n"
						+ "4)  Give me an ISBN number and a Book object, and I will insert Node with the book before the record with this ISBN;\r\n"
						+ "5)  Give me 2 ISBN numbers and a Book object, and I will insert a Node between them, if I find them!\r\n"
						+ "6)  Give me 2 ISBN numbers and I will swap them in the list for rearrangement of records; of course if they exist!\r\n"
						+ "7)  Tell me to STOP TALKING. \n\n");

	}
}