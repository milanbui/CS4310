import java.util.Comparator;

public class SortByArrival implements Comparator<Process> {
	public int compare(Process a, Process b) {
		return a.arrivalTime - b.arrivalTime;  // ascending order
	}
}
