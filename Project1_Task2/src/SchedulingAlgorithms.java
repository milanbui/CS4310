/*********************************************************************************************
 * Name      : Milan Bui
 * Date      : 18 March 2022
 * Class     : CS 4310.01
 * Assignment: Project 1 - Task #2
 * File Name : SchedulingAlgorithms.java
 ********************************************************************************************/
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class SchedulingAlgorithms {
	
	/*****************************************************************************************
	 * fcfs
	 * First come first serve CPU scheduling algorithm. 
	 * @param  process  the list of processes 
	 * @return idleTime the time in ms when the CPU is not processing.
	 ****************************************************************************************/
	public static int fcfs(ArrayList<Process> process) {
		
		int idleTime = 0;         // idle time in ms
		int processNum = 0;       // the index of the process
		int time = 0;             // time from 0 in ms
		Queue<Process> queue = new LinkedList<Process>();   // wait queue
		
		System.out.println("\n\n\nFIRST COME FIRST SERVE (FCFS)");
		
		// while there are processes
		while(processNum < process.size() || !queue.isEmpty()) {
			
			// while there are processes arriving at the given time, add to wait queue
			while(processNum < process.size() && process.get(processNum).getArrivalTime() == time){
				
				queue.add(process.get(processNum));
				processNum++;
				
			}
			
			// If queue is not empty and a process is active, decrement remaining time
			if(!queue.isEmpty() && queue.peek().getStatus() == Status.PROCESSING) {
				
				queue.peek().decrementRemTime();
				
				// If there is not remaining time (process is done)
				if(queue.peek().getRemainingTime() == 0) {
					
					// Set status to terminate, set end time to current time, and remove from
					// queue
					queue.peek().setStatus(Status.TERMINATED);
					queue.peek().setEndTime(time);
					queue.remove();
				}
			}
			
			// If queue is still not empty and process at top of queue is waiting
			if(!queue.isEmpty() && queue.peek().getStatus() == Status.WAITING) {
	
				// Set start time to current time and set status to processing
				queue.peek().setStartTime(time);
				queue.peek().setStatus(Status.PROCESSING);
			}
			// else if queue is empty, increment idle time
			else if(queue.isEmpty() && processNum < process.size()) {  
				idleTime++;
			}
			
			
			// Display process progress at current time
			// Shows pid, arrival time, burst time, remaining time, idle time, the current 
			// time, and the status
			DisplayMethods.displayProgress(process, time, idleTime);
			
			// increment time
			time++;
						
		}
		
		// returns idle time
		return idleTime;
	}
	
	/*****************************************************************************************
	 * sjf
	 * Shortest job first CPU scheduling algorithm. (Non preemptive)
	 * @param  process  the list of processes
	 * @return idleTime time CPU is not processing a process
	 ****************************************************************************************/
	public static int sjf(ArrayList<Process> process) {
		
		// Variables
		int idleTime = 0;     // time a process is not running and CPU is idle
		int processNum = 0;   // the index for the process array list
		int time = 0;         // CPU time in ms
		
		// Wait queue, sorted by burst time (shortest time at top) 
		PriorityQueue<Process> waitQueue = new PriorityQueue<Process>(process.size(), 
				                                                      new SortByBurst());
		Queue<Process> activeProcess = new LinkedList<Process>(); // the active process
		
		System.out.println("\n\n\nSHORTEST JOB FIRST (SJF)");
		
		// While there are processes
		while(processNum < process.size() || !activeProcess.isEmpty()) {
			
			// While there are processes arriving at the current time, add to wait queue
			while(processNum < process.size() && process.get(processNum).getArrivalTime() == time){
				waitQueue.add(process.get(processNum));
				processNum++;
			}
			
			// If there is an active process, decrement remaining time
			if(!activeProcess.isEmpty() && activeProcess.peek().getStatus() == Status.PROCESSING) {
				
				activeProcess.peek().decrementRemTime();
				
				// If there is no remaining time (process is done)
				if(activeProcess.peek().getRemainingTime() == 0) {
					
					// set status to terminated, set end time to current time, and remove from
					// active process queue
					activeProcess.peek().setStatus(Status.TERMINATED);
					activeProcess.peek().setEndTime(time);
					activeProcess.remove();
					
				}
			}
			
			// If there is not active process
			if(activeProcess.isEmpty()) {
				
				// If there are processes in the wait queue
				if(!waitQueue.isEmpty()) {
					
					// Add next process to active process and remove to wait queue
					activeProcess.add(waitQueue.poll());
					
					// Set start time to current time and set status to processing
					activeProcess.peek().setStartTime(time);
					activeProcess.peek().setStatus(Status.PROCESSING);
					
				}
				// Else if there are no processes waiting, increment idle time
				else if(processNum < process.size()) {
					idleTime++;
				}
			}

			// Display process progress at current time
			// Shows pid, arrival time, burst time, remaining time, idle time, the current 
			// time, and the status
			DisplayMethods.displayProgress(process, time, idleTime);
			
			// Increment time
			time++;
						
		}
	
		return idleTime;
		
	}

	/*****************************************************************************************
	 * preemptivePriority
	 * Preemptive priority CPU scheduling algorithm. The lower the number, the higher the
	 * priority.
	 * @param  process  the list of processes
	 * @return idleTime the time the CPU is idle (not processing a task)
	 ****************************************************************************************/
	public static int preemptivePriority(ArrayList<Process> process) {
		
		// Variables
		int processNum = 0;   // the index for the process array list
		int time = 0;         // CPU time
		int pid = -9999;      // pid of previously running process
		int idleTime = 0;     // time CPU is idle
		
		// wait queue, sorted by priority
		PriorityQueue<Process> queue = new PriorityQueue<Process>(process.size(), 
				                                                  new SortByPriority());
		
		System.out.println("\n\n\nPREEMPTIVE PRIORITY");
		
		// while there are processes
		while(processNum < process.size() || !queue.isEmpty()) {
		
			// If queue is not empty and process is active
			if(!queue.isEmpty() && queue.peek().getStatus() == Status.PROCESSING) {
				
				// decrement remaining time
				queue.peek().decrementRemTime();
				
				// If no more remaining time (process dont)
				if(queue.peek().getRemainingTime() == 0) {
					
					// Set status to terminated, set end time to current time, remove from queue
					queue.peek().setStatus(Status.TERMINATED);
					queue.peek().setEndTime(time);
					queue.remove();
				}
			}
			
			// While there are processes arriving at the current time, add to queue
			while(processNum < process.size() && process.get(processNum).getArrivalTime() == time){
				queue.add(process.get(processNum));
				processNum++;
			}
			
			// If wait queue is not empty and process with higher priority enters the queue
			// (new top of queue pid is not same as previous top of queue pid),
			// set previously running process status to waiting. 
			if(!queue.isEmpty() && pid != queue.peek().getPid()) {
				setWaiting(process, pid);
			}
			
			// If queue is still not empty, there are waiting processes
			if(!queue.isEmpty() && queue.peek().getStatus() == Status.WAITING) {

				// set starting time to current time and set status to processing
				queue.peek().setStartTime(time);
				queue.peek().setStatus(Status.PROCESSING);
				
				// set pid to currently running pid
				pid = queue.peek().getPid();
			}
			// Else if there are no processes in the wait queue but there are more processes
			// in the file that haven't arrived, increment idle time
			else if(queue.isEmpty() && processNum < process.size()) {
				idleTime++;
			}
			
			// Display process progress at current time
			// Shows pid, arrival time, burst time, remaining time, idle time, the current 
			// time, priority, and the status						
			DisplayMethods.ppDisplayProgress(process, time, idleTime);
			
			// Increment time
			time++;
						
		}
	
		return idleTime;
		
	}

	
	/*****************************************************************************************
	 * roundRobin
	 * Round robin CPU scheduling algorithm.
	 * @param  process     the list of processes
	 * @param  timeQuantum the time frame each process gets when it's their turn
	 * @return idleTime    the time the CPU is idle 
	 ****************************************************************************************/
	public static int roundRobin(ArrayList<Process> process, int timeQuantum) {
		
		// Variables
		int idleTime = 0;        // CALC/OUT - time CPU is idle
		int time = 0;            // CPU time
		int processNum = 0;      // index of processes array list
		int currentQuantum = 0;  // time passed in current turn 
		

		Queue<Process> queue = new LinkedList<Process>();

		System.out.println("\n\n\nROUND ROBIN");
		
		// while there are processes
		while(processNum < process.size() || !queue.isEmpty()) {
			
			// while there are processes arriving at the current time, add to wait queue
			while(processNum < process.size() && process.get(processNum).getArrivalTime() == time){
				queue.add(process.get(processNum));
				processNum++;
			}
			
			// If there is a process running
			if(!queue.isEmpty() && queue.peek().getStatus() == Status.PROCESSING) {
				
				// Decrement remaining time and increment the time used
				queue.peek().decrementRemTime();
				currentQuantum++;
				
				// If the process is done (no more remaining time) or the time quantum is over
				if(currentQuantum == timeQuantum || queue.peek().getRemainingTime() == 0) {
					
					// If process is not done, set status to waiting and move to end of queue
					if(queue.peek().getRemainingTime() > 0) {
						queue.peek().setStatus(Status.WAITING);
						queue.add(queue.poll());
					}
					// Else set status to terminated, set end time to current time, and remove
					// from queue
					else {
						queue.peek().setStatus(Status.TERMINATED);
						queue.peek().setEndTime(time);
						queue.remove();
					}
					
					// Set current time used from quantum back to 0
					currentQuantum = 0;
				}
			}
			
			// If there are processes waiting in the queue, set start time to current time
			// and set status to processing
			if(!queue.isEmpty() && queue.peek().getStatus() == Status.WAITING) {

				if(queue.peek().getRemainingTime() ==  queue.peek().getBurstTime()) {
					queue.peek().setStartTime(time);
				}
						
				queue.peek().setStatus(Status.PROCESSING);
			}
			// else if no processes waiting, but more processes to arrive in future,
			// increment idle time
			else if(queue.isEmpty() && processNum < process.size()) {
				idleTime++;
			}
			
			// Display process progress at current time
			// Shows pid, arrival time, burst time, remaining time, idle time, the current 
			// time, and the status						
			DisplayMethods.displayProgress(process, time, idleTime);
			
			// Increment time
			time++;
				
			
		}
		
		return idleTime;
		
	}
	
	
	
/********************************************************************************************
 * PRIVATE METHODS
 ********************************************************************************************/
	
	/****************************************************************************************
	 * setWaiting
	 * Sets the status of the process with the given process id to waiting
	 * @param process list of processes
	 * @param pid     process id
	 ****************************************************************************************/
	private static void setWaiting(ArrayList<Process> process, int pid) {
		
		// Variables
		boolean isFound = false;  // if the process with the given id is found
		int index = 0;            // iterating index
		
		// While the process is not found and the index is within bounds
		while(!isFound && index < process.size()) {
			
			// If current process' pid matches given id and status is processing (not 
			// terminated), set isFound to true and set status to waiting
			if( process.get(index).getPid() == pid && 
				process.get(index).getStatus() == Status.PROCESSING) {
				
				isFound = true;
				process.get(index).setStatus(Status.WAITING);
			}
			
			// Increment index
			index++;
		}
		
	}
}
