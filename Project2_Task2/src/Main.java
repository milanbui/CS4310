/*********************************************************************************************
 * Name      : Milan Bui
 * Date      : 29 April 2022
 * Class     : CS 4310.01
 * Assignment: Project 2 - Task #2
 *      Your program will service a disk with 5,000 cylinders numbered 0 to 4,999. The program 
 *      will be passed the initial position of the disk head (as a parameter on the command 
 *      line) and report the total amount of head movement and total number of change of 
 *      direction required by each algorithm under each of the following cases:
 *      > a. The program will generate a random series of 1,000 cylinder requests and service 
 *           them according to each of the algorithms listed above.
 *      > b. The program will read a series of cylinder requests from an input.txt file and 
 *           service them according to each of the algorithms listed above.
 ********************************************************************************************/
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;


public class Main {
	public static void main(String[] args) {
		
		// VARIABLES
		final int NUM_OF_CYLINDERS = 200;
//		final int HEAD_POSITION = Integer.valueOf(args[0]);
//		final int PREV_POSITION = Integer.valueOf(args[1]);
		final int HEAD_POSITION = 53;
		final int PREV_POSITION = 30;
		int[] fcfsResult;
		int[] sstfResult;
		int[] scanResult;
		int[] cscanResult;
		int selection;
		int fileNumber = 1;
		ArrayList<Integer> requests = new ArrayList<Integer>();
		Scanner userIn = new Scanner(System.in);
		
		// Displays program header
		System.out.print(String.format("%40s", "-").replace(' ', '-'));
		System.out.print("TASK #2");
		System.out.println(String.format("%40s", "-").replace(' ', '-'));
		System.out.println();
		
		// menu - random or file input
		// >> if file input, prompt for file name (try catch)
		
		System.out.println(NUM_OF_CYLINDERS + " cylinders");
		System.out.println("Current head position : " + HEAD_POSITION);
		System.out.println("Previous head position: " + PREV_POSITION);
		System.out.println();
		
		try {
			do {
				System.out.println("How would you like to do?");
				System.out.println("1. Generate 1000 randomized requests");
				System.out.println("2. Read requests from input file");
				System.out.println("3. Exit");
				System.out.print("Enter selection: ");
				selection = userIn.nextInt();
				
				if (selection == 1 || selection == 2) {
					if (selection == 1) {
						String fileName = "RandomizedRequests" + fileNumber + ".txt";
						File randomFile = new File(fileName);
						randomFile.createNewFile();
						FileWriter writer = new FileWriter(fileName);
					      
						Random rand = new Random();
						requests = new ArrayList<Integer>();

						for (int i = 0; i < 1000; i++) {

							int num = rand.nextInt(NUM_OF_CYLINDERS);
							requests.add(num);
							writer.write(String.format("%-6d", i) + "| " + Integer.toString(num) + "\n");
						}
						
						writer.close();
						fileNumber++;

					} else if (selection == 2) {

						String filePath;
						System.out.print("Enter full path to input file: ");
						filePath = userIn.nextLine();

						File inputFile = new File(filePath);
						Scanner fileIn = new Scanner(inputFile);
						requests = new ArrayList<Integer>();

						while (fileIn.hasNext()) {

							String line = fileIn.nextLine();
							int num = Integer.valueOf(line);
							requests.add(num);

						}
						
						fileIn.close();
					}

					SchedulingAlgorithms disk = new SchedulingAlgorithms(HEAD_POSITION, PREV_POSITION, NUM_OF_CYLINDERS,
							requests);

					fcfsResult = disk.fcfs();
					sstfResult = disk.sstf();
					scanResult = disk.scan();
					cscanResult = disk.cscan();

					System.out.print(String.format("%-12s", "Algorithm"));
					System.out.print(String.format("%-23s", "Total Head Movements"));
					System.out.print(String.format("%-30s", "Total Direction Changes"));
					System.out.println();
					System.out.println(String.format("%-65s", "-").replace(' ', '-'));

					System.out.print(String.format("%-12s", "FCFS"));
					System.out.print(String.format("%-25d", fcfsResult[0]));
					System.out.println(String.format("%-30s", fcfsResult[1]));

					System.out.print(String.format("%-12s", "SSTF"));
					System.out.print(String.format("%-25d", sstfResult[0]));
					System.out.println(String.format("%-30s", sstfResult[1]));

					System.out.print(String.format("%-12s", "SCAN"));
					System.out.print(String.format("%-25d", scanResult[0]));
					System.out.println(String.format("%-30s", scanResult[1]));

					System.out.print(String.format("%-12s", "C-SCAN"));
					System.out.print(String.format("%-25d", cscanResult[0]));
					System.out.println(String.format("%-30s", cscanResult[1]));
				} else if (selection == 3){
					System.out.println("Program Terminated");
				} 
				else {
					System.out.println("Invalid selection");
				}
			} while(selection != 3);
			
			userIn.close();
		} catch (NumberFormatException e) {
			
			System.err.println(e.getMessage());
			
		} catch (FileNotFoundException e) {
			
			System.err.println(e.getMessage());
			
		} catch (Exception e) {
			System.err.println(e.getMessage() + "\n" + e.getCause() + "\n" 
					+ e.getStackTrace());
			e.printStackTrace();
		}
		
	}
}
