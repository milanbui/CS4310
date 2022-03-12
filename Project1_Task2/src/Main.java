import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
	public static void main(String[] args) {
		
		String inputFileName;
		File    inFile;
		Scanner userIn;
		Scanner fileIn;
		ArrayList<Process> processes;
		
		userIn = new Scanner(System.in);
		System.out.print(String.format("%40s", "-").replace(' ', '-'));
		System.out.print(" CPU Scheduling Simulator ");
		System.out.println(String.format("%40s", "-").replace(' ', '-'));
		
		try {
			System.out.print("\nPlease enter the path to the input file: ");
			inputFileName = userIn.nextLine();
			
			inFile = new File(inputFileName);
			fileIn = new Scanner(inFile);
			
			processes = new ArrayList<Process>();
			Process process;

			while(fileIn.hasNext()) {
				
				String line = fileIn.nextLine();
				String[] data = line.split(" ");
				
				process = new Process();
				process.pid         = Integer.parseInt(data[0]);
				process.arrivalTime = Integer.parseInt(data[1]);
				process.burstTime   = Integer.parseInt(data[2]);
				process.priority    = Integer.parseInt(data[3]);
				process.remainingTime = process.burstTime;
				
				processes.add(process);
				
			}
			
			fileIn.close();
			
			int selection;
			
			do {
		
				System.out.println("\nPlease Select a CPU Scheduling Algorithm: ");
				System.out.println("1. First Come First Serve (FCFS)");
				System.out.println("2. Shortest Job First (SJF)");
				System.out.println("3. Preemptive Priority Scheduling");
				System.out.println("4. Round Robin");
				System.out.println("5. exit");
				System.out.print("Enter selection number: ");
				
				selection = userIn.nextInt();
				
				ArrayList<Process> copy = new ArrayList<Process>();
				for(int i = 0; i < processes.size(); i++) {
					copy.add(new Process(processes.get(i)));
				}
				
				switch(selection) {
				case 1:
					SchedulingAlgorithms.fcfs(copy);
					break;
				case 2:
					SchedulingAlgorithms.sjf(copy);
					break;
				case 3:
					SchedulingAlgorithms.preemptivePriority(copy);
					break;
				case 4:
					int timeQuantum;
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
