import java.util.Comparator;

public class SortByPriority implements Comparator<Process> {
	public int compare(Process a, Process b) {
		return a.getPriority() - b.getPriority(); // ascending order (smaller number = higher priority)
	}
}