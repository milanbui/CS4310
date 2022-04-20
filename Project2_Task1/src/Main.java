/*********************************************************************************************
 * Name      : Milan Bui
 * Date      : 29 April 2022
 * Class     : CS 4310.01
 * Assignment: Project 2 - Task #1
 *      Assume that a system has a 32-bit virtual address. Write a program that 
 *      will be passed a virtual address (in decimal) and page size in KB from 
 *      console and have it output the page number and offset for the given address.
 ********************************************************************************************/
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
	public static void main(String[] args) {
		
		// VARIABLES
		
		// Virtual address space
		final int VA_BITS_NUM = 32;      // n number of bits to determine size of space
		final long VA_SIZE = (long) Math.pow(2, VA_BITS_NUM); // the size of the va space
		
		long pageSize;         // IN
		long virtualAddress;   // IN
		int pageNumber;        // CALC/OUT
		long offset;           // CALC/OUT
		
		Scanner in = new Scanner(System.in);
		
		// Displays program header
		System.out.print(String.format("%40s", "-").replace(' ', '-'));
		System.out.print("TASK #1");
		System.out.println(String.format("%40s", "-").replace(' ', '-'));
		
		try {
			
			boolean isInvalid;
			
			// Do while input is invalid (nums out of range)
			do {

				isInvalid = false;
				
				// Prompts user for input
				System.out.print("Enter page size (in KB): ");
				pageSize = in.nextInt();
				
				System.out.print("Enter a virtual address: ");
				virtualAddress = in.nextLong();
	
				System.out.print("\n");
				
				pageSize = pageSize * 1024;   // converts page size to bytes from kb
				
				
				if(virtualAddress >= VA_SIZE || virtualAddress < 0) { 
					
					isInvalid = true;
					System.out.println("Invalid virtual address. Out of range [0 - " 
					                   + VA_SIZE + "].\n");
					
				}
				else if(pageSize > VA_SIZE || pageSize <= 0) {
					
					isInvalid = true;
					System.out.println("Invalid page size. Out of range.\n");
					
				}
				else {
					
					pageNumber = (int) (virtualAddress / pageSize);
					offset = virtualAddress - (pageNumber * pageSize);
					
					// Prints results
					System.out.println("The address " + virtualAddress + " contains: page "
							           + "number " + pageNumber + " and offset " + offset);
					 
				}
			}while(isInvalid);
			
			in.close();
			
		}
		catch(InputMismatchException e) {
			System.err.println(e.getMessage());
		}
		catch(Exception e) {
			System.err.println(e.getMessage() + "\n" + e.getCause() + "\n" 
								+ e.getStackTrace());
			e.printStackTrace();
		}
		
		
	}

}
