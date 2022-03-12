/*********************************************************************************************
 * Name      : Milan Bui
 * Date      : 18 March 2022
 * Class     : CS 4310.01
 * Assignment: Project 1 - Task #2
 * File Name : SortByBurst.java
 ********************************************************************************************/
import java.util.Comparator;

public class SortByBurst implements Comparator<Process> {
	public int compare(Process a, Process b) {
		return a.getBurstTime() - b.getBurstTime(); // ascending
	}
}