/*********************************************************************************************
 * Name      : Milan Bui
 * Date      : 29 April 2022
 * Class     : CS 4310.01
 * Assignment: Project 2 - Task #2
 ********************************************************************************************/
import java.util.ArrayList;
import java.util.Collections;

public class SchedulingAlgorithms {
	
	private int headPosition;
	private int previousPosition;
	private int numOfCylinders;
	private ArrayList<Integer> requests;
	
	public SchedulingAlgorithms(int head, int prev, int num, ArrayList<Integer> req) {
		this.headPosition = head;
		this.previousPosition = prev;
		this.numOfCylinders = num;
		this.requests = new ArrayList<Integer>(req);
	}
	
	
	public int[] fcfs() {
		
		int head = this.headPosition;
		int prev = this.previousPosition;
		
		int totalMovements = 0;
		int totalDirChanges = 0;
		
		for(int i = 0; i < requests.size(); i++) {
			
			int currentReq = requests.get(i);
			
			totalMovements += Math.abs(head - currentReq);
			
			if(head < prev && currentReq > head || head > prev && currentReq < head) {
				totalDirChanges++;
			}
			
			prev = head;
			head = currentReq;
			
		}
		
		int[] result = {totalMovements, totalDirChanges};
		return result;
	}
	
	
	public int[] sstf() {
		
		int head = this.headPosition;
		int prev = this.previousPosition;
		
		int totalMovements = 0;
		int totalDirChanges = 0;
		
		ArrayList<Integer> copy = new ArrayList<Integer>(this.requests);
		Collections.sort(copy);

		
		while(!copy.isEmpty()) {
			
			int smallestDiff = 6000;
			int smallestDiffIndex = 6000;
			
			for(int i = 0; i < copy.size(); i++) {
				int difference = Math.abs(head - copy.get(i));
				
				if(difference < smallestDiff) {
					smallestDiff = difference;
					smallestDiffIndex = i;
				}
			}

			int currentReq = copy.get(smallestDiffIndex);
			
			totalMovements += Math.abs(head - currentReq);
			
			if(head < prev && currentReq > head || head > prev && currentReq < head) {
				totalDirChanges++;
			}
			
			prev = head;
			head = currentReq;
			copy.remove(smallestDiffIndex);
			
		}

		int[] result = {totalMovements, totalDirChanges};
		return result;
	}
	
	
	public int[] scan() {
		
		int head = this.headPosition;
		int prev = this.previousPosition;
		
		int totalMovements = 0;
		int totalDirChanges = 0;
		
		ArrayList<Integer> copy = new ArrayList<Integer>(this.requests);
		Collections.sort(copy);
		int index = 0;
		
		while(index < copy.size() && head > copy.get(index)) {
			
			index++;
			
		}
		
		// going left, towards 0
		if(head < prev) {
			
			for(int i = index - 1 ; i >= 0; i--) {

				int currentReq = copy.get(i);
				
				totalMovements += Math.abs(head - currentReq);
				
				head = currentReq;
			}
			
			totalMovements += Math.abs(head - 0);
			totalDirChanges++;
			head = 0;
			
			
			for(int i = index; i < copy.size(); i++) {

				int currentReq = copy.get(i);
				
				totalMovements += Math.abs(head - currentReq);
				
				head = currentReq;
			}
			
		}
		// going right towards max 
		else {

			for(int i = index; i < copy.size(); i++) {

				int currentReq = copy.get(i);

				totalMovements += Math.abs(head - currentReq);
				
				head = currentReq;
			}
			
			totalMovements += Math.abs(head - (numOfCylinders - 1));
			totalDirChanges++;
			head = numOfCylinders - 1;
			
			for(int i = index - 1 ; i >= 0; i--) {

				int currentReq = copy.get(i);
				
				totalMovements += Math.abs(head - currentReq);
				
				head = currentReq;
			}
			
		}


		int[] result = {totalMovements, totalDirChanges};
		return result;
	}
	
	
	public int[] cscan() {
		
		int head = this.headPosition;
		int prev = this.previousPosition;
		
		int totalMovements = 0;
		int totalDirChanges = 0;
		
		ArrayList<Integer> copy = new ArrayList<Integer>(this.requests);
		Collections.sort(copy);
		int index = 0;
		
		while(index < copy.size() && head > copy.get(index)) {
			
			index++;
			
		}
		
		// going left, towards 0
		if(head < prev) {
			
			for(int i = index - 1 ; i >= 0; i--) {

				int currentReq = copy.get(i);
				
				totalMovements += Math.abs(head - currentReq);
				
				head = currentReq;
			}
			
			totalMovements += Math.abs(head - 0);
			totalDirChanges++;
			head = 0;
			
			totalMovements += Math.abs(head - (this.numOfCylinders - 1));
			totalDirChanges++;
			head = this.numOfCylinders - 1;
			
			for(int i = copy.size() - 1 ; i > index - 1; i--) {

				int currentReq = copy.get(i);
				
				totalMovements += Math.abs(head - currentReq);
				
				head = currentReq;
			}
			
		}
		// going right towards max 
		else {

			for(int i = index; i < copy.size(); i++) {

				int currentReq = copy.get(i);
				totalMovements += Math.abs(head - currentReq);
				
				head = currentReq;
			}
			
			totalMovements += Math.abs(head - (numOfCylinders - 1));
			totalDirChanges++;
			head = numOfCylinders - 1;
			
			totalMovements += Math.abs(head - 0);
			totalDirChanges++;
			head = 0;
			
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
