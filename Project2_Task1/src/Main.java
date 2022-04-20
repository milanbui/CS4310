import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
	public static void main(String[] args) {
		
		final int VA_BITS_NUM = 32;
		final long VA_SIZE = (long) Math.pow(2, VA_BITS_NUM);
		
		long pageSize;
		long virtualAddress;
		int pageNumber;
		long offset;
		
		Scanner in = new Scanner(System.in);
		
		// Displays program header
		System.out.print(String.format("%40s", "-").replace(' ', '-'));
		System.out.print("TASK #1");
		System.out.println(String.format("%40s", "-").replace(' ', '-'));
		
		try {
			
			boolean isInvalid;
			
			do {

				isInvalid = false;
				System.out.print("Enter page size (in KB): ");
				pageSize = in.nextInt();
				
				System.out.print("Enter a virtual address: ");
				virtualAddress = in.nextLong();
	
				System.out.print("\n");
				
				pageSize = pageSize * 1024;
				
				if(virtualAddress >= VA_SIZE || virtualAddress < 0) {
					isInvalid = true;
					System.out.println("Invalid virtual address. Out of range [0 - " + VA_SIZE + "].\n");
				}
				else if(pageSize > VA_SIZE || pageSize <= 0) {
					isInvalid = true;
					System.out.println("Invalid page size. Out of range.\n");
				}
				else {
					
					pageNumber = (int) (virtualAddress / pageSize);
					offset = virtualAddress - (pageNumber * pageSize);
					
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
