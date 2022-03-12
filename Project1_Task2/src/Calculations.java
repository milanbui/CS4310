/*********************************************************************************************
 * Name      : Milan Bui
 * Date      : 18 March 2022
 * Class     : CS 4310.01
 * Assignment: Project 1 - Task #2
 * File Name : Calculations.java
 ********************************************************************************************/
import java.util.ArrayList;
import java.util.Collections;

public class Calculations {
	
	/*****************************************************************************************
	 * calcCPUUtilization
	 * Calculates the CPU utilization rate.
	 * @param process  the list of processes (used to find total time)
	 * @param idleTime time CPU is not being used
	 * @return rate    the CPU utilization rate
	 ****************************************************************************************/
	public static double calcCPUUtilization(ArrayList<Process> process, int idleTime) {
		
		ArrayList<Process> copy = new ArrayList<Process>(); // copy of list
		
		// Copies list of processes
		for(int i = 0; i < process.size(); i++) {
			copy.add(new Process(process.get(i)));
		}
		
		// Sorts list of processes in descending order based on end time to find the end time
		// of the final running process
		Collections.sort(copy, new SortByEndTime());
		
		// total time = end time of last running process
		double totalTime = copy.get(0).getEndTime();
		
		double rate = ((totalTime - idleTime) / totalTime) * 100;
		
		return rate;
		
	}
	
	/*****************************************************************************************
	 * calcAvgWaitingTime
	 * Calculates the average waiting time
	 * @param  process     list of processes
	 * @return avgWaitTime average wait time
	 ****************************************************************************************/
	public static double calcAvgWaitingTime(ArrayList<Process> process) {
		
		double sum = 0;      // sum of wait times per process
		double avgWaitTime;  // avg wait time
		
		// For all processes, calculate wait time and add to sum of wait times
		for(int i = 0; i < process.size(); i++) {
			
			Process current = process.get(i); 
			int waitTime = current.getEndTime() - current.getArrivalTime() - current.getBurstTime();
			sum += waitTime;
		}
		
		avgWaitTime = sum / process.size();   // divide sum by number of processes
		
		return avgWaitTime;
		
	}
	
	/****************************************************************************************
	 * calcAvgResponseTime
	 * Calculates the average response time (time between arrival and when first uses CPU)
	 * @param process the list of processes
	 * @return avgTime average response time
	 ****************************************************************************************/
	public static double calcAvgResponseTime(ArrayList<Process> process) {
		
		double sum = 0;   // sum of response times
		double avgTime;   // avg response time
		
		// for all processes, calculate response time and add to sum
		for(int i = 0; i < process.size(); i++) {
			
			Process current = process.get(i);
			int respTime = current.getStartTime() - current.getArrivalTime();
			sum += respTime;
		}
		
		avgTime = sum / process.size(); // divide by number of processes
		
		return avgTime;
		
		
	}
	
	/*****************************************************************************************
	 * calcAvgTurnTime
	 * Calculate the average turnaround time.
	 * @param process list of processes
	 * @return avgTime the average turn around time
	 ****************************************************************************************/
	public static double calcAvgTurnTime(ArrayList<Process> process) {
		
		double sum = 0;  // sum of turn around times of all processes
		double avgTime;  // avg turn around time
		
		// for all processes, calculate the turn around time and add to the sum
		for(int i = 0; i < process.size(); i++) {
			Process current = process.get(i);
			int time = current.getEndTime() - current.getArrivalTime();
			sum += time;
		}
		
		avgTime = sum / process.size();  // divide sum by number of processes
		
		return avgTime;
		
		
	}
}
