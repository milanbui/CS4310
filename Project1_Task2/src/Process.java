enum Status {
	PROCESSING,
	WAITING,
	TERMINATED,
}

public class Process {
	public int pid;
	public int arrivalTime;
	public int burstTime;
	public int priority;
	public Status status;
	public int remainingTime;
	public int endTime;
	public int startTime;



	public Process() {
		this.pid           = 0;
		this.arrivalTime   = 0;
		this.burstTime     = 0;
		this.priority      = 0;
		this.status        =  Status.WAITING;
		this.remainingTime = 0;
		this.endTime       = 0;
		this.startTime     = 0;
	}
	
	public Process(Process process) {
		this.pid           = process.pid;
		this.arrivalTime   = process.arrivalTime;
		this.burstTime     = process.burstTime;
		this.priority      = process.priority;
		this.status        = process.status;
		this.remainingTime = process.remainingTime;
		this.endTime       = process.endTime;
		this.startTime     = process.startTime;
	}
	
}
