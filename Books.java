/*
		Library Management System by - ( Suyash + Shubham + Manasi + Jyoti + Chinmay)
*/
package LibraryManagement;

import java.util.*;
import java.io.*;
import java.text.*;
import java.time.*;

public class Books implements Serializable
{
	BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
	//Declarations of Variables/Properties
	
	static int bookId=0; 		// Stores ID of Books
	double bookPrice; 			// Stores Price of Book
	int availableCopies;		// Stores Nos. of available copies
	String bookTitle; 			// Stores title of Books
	String bookAuthor; 			// Stores author of Book
	String bookSubject; 		// Stores Subject of Book
	//StringBuffer studentName;	// Stores Name of Student which issued/Returned Book.
	StringBuffer issuedTo[] = new StringBuffer[1000];
	//Stores all names of students which issued same book.
	
	static ArrayList<Books> bookData = new ArrayList<Books>();
	
	//Constructor
	public Books(String bookTitle, String bookAuthor, String bookSubject, double bookPrice, int availableCopies)
	{	
		generateBookId();
		this.bookTitle=bookTitle;
		this.bookAuthor=bookAuthor;
		this.bookSubject=bookSubject;
		this.bookPrice=bookPrice;
		this.availableCopies=availableCopies;
	}
	
	//Functions
	public static int generateBookId(){
		bookId++;		//Incrementing Book Id
		return bookId;	// Returning the incremented book Id
	}
	
	public int getBookId(){
		return bookId;
	}
	
	public double getBookPrice(){
		return bookPrice;
	}
	
	public String getBookTitle(){
		return bookTitle;
	}
	
	public String getBookAuthor(){
		return bookAuthor;
	}
	
	public String getBookSubject(){
		return bookSubject;
	}
	
	public int getAvailableCopies(){
		return availableCopies;
	}
	
	public int setBookId(){
		bookId = Integer.parseInt(scan.readLine());
	}
	
	public void setBookPrice(){
		bookPrice = Double.parseDouble(scan.readLine());
	}
	
	public void setBookTitle(){
		bookTitle = scan.readLine();
	}
	
	public void setBookAuthor(){
		bookAuthor = scan.readLine();
	}
	
	public void setBookSubject(){
		bookSubject = scan.readLine();
	}
	
	public void setNoOfCopies(){
		availableCopies = Integer.parseInt(scan.readLine());
	}
	
	public void addBookCopies(){
		availableCopies++;
	}
	
	public void removeBookCopies(){
		availableCopies--;
	}
	
	public static void saveData()
	{
		FileOutputStream fos = null;
        ObjectOutputStream oos = null;
 
        try {
            fos = new FileOutputStream("Database.ser");
            oos = new ObjectOutputStream(fos);
			oos.flush();
			oos.writeObject(bookData);
            oos.close();
        } 
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        catch (IOException e) {
            System.out.println("IO Exception");
        }
 
        System.out.println("Database Updated.");
	}
	
	public static void fetchData()
	{
		FileInputStream fis = null;
        ObjectInputStream ois = null;
 
       
        ArrayList<Books> bookData = null;
 
        try {
            fis = new FileInputStream("Database.ser");
            ois = new ObjectInputStream(fis);
            bookData = (ArrayList<Books>) ois.readObject();
        } 
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        catch (IOException e) {
            System.out.println("IO Exception");
        } 
        catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        }
 
