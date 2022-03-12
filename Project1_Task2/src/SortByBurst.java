

import java.util.Comparator;

public class SortByBurst implements Comparator<Process> {
	public int compare(Process a, Process b) {
		return a.burstTime - b.burstTime;
	}
}