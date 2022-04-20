/*********************************************************************************************
 * Name      : Milan Bui
 * Date      : 29 April 2022
 * Class     : CS 4310.01
 * Assignment: Project 2 - Task #3
 *      Write a program that is passed: number of sectors, number of tracks, number of 
 *      cylinders of a given hard disk and a logical block number (all in decimals) and have 
 *      it output the logical block number in <Cylinder#, Track# , Sector #> format.
 ********************************************************************************************/
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
	public static void main(String[] args) {
		
		// VARIABLES
		int blockNumber;      // logical block number
		int numOfCylinders;  
		int numOfTracks;      
		int numOfSectors;
		int cylinderNum;     // cylinder number of logical block number location
		int trackNum;        // track number of logical block number location
		int sectorNum;       // sector number of logical block number location
		
		Scanner in = new Scanner(System.in);
		
		// Displays program header
		System.out.print(String.format("%40s", "-").replace(' ', '-'));
		System.out.print("TASK #2");
		System.out.println(String.format("%40s", "-").replace(' ', '-'));
		
		try {
			
			boolean isInvalid;
			
			// Do while input is invalid (nums out of range)
			do {

				isInvalid = false;
				
				// Prompt user for input
				System.out.print("Enter a logical block number: ");
				blockNumber = in.nextInt();
				
				System.out.print("Enter HD number of cylinders: ");
				numOfCylinders = in.nextInt();
				
				System.out.print("Enter HD number of tracks   : ");
				numOfTracks = in.nextInt();
				
				System.out.print("Enter HD number of sectors  : ");
				numOfSectors = in.nextInt();
				
				System.out.println();
				
				if(numOfCylinders <= 0 || numOfTracks <= 0 || numOfSectors <= 0 || blockNumber < 0) {
					isInvalid = true;
					System.out.println("Invalid input. Block number must be non-negative. "
							           + "Other values must be greater than zero.\n");
				}
				else {

					// Find locations
					// each cylinder has x num of tracks that each have y num of sectors
					// divide the block number to find the cylinder
					cylinderNum = blockNumber / (numOfTracks * numOfSectors);
					
					// the remainder from the result above is taken. each track has x num of 
					// sectors, so divide by remainder to find track
					trackNum = (blockNumber % (numOfTracks * numOfSectors)) / numOfSectors;
					
					// whatever is leftover is the sector number
					sectorNum = (blockNumber % (numOfTracks * numOfSectors)) 
							    - (trackNum * numOfSectors);
					
					// Prints results
					System.out.println("The logical block number " + blockNumber 
							           + " is located at < " + cylinderNum + ", " + trackNum 
							           + ", " + sectorNum + " >");
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
