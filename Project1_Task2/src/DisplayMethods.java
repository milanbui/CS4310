/*********************************************************************************************
 * Name      : Milan Bui
 * Date      : 18 March 2022
 * Class     : CS 4310.01
 * Assignment: Project 1 - Task #2
 * File Name : DisplayMethods.java
 ********************************************************************************************/
import java.util.ArrayList;

public class DisplayMethods {

	/*****************************************************************************************
	 * displayStats
	 * Displays the average wait time, average response time, average turn around time, and 
	 * the CPU rate.
	 * @param awt      average wait time
	 * @param art      average response time
	 * @param att      average turn around time
	 * @param cpuRate  CPU utilization rate
	 ****************************************************************************************/
	public static void displayStats(double awt, double art, double att, double cpuRate) {
		
		System.out.println();
		System.out.println(String.format("Average Waiting Time   : %.4f ms", awt));
		System.out.println(String.format("Average Response Time  : %.4f ms", art));
		System.out.println(String.format("Average Turnaround Time: %.4f ms", att)); 
		System.out.println(String.format("CPU Utilization Rate   : %.4f %%", cpuRate)); 
		
	}
	
	/*****************************************************************************************
	 * displayProgress
	 * Displays the status of the processes at the given time
	 * @param process  list of processes
	 * @param time     current CPU time
	 * @param idleTime time CPU is idle (not processing a task)
	 ****************************************************************************************/
	public static void displayProgress(ArrayList<Process> process, int time, int idleTime) {
		
		int size = process.size();

		// OUT - displays the header of the table
		System.out.println(String.format("\n\n%-63s", "-").replace(' ', '-'));
		System.out.println("TIME: " + time + " ms | IDLE TIME: " + 
							idleTime + " ms");
		System.out.println(String.format("%-63s", "-").replace(' ', '-'));
		System.out.print(String.format("%-5s" , "PID"));
		System.out.print(String.format("%-16s", "Arrival Time"));
		System.out.print(String.format("%-14s", "Burst Time"));
		System.out.print(String.format("%-18s", "Remaining Time"));
		System.out.print(String.format("%-10s", "Status"));
		System.out.println();
		System.out.println(String.format("%-63s", "-").replace(' ', '-'));
		
		// for all processes
		for(int i = 0; i < size; i++) {
			
			// if process has arrived by the given time, display info
			if(process.get(i).getArrivalTime() <= time) {
				
				System.out.print(String.format("%-5d" , process.get(i).getPid()));
				System.out.print(String.format("%-16d", process.get(i).getArrivalTime()));
				System.out.print(String.format("%-14d", process.get(i).getBurstTime()));
				System.out.print(String.format("%-18d", process.get(i).getRemainingTime()));
				System.out.print(String.format("%-10s", process.get(i).getStatus().toString()));
				System.out.println();
				
			}
			
		}
		

		System.out.println(String.format("%-63s", "-").replace(' ', '-'));
	}
	
	/*****************************************************************************************
	 * ppDisplayProgress
	 * Displays progress of preemptive priority at given time. (same as other display function
	 * but includes priority in table)
	 * @param process  list of processes
	 * @param time     current CPU time
	 * @param idleTime time CPU has been idle (no processes running)
	 */
	public static void ppDisplayProgress(ArrayList<Process> process, int time, int idleTime){
		
		int size = process.size();

		// OUT - header for table
		System.out.println(String.format("\n\n%-75s", "-").replace(' ', '-'));
		System.out.println("TIME: " + time + " ms | IDLE TIME: " + 
							idleTime + " ms");
		System.out.println(String.format("%-75s", "-").replace(' ', '-'));
		System.out.print(String.format("%-5s" , "PID"));
		System.out.print(String.format("%-16s", "Arrival Time"));
		System.out.print(String.format("%-14s", "Burst Time"));
		System.out.print(String.format("%-18s", "Remaining Time"));
		System.out.print(String.format("%-12s", "Priority"));
		System.out.print(String.format("%-10s", "Status"));
		System.out.println();
		System.out.println(String.format("%-75s", "-").replace(' ', '-'));
		
		// for all processes
		for(int i = 0; i < size; i++) {
			
			// if processes has arrived, display info including priority
			if(process.get(i).getArrivalTime() <= time) {
				
				System.out.print(String.format("%-5d" , process.get(i).getPid()));
				System.out.print(String.format("%-16d", process.get(i).getArrivalTime()));
				System.out.print(String.format("%-14d", process.get(i).getBurstTime()));
				System.out.print(String.format("%-18d", process.get(i).getRemainingTime()));
				System.out.print(String.format("%-12d", process.get(i).getPriority()));
				System.out.print(String.format("%-10s", process.get(i).getStatus().toString()));
				System.out.println();
				
			}
			
		}

		System.out.println(String.format("%-75s", "-").replace(' ', '-'));
	}
}
