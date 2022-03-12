import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class SchedulingAlgorithms {
	
	public static void fcfs(ArrayList<Process> process) {
		
		Queue<Process> queue = new LinkedList<Process>();
		
		int idleTime = 0;
		int processNum = 0;
		int time = 0;
		
		System.out.println("\n\n\nFIRST COME FIRST SERVE (FCFS)");
		while(processNum < process.size() || !queue.isEmpty()) {
			
			while(processNum < process.size() && process.get(processNum).getArrivalTime() == time){
				queue.add(process.get(processNum));
				processNum++;
			}
			
			if(!queue.isEmpty() && queue.peek().getStatus() == Status.PROCESSING) {
				queue.peek().decrementRemTime();
				
				if(queue.peek().getRemainingTime() == 0) {
					
					queue.peek().setStatus(Status.TERMINATED);
					queue.peek().setEndTime(time);
					queue.remove();
				}
			}
			
			if(!queue.isEmpty() && queue.peek().getStatus() == Status.WAITING) {
	
				queue.peek().setStartTime(time);
				queue.peek().setStatus(Status.PROCESSING);
			}
			

			if(queue.isEmpty() && processNum < process.size()) {
				idleTime++;
			}
			displayProgress(process, time, idleTime);
			
			time++;
						
		}
	
		double cpuRate = calcCPUUtilization(process, idleTime);
		double awt = calcAvgWaitingTime(process);
		double art = calcAvgResponseTime(process);
		double att = calcAvgTurnTime(process);
		
		displayStats(awt, art, att, cpuRate);
		
		
	}
	
	
	public static void sjf(ArrayList<Process> process) {
		
		PriorityQueue<Process> waitQueue = new PriorityQueue<Process>(process.size(), 
				                                                      new SortByBurst());
		Queue<Process> activeProcess = new LinkedList<Process>();
		
		int idleTime = 0;
		int processNum = 0;
		int time = 0;
		
		System.out.println("\n\n\nSHORTEST JOB FIRST (SJF)");
		
		while(processNum < process.size() || !activeProcess.isEmpty()) {
			
			while(processNum < process.size() && process.get(processNum).getArrivalTime() == time){
				waitQueue.add(process.get(processNum));
				processNum++;
			}
			
			if(!activeProcess.isEmpty() && activeProcess.peek().getStatus() == Status.PROCESSING) {
				activeProcess.peek().decrementRemTime();
				
				if(activeProcess.peek().getRemainingTime() == 0) {
					
					activeProcess.peek().setStatus(Status.TERMINATED);
					activeProcess.peek().setEndTime(time);
					activeProcess.remove();
					
				}
			}
			
			if(activeProcess.isEmpty() && !waitQueue.isEmpty()) {
				activeProcess.add(waitQueue.poll());
				activeProcess.peek().setStartTime(time);
				activeProcess.peek().setStatus(Status.PROCESSING);
				
			}
			

			if(activeProcess.isEmpty() && processNum < process.size()) {
				idleTime++;
			}
			displayProgress(process, time, idleTime);
			
			time++;
						
		}
	
		double cpuRate = calcCPUUtilization(process, idleTime);
		double awt = calcAvgWaitingTime(process);
		double art = calcAvgResponseTime(process);
		double att = calcAvgTurnTime(process);
		
		displayStats(awt, art, att, cpuRate);
		
	}

	
	public static void preemptivePriority(ArrayList<Process> process) {
		
		int processNum = 0;
		int time = 0;
		int pid = -9999;		
		int idleTime = 0;
		PriorityQueue<Process> queue = new PriorityQueue<Process>(process.size(), 
				                                                  new SortByPriority());
		
		System.out.println("\n\n\nPREEMPTIVE PRIORITY");
		while(processNum < process.size() || !queue.isEmpty()) {
		
			if(!queue.isEmpty() && queue.peek().getStatus() == Status.PROCESSING) {
				queue.peek().decrementRemTime();
				
				if(queue.peek().getRemainingTime() == 0) {
					queue.peek().setStatus(Status.TERMINATED);
					queue.peek().setEndTime(time);
					queue.remove();
				}
			}
			
			while(processNum < process.size() && process.get(processNum).getArrivalTime() == time){
				queue.add(process.get(processNum));
				processNum++;
			}
			
			if(!queue.isEmpty() && pid != queue.peek().getPid()) {
				setWaiting(process, pid);
			}
			
			if(!queue.isEmpty() && queue.peek().getStatus() == Status.WAITING) {

				queue.peek().setStartTime(time);
				queue.peek().setStatus(Status.PROCESSING);
				pid = queue.peek().getPid();
			}
			
			
			if(queue.isEmpty() && processNum < process.size()) {
				idleTime++;
			}
			
			ppDisplayProgress(process, time, idleTime);
			
			time++;
						
		}
	
		double cpuRate = calcCPUUtilization(process, idleTime);
		double awt = calcAvgWaitingTime(process);
		double art = calcAvgResponseTime(process);
		double att = calcAvgTurnTime(process);
		
		displayStats(awt, art, att, cpuRate);
		
	}

	public static void roundRobin(ArrayList<Process> process, int timeQuantum) {
		
		int idleTime = 0;
		int time = 0;
		int processNum = 0;
		
		
		Queue<Process> queue = new LinkedList<Process>();
		
		int currentQuantum = 0;

		
		System.out.println("\n\n\nROUND ROBIN");
		while(processNum < process.size() || !queue.isEmpty()) {
			
			while(processNum < process.size() && process.get(processNum).getArrivalTime() == time){
				queue.add(process.get(processNum));
				processNum++;
			}
			
			if(!queue.isEmpty() && queue.peek().getStatus() == Status.PROCESSING) {
				queue.peek().decrementRemTime();
				currentQuantum++;
				
				if(currentQuantum == timeQuantum || queue.peek().getRemainingTime() == 0) {
					
					if(queue.peek().getRemainingTime() > 0) {
						queue.peek().setStatus(Status.WAITING);
						queue.add(queue.poll());
					}
					else {
						queue.peek().setStatus(Status.TERMINATED);
						queue.peek().setEndTime(time);
						queue.remove();
					}
					
					currentQuantum = 0;
				}
			}
			
	
			if(!queue.isEmpty() && queue.peek().getStatus() == Status.WAITING) {

				queue.peek().setStartTime(time);
				queue.peek().setStatus(Status.PROCESSING);
			}

			if(queue.isEmpty() && processNum < process.size()) {
				idleTime++;
			}

			displayProgress(process, time, idleTime);
			
			time++;
				
			
		}
	
		double cpuRate = calcCPUUtilization(process, idleTime);
		double awt = calcAvgWaitingTime(process);
		double art = calcAvgResponseTime(process);
		double att = calcAvgTurnTime(process);
		
		displayStats(awt, art, att, cpuRate);
		
	}
	
	
	
/******************************************************************************
 * PRIVATE METHODS
 ******************************************************************************/
	private static void setWaiting(ArrayList<Process> process, int pid) {
		
		boolean isFound = false;
		int index = 0;
		
		while(!isFound && index < process.size()) {
			
			if( process.get(index).getPid() == pid && 
				process.get(index).getStatus() == Status.PROCESSING) {
				
				isFound = true;
				process.get(index).setStatus(Status.WAITING);
			}
			
			index++;
		}
		
	}
	
	private static double calcCPUUtilization(ArrayList<Process> process, int idleTime) {
		
		ArrayList<Process> copy = new ArrayList<Process>();
		for(int i = 0; i < process.size(); i++) {
			copy.add(new Process(process.get(i)));
		}
		
		Collections.sort(copy, new SortByEndTime());
		
		double totalTime = copy.get(0).getEndTime();
		
		double rate = ((totalTime - idleTime) / totalTime) * 100;
		
		return rate;
		
	}
	private static double calcAvgWaitingTime(ArrayList<Process> process) {
		double sum = 0;
		double avgWaitTime;
		
		for(int i = 0; i < process.size(); i++) {
			Process current = process.get(i);
			int waitTime = current.getEndTime() - current.getArrivalTime() - current.getBurstTime();
			sum += waitTime;
		}
		
		avgWaitTime = sum / process.size();
		
		return avgWaitTime;
		
	}
	
	private static double calcAvgResponseTime(ArrayList<Process> process) {
		double sum = 0;
		double avgTime;
		
		for(int i = 0; i < process.size(); i++) {
			Process current = process.get(i);
			int respTime = current.getStartTime() - current.getArrivalTime();
			sum += respTime;
		}
		
		avgTime = sum / process.size();
		
		return avgTime;
		
		
	}
	
	private static double calcAvgTurnTime(ArrayList<Process> process) {
		double sum = 0;
		double avgTime;
		
		for(int i = 0; i < process.size(); i++) {
			Process current = process.get(i);
			int time = current.getEndTime() - current.getArrivalTime();
			sum += time;
		}
		
		avgTime = sum / process.size();
		
		return avgTime;
		
		
	}
	
	private static void displayStats(double awt, double art, double att, double cpuRate) {
		
		System.out.println();
		System.out.println(String.format("Average Waiting Time   : %.4f ms", awt));
		System.out.println(String.format("Average Response Time  : %.4f ms", art));
		System.out.println(String.format("Average Turnaround Time: %.4f ms", att)); 
		System.out.println(String.format("CPU Utilization Rate   : %.4f %%", cpuRate)); 
	}
	
	private static void displayProgress(ArrayList<Process> process, int time, int idleTime) {
		
		int size = process.size();

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
		
		
		for(int i = 0; i < size; i++) {
			
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
	
	private static void ppDisplayProgress(ArrayList<Process> process, int time, int idleTime){
		
		int size = process.size();

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
		
		
		for(int i = 0; i < size; i++) {
			
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