        System.out.println("Data Fetched Successfully");
	}
	
	public void addBook()
	{
		try{
			System.out.println("_________________Enter Book Details_________________");
			System.out.println(" Enter title of the Book    : ");
			this.setBookTitle();
			System.out.println(" Enter the Name of Author   : ");
			this.setBookAuthor();
			System.out.println(" Enter the Subject of Book  : ");
			this.setBookSubject();
			System.out.println(" Enter the Price of Book    : ");
			this.setBookPrice();
			System.out.println(" Enter Number of Copies     :");
			this.setNoOfCopies();
		
		}catch(IOException e){
			System.out.println("IO Exception Caught: "+e);
		}catch(Exception e){
			System.out.println("Exception Caught: "+e);
		}finally{
			File file = new File("Books_Index.txt");
			FileWriter fw = new FileWriter(file, true);
			int count=Library.getObjCount();
			fw.write("\n Book Index ["+count+"] Contains Book Named: ["+getBookTitle()+"]");
			fw.close();
			saveData();
		}		
		
	}
	
	public void deleteBook()
	{
		fetchData();
		int status;
		try{
			System.out.println("_________________Delete Book_________________");
			System.out.println("Enter Title of Book to delete: ");
			String title = scan.readLine();
			
			for(int i=0;i<bookData.size();i++)
			{
				if(title.equalsIgnoreCase(bookData.get(i).getBookTitle()))
				{
					if(bookData.getBookId() == Library.getObjCount())
					{
						bookData.set(bookData.indexOf(i),null); 
						//changing the value to null so that index of books would be maintained
						// ...to avoid left shift
						break;
					}else{
						System.out.println("Internal Error Caught.[1] Index Mismatch");
						Library.main();
					}
				}
				else{
					status=1;
				}
			}
			if(status == 1)
			{
				System.out.println("Book Not Found.");
			}
			
		}catch(Exception e){
			System.out.println("Exception Caught: "+e);
		}finally{
			File file = new File("Books_Index.txt");
			FileWriter fw = new FileWriter(file, true);
			int count=Library.getObjCount();
			fw.write("\n Book Index ["+count+"] got deleted. Book Named: ["+getBookTitle()+"]");
			fw.close();
			System.out.println("Delete Operation Finished.");
			saveData();
		}
	}
	
	public void modifyBook()
	{
		fetchData();
		int status;
		try{
			System.out.println("_________________Modify Book_________________");
			System.out.println("Enter Title of Book to Modify: ");
			String title = scan.readLine();
			
			for(int i=0;i<bookData.size();i++)
			{
				if(title.equalsIgnoreCase(bookData.get(i).getBookTitle()))
				{
					if(bookData.getBookId() == Library.getObjCount())
					{
						System.out.println("Enter New Price of Book: ");
						int price =Double.parseDouble(scan.readLine());
						bookData.get(i).setBookPrice(price);
						System.out.println("Enter New Available copies of Book: ");
						int copies =Integer.parseInt(scan.readLine());
						bookData.get(i).setNoOfCopies(copies);
						break;
					}else{
						System.out.println("Internal Error Caught.[1] Index Mismatch");
						Library.main();
					}
				}
				else{
					status=1;
				}
			}
			if(status == 1)
			{
				System.out.println("Book Not Found.");
			}
			
		}catch(Exception e){
			System.out.println("Exception Caught: "+e);
		}finally{
			System.out.println("Modify Operation Finished.");
			saveData();
		}
	}
	
	public void searchBook()
	{
		fetchData();
		int status;
		try{
			System.out.println("_________________Search Book_________________");
			System.out.println("Enter Title of Book to Search: ");
			String title = scan.readLine();
			for(int i=0;i<bookData.size();i++)
			{
				if(title.equalsIgnoreCase(bookData.get(i).getBookTitle()))
				{
					if(bookData.getBookId() == Library.getObjCount())
					{
						System.out.println("Book Found at ["+i+"]");
						System.out.println("Displaying Book Data....");
						System.out.println("Book Title    : "+bookData.get(i).getBookTitle());
						System.out.println("Book Author   : "+bookData.get(i).getBookAuthor());
						System.out.println("Book Subject  : "+bookData.get(i).getBookSubject());
						System.out.println("Book Price    : "+bookData.get(i).getBookPrice());
						System.out.println("No. of Copies : "+bookData.get(i).getAvailableCopies());
						break;
					}else{
						System.out.println("Internal Error Caught.[1] Index Mismatch");
						Library.main();
					}
				}
				else{
					status=1;
				}
			}
			if(status == 1)
			{
				System.out.println("Book Not Found.");
			}
		
		}catch(Exception e){
			System.out.println("Exception Caught: "+e);
		}finally{
			System.out.println("Search Operation Finished.");
			saveData();
		}
	}
	
	public void issueBook()
	{
		fetchData();
		try{
			System.out.println("_________________Issue Book_________________");
			System.out.println("Enter Title of Book  :  ");
			String title = scan.readLine();
			System.out.println("Enter Name of Student:  ");
			String studName = scan.readLine();
			for(int i=0;i<bookData.size();i++)
			{
				if(title.equalsIgnoreCase(bookData.get(i).getBookTitle()))
				{
					if(bookData.getBookId() == Library.getObjCount())
					{
						Format formatter = new SimpleDateFormat("yyyy-MM-dd");
						StringBuffer date = formatter.format(date);
						i++;
						bookData.get(i).removeBookCopies();
						i++;
						bookData.get(i).setIssueDate(date); 
						
						File file = new File("issueLogs.txt");
						FileWriter fw = new FileWriter(file, true);
						fw.write("\n["+date+"] : Book Title : "+bookData.get(i).getBookTitle+" was Issued by Student Name"+studName);
						fw.close();
						break;
					}else{
						System.out.println("Internal Error Caught.[1] Index Mismatch");
						Library.main();
					}
				}
				else{
					status=1;
				}
			}
		}catch(Exception e){
			System.out.println("Exception Caught: "+e);
		}finally{
			System.out.println("Issue Operation Finished.");
			saveData();
		}
	}
	
	public void returnBook()
	{
		fetchData();
		try{
			System.out.println("_________________Return Book_________________");
			System.out.println("Enter Title of Book  :  ");
			String title = scan.readLine();
			System.out.println("Enter Name of Student:  ");
			String studName = scan.readLine();
			for(int i=0;i<bookData.size();i++)
			{
				if(title.equalsIgnoreCase(bookData.get(i).getBookTitle()))
				{
					if(bookData.getBookId() == Library.getObjCount())
					{
						Format formatter = new SimpleDateFormat("yyyy-MM-dd");
						StringBuffer date = formatter.format(date);
						i++;
						bookData.get(i).addBookCopies();
						i++;
						bookData.get(i).setIssueDate(date); 
						
						File file = new File("returnLogs.txt");
						FileWriter fw = new FileWriter(file, true);
						fw.write("\n["+date+"] : Book Title : "+bookData.get(i).getBookTitle+" was Returned by Student Name"+studName);
						fw.close();
					}else{
						System.out.println("Internal Error Caught.[1] Index Mismatch");
						Library.main();
					}
				}
				else{
					status=1;
				}
			}
		}catch(Exception e){
			System.out.println("Exception Caught: "+e);
		}finally{
			System.out.println("Return Operation Finished.");
			saveData();
		}
		
	}
}








