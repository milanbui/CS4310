/*******************************************************************************
 * Name      : Milan Bui
 * Date      : 18 March 2022
 * Class     : CS 4310.01
 * Assignment: Project 1 - Task #2
 *      Simulates a CPU scheduler using first come first serve, shortest job 
 *      first, preemptive priority, and round robin. Prompts user for absolute
 *      path to input file and selection for scheduling algorithm. Displays
 *      status of processes/CPU each ms.
 ******************************************************************************/
import java.util.ArrayList;
import java.util.Collections;  // for sort
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
	public static void main(String[] args) {
		
		// VARIABLES
		String inputFileName;
		File    inFile;
		Scanner userIn;  // user input stream
		Scanner fileIn;  // file input stream
		int selection;   // user selection from menu
		ArrayList<Process> processes;  // holds list of processes read from file

		userIn = new Scanner(System.in);
		
		// Displays program header
		System.out.print(String.format("%40s", "-").replace(' ', '-'));
		System.out.print(" CPU Scheduling Simulator ");
		System.out.println(String.format("%40s", "-").replace(' ', '-'));
		
		// Try-catch to catch file error
		try {
			
			// INPUT - prompts user for input file path
			System.out.print("\nPlease enter the path to the input file: ");
			inputFileName = userIn.nextLine();
			
			// Creates file input stream
			inFile = new File(inputFileName);
			fileIn = new Scanner(inFile);
			
	
			processes = new ArrayList<Process>();
			Process process;

			// INPUT - reads file
			while(fileIn.hasNext()) {
				
				String line = fileIn.nextLine();  // line from file
				String[] data = line.split(" ");  // splits line to obtain data
				
				// Creates new process using data from read line
				process = new Process(Integer.parseInt(data[0]), Integer.parseInt(data[1]), 
						              Integer.parseInt(data[2]), Integer.parseInt(data[3]));
				
				// adds process to list of processes 
				processes.add(process);
				
			}
			
			// Done reading file, so closes input stream
			fileIn.close();
		
			// Do while user has not entered 5 to exit
			do {
		
				// OUTPUT - displays menu
				System.out.println("\nPlease Select a CPU Scheduling Algorithm: ");
				System.out.println("1. First Come First Serve (FCFS)");
				System.out.println("2. Shortest Job First (SJF)");
				System.out.println("3. Preemptive Priority Scheduling");
				System.out.println("4. Round Robin");
				System.out.println("5. exit");
				System.out.print("Enter selection number: ");
				
				// Obtains user's selection
				selection = userIn.nextInt();
				
				// Creates copy of processes (so can reuse without changing data)
				Collections.sort(processes, new SortByArrival());
				ArrayList<Process> copy = new ArrayList<Process>();
				
				for(int i = 0; i < processes.size(); i++) {
					copy.add(new Process(processes.get(i)));
				}
				
				
				switch(selection) {
				case 1: // FCFS
					SchedulingAlgorithms.fcfs(copy);
					break;
				case 2: // SJF
					SchedulingAlgorithms.sjf(copy);
					break;
				case 3: // PREEMPTIVE PRIORITY
					SchedulingAlgorithms.preemptivePriority(copy);
					break;
				case 4: // ROUND ROBIN
					int timeQuantum;
					// Prompts user for time quantum
					System.out.print("\nPlease enter the time quantum: ");
					timeQuantum = userIn.nextInt();
					
					SchedulingAlgorithms.roundRobin(copy, timeQuantum);
					break;
				case 5:
					System.out.println("\nProgram Terminated");
					break;
				default:
					break;
				}
				
			} while(selection != 5);
			
			// Program terminated, close user input
			userIn.close();
			
		}
		catch(FileNotFoundException e) {
			System.err.println(e.getMessage());
		}
		catch(Exception e) {
			System.err.println(e.getMessage() + "\n" + e.getCause() + "\n" 
								+ e.getStackTrace());
			e.printStackTrace();
		}
		
	}
}
