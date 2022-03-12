import java.util.Comparator;

public class SortByEndTime implements Comparator<Process> {
	public int compare(Process a, Process b) {
		return b.getEndTime() - a.getEndTime();  // descending order
	}
}
