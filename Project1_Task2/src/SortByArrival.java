/*********************************************************************************************
 * Name      : Milan Bui
 * Date      : 18 March 2022
 * Class     : CS 4310.01
 * Assignment: Project 1 - Task #2
 * File Name : SortByArrival.java
 ********************************************************************************************/
import java.util.Comparator;

public class SortByArrival implements Comparator<Process> {
	public int compare(Process a, Process b) {
		return a.getArrivalTime() - b.getArrivalTime();  // ascending order
	}
}
