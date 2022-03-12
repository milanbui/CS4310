// Status of process
enum Status {
	PROCESSING,
	WAITING,
	TERMINATED,
}

public class Process {
	
	// INSTANCE VARIABLES
	private int pid;            // process id
	private int arrivalTime;    // process arrival time
	private int burstTime;      // process burst time/CPU time
	private int priority;       // process priority, lower number = higher priority
	private Status status;      // process status
	private int remainingTime;  // remaining process/CPU time needed by process
	private int endTime;        // time process terminates
	private int startTime;      // time process first uses CPU

	/*****************************************************************************************
	 * DEFAULT CONSTRUCTOR
	 ****************************************************************************************/
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
	
	/****************************************************************************************
	 * CONSTRUCTOR
	 * Initializes instance variables with given values
	 * @param pid     process id
	 * @param arrival arrival time
	 * @param burst   burst time
	 * @param p       priority
	 ****************************************************************************************/
	public Process(int pid, int arrival, int burst, int p) {
		this.pid           = pid;
		this.arrivalTime   = arrival;
		this.burstTime     = burst;
		this.priority      = p;
		this.status        = Status.WAITING;
		this.remainingTime = burst;
		this.endTime       = 0;
		this.startTime     = 0;
	}
	
	/****************************************************************************************
	 * CONSTRUCTOR
	 * Initializes instance variables with values from given process. Essentially copies
	 * @param process process to copy
	 ***************************************************************************************/
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
	
/*********************************************************************************************
 * GETTERS / ACCESSORS
 ********************************************************************************************/
	
	public int getPid() {
		return this.pid;
	}
	
	public int getArrivalTime() {
		return this.arrivalTime;
	}
	
	public int getBurstTime() {
		return this.burstTime;
	}
	
	public int getPriority() {
		return this.priority;
	}
	
	public Status getStatus() {
		return this.status;
	}
	
	public int getRemainingTime() {
		return this.remainingTime;
	}
	
	public int getEndTime() {
		return this.endTime;
	}
	
	public int getStartTime() {
		return this.startTime;
	}
	
/*********************************************************************************************
 * SETTERS / MUTATORS
 ********************************************************************************************/
	
	public void setPid(int id) {
		this.pid = id;
	}
	
	public void setArrivalTime(int time) {
		this.arrivalTime = time;
	}
	
	public void setBurstTime(int time) {
		this.burstTime = time;
	}
	
	public void setPriority(int num) {
		this.priority = num;
	}
	
	public void setStatus(Status state) {
		this.status = state;
	}
	
	public void setRemainingTime(int time) {
		this.remainingTime = time;
	}
	
	public void setEndTime(int time) {
		this.endTime = time;
	}
	
	public void setStartTime(int time) {
		this.startTime = time;
	}
	
	public void decrementRemTime() {
		this.remainingTime--;
	}
}
