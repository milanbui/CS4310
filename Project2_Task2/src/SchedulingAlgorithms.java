/*********************************************************************************************
 * Name      : Milan Bui
 * Date      : 29 April 2022
 * Class     : CS 4310.01
 * Assignment: Project 2 - Task #2
 ********************************************************************************************/
import java.util.ArrayList;
import java.util.Collections;

public class SchedulingAlgorithms {
	
	// INSTANCE VARIABLES   
	private int headPosition;             // current position of head 
	private int previousPosition;         // previous position
	private int numOfCylinders;           // Number of cylinders
	private ArrayList<Integer> requests;  // list of requests
	
	
	/*****************************************************************************************
	 * CONSTRUCTOR
	 * @param head current head position
	 * @param prev previous position
	 * @param num  number of cylinders
	 * @param req  list of requests
	 ****************************************************************************************/
	public SchedulingAlgorithms(int head, int prev, int num, ArrayList<Integer> req) {
		this.headPosition = head;
		this.previousPosition = prev;
		this.numOfCylinders = num;
		this.requests = new ArrayList<Integer>(req);
	}
	
	/*****************************************************************************************
	 * fcfs
	 * Uses the first come first serve disk-scheduling algorithm to process requests.
	 * 
	 * @return results the total head movements in cylinders and the total direction changes
	 ****************************************************************************************/
	public int[] fcfs() {
		
		// Variables
		int head = this.headPosition;      // current head position
		int prev = this.previousPosition;  // previous position
		int totalMovements = 0;            // total head movements in cylinders
		int totalDirChanges = 0;           // total number of direction changes
		
		// For each request in the list
		for(int i = 0; i < requests.size(); i++) {
			
			int currentReq = requests.get(i); 
			 
			// calcs distance between head and request and adds to total movements
			totalMovements += Math.abs(head - currentReq);  
			
			// If head < request < prev or prev < request < head, direction change
			if(head < prev && currentReq > head || head > prev && currentReq < head) {
				totalDirChanges++;
			}
			
			// Sets new head and previous position
			prev = head;
			head = currentReq;
			
		}
		
		int[] result = {totalMovements, totalDirChanges};
		return result;
	}
	
	
	/*****************************************************************************************
	 * sstf
	 * Uses the Shortest Seek Time First disk-scheduling algorithm to process requests.
	 * 
	 * @return results the total head movements in cylinders and the total direction changes
	 ****************************************************************************************/	
	public int[] sstf() {
		
		// Variables
		int head = this.headPosition;      // current head position
		int prev = this.previousPosition;  // previous position
		int totalMovements = 0;            // total head movements
		int totalDirChanges = 0;           // total number of direction changes
		
		// Copies list of requests and sorts from smallest number to largest
		ArrayList<Integer> copy = new ArrayList<Integer>(this.requests);
		Collections.sort(copy);

		// While there are requests to process
		while(!copy.isEmpty()) {
			
			int smallestDiff = numOfCylinders + 1000;
			int smallestDiffIndex = numOfCylinders + 1000;
			
			// for remaining requests find request with smallest distance
			for(int i = 0; i < copy.size(); i++) {
				
				// Calculates distance between head and possible request
				int difference = Math.abs(head - copy.get(i));
				
				// If difference is smaller than current smallest, set values to this request
				if(difference < smallestDiff) {
					smallestDiff = difference;
					smallestDiffIndex = i;
				}
			}

			// Set request to request with smallest difference
			int currentReq = copy.get(smallestDiffIndex);
			
			// Add distance from head to request to total movements
			totalMovements += Math.abs(head - currentReq);
			
			// If head < request < prev or prev < request < head, direction change
			if(head < prev && currentReq > head || head > prev && currentReq < head) {
				totalDirChanges++;
			}
			
			// Set new head and previous positions and delete the processed request
			prev = head;
			head = currentReq;
			copy.remove(smallestDiffIndex);
			
		}

		int[] result = {totalMovements, totalDirChanges};
		return result;
	}
	
	
	/*****************************************************************************************
	 * scan
	 * Uses the SCAN disk-scheduling algorithm to process requests.
	 * 
	 * @return results the total head movements in cylinders and the total direction changes
	 ****************************************************************************************/	
	public int[] scan() {
		
		// Variables
		int head = this.headPosition;      // current head position
		int prev = this.previousPosition;  // previous position
		int totalMovements = 0;            // total head movements
		int totalDirChanges = 0;           // total number of direction changes
		int index = 0;                     // iterator for while loop to find head's position
		                                   // among list of requests
		
		// Copies list of requests and sorts from smallest number to largest
		ArrayList<Integer> copy = new ArrayList<Integer>(this.requests);
		Collections.sort(copy);
		
		// While index is in bounds and the head is greater than the requests, iterate
		// finds location of head among requests. cannot have in between so index is the index 
		// of the larger request
		while(index < copy.size() && head > copy.get(index)) {
			
			index++;
			
		}
		
		// going left, towards 0
		if(head < prev) {
			
			// For requests to the left of the head, process and add distance
			for(int i = index - 1 ; i >= 0; i--) {

				int currentReq = copy.get(i);
				
				totalMovements += Math.abs(head - currentReq);
				
				head = currentReq;
			}
			
			// Jump from head to the end which is 0 as the dir is left & change directions
			totalMovements += Math.abs(head - 0);
			totalDirChanges++;
			head = 0;
			
			// For requests to the right, process and add to distance
			for(int i = index; i < copy.size(); i++) {

				int currentReq = copy.get(i);
				
				totalMovements += Math.abs(head - currentReq);
				
				head = currentReq;
			}
			
		}
		// going right towards max 
		else {

			// For requests to the right, process and add to distance
			for(int i = index; i < copy.size(); i++) {

				int currentReq = copy.get(i);

				totalMovements += Math.abs(head - currentReq);
				
				head = currentReq;
			}
			
			// Jump to end, opposite end of 0 as moving right, and change directions
			totalMovements += Math.abs(head - (numOfCylinders - 1));
			totalDirChanges++;
			head = numOfCylinders - 1;
			
			// For requests to the left, process and add to distance
			for(int i = index - 1 ; i >= 0; i--) {

				int currentReq = copy.get(i);
				
				totalMovements += Math.abs(head - currentReq);
				
				head = currentReq;
			}
			
		}


		int[] result = {totalMovements, totalDirChanges};
		return result;
	}
	
	
	/*****************************************************************************************
	 * cscan
	 * Uses the C-SCAN disk-scheduling algorithm to process requests.
	 * 
	 * @return results the total head movements in cylinders and the total direction changes
	 ****************************************************************************************/	
	public int[] cscan() {
		
		// Variables
		int head = this.headPosition;      // current head position
		int prev = this.previousPosition;  // previous position
		int totalMovements = 0;            // total head movements
		int totalDirChanges = 0;           // total number of direction changes
		int index = 0;                     // iterator for while loop to find head's position
		                                   // among list of requests
		
		// Copies list of requests and sorts from smallest number to largest
		ArrayList<Integer> copy = new ArrayList<Integer>(this.requests);
		Collections.sort(copy);
		
		while(index < copy.size() && head > copy.get(index)) {
			
			index++;
			
		}
		
		// going left, towards 0
		if(head < prev) {
			
			// For request to the left, process and add distances
			for(int i = index - 1 ; i >= 0; i--) {

				int currentReq = copy.get(i);
				
				totalMovements += Math.abs(head - currentReq);
				
				head = currentReq;
			}
			
			// Jump to left end and change directions
			totalMovements += Math.abs(head - 0);
			totalDirChanges++;
			head = 0;
			
			// Jump to opposite end
			totalMovements += Math.abs(head - (this.numOfCylinders - 1));
			totalDirChanges++;
			head = this.numOfCylinders - 1;
			
			// For requests to the left, process and add distances
			for(int i = copy.size() - 1 ; i > index - 1; i--) {

				int currentReq = copy.get(i);
				
				totalMovements += Math.abs(head - currentReq);
				
				head = currentReq;
			}
			
		}
		// going right towards max 
		else {

			// For requests to the right, process and add distances
			for(int i = index; i < copy.size(); i++) {

				int currentReq = copy.get(i);
				totalMovements += Math.abs(head - currentReq);
				
				head = currentReq;
			}
			
			// Jump to right end
			totalMovements += Math.abs(head - (numOfCylinders - 1));
			totalDirChanges++;
			head = numOfCylinders - 1;
			
			// Jump the opposite end
			totalMovements += Math.abs(head - 0);
			totalDirChanges++;
			head = 0;
			
			// For processes to the right, process and add distances
			for(int i = 0; i < index; i++) {

				int currentReq = copy.get(i);
				
				totalMovements += Math.abs(head - currentReq);
				
				head = currentReq;
			}
			
		}


		int[] result = {totalMovements, totalDirChanges};
		return result;
	}

}
