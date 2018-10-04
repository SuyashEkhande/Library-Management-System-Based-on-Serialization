/*
		Library Management System by - ( Suyash + Shubham + Manasi + Jyoti + Chinmay)		
*/
package LibraryManagement;

import java.util.*;
import java.io.*;

public class Library extends Books 
{	
	static int objCount=0;
	BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
	
	public static int getObjCount()
	{
		return objCount;
	}
	
	public static void main(String a[])
	{
		
		System.out.println("Select From Below Options: ");
		System.out.println("1. Add Book\n2. Modify Book\n3. Delete Book\n4. Search Book\n5. Issue Book\n6. Return Book\n7. LogOut");
		System.out.println("Enter Your Choice: ");
		int choice = Integer.parseInt(scan.readLine());
		
		while(choice!=7)
		{
			switch(choice)
			{
				case 1:
					{
						bookData.add(new Books());
						bookData[objCount].addBook();
						objCount++;
						break;
					}
				case 2:
					{
						System.out.println("Enter Index of Book you want to Modify: ");
						int index=Integer.parseInt(readLine());
						bookData[index].modifyBook();
						break;
					}
				case 3:
					{
						System.out.println("Enter Index of Book you want to Delete: ");
						int index=Integer.parseInt(readLine());
						bookData[index].deleteBook();
						break;
					}
				case 4:
					{
						System.out.println("Enter Index of Book you want to Search: ");
						int index=Integer.parseInt(readLine());
						bookData[index].searchBook();
						break;
					}
				case 5:
					{
						System.out.println("Enter Index of Book you want to Issue: ");
						int index=Integer.parseInt(readLine());
						bookData[index].issueBook();
						break;
					}
				case 6:
					{
						System.out.println("Enter Index of Book you want to Return: ");
						int index=Integer.parseInt(readLine());
						bookData[index].returnBook();
						break;
					}
				case 7:
					{
						System.exit(0);
						break;
					}
			}
		}
	}
}