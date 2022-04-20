import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) {
		
		// VARIABLES
		final int NUM_OF_CYLINDERS = 200;
//		final int HEAD_POSITION = Integer.valueOf(args[0]);
//		final int PREV_POSITION = Integer.valueOf(args[1]);
		final int HEAD_POSITION = 53;
		final int PREV_POSITION = 30;
		int[] fcfsResult;
		int[] sstfResult;
		int[] scanResult;
		int[] cscanResult;
		
		Scanner userIn = new Scanner(System.in);
		
		ArrayList<Integer> requests = new ArrayList<Integer>(Arrays.asList(98,183,37,122,14,124,65,67));
		SchedulingAlgorithms disk = new SchedulingAlgorithms(HEAD_POSITION, PREV_POSITION, NUM_OF_CYLINDERS, requests);
		
		
		// menu - random or file input
		// >> if file input, prompt for file name (try catch)
		
		fcfsResult = disk.fcfs();
		sstfResult = disk.sstf();
		scanResult = disk.scan();
		cscanResult = disk.cscan();

		System.out.print(String.format("%-12s", "Algorithm"));
		System.out.print(String.format("%-23s" , "Total Head Movements"));
		System.out.print(String.format("%-30s" , "Total Direction Changes"));
		System.out.println();
		System.out.println(String.format("%-65s", "-").replace(' ', '-'));
		
		
		System.out.print(String.format("%-12s", "FCFS"));
		System.out.print(String.format("%-25d", fcfsResult[0]));
		System.out.println(String.format("%-30s" ,fcfsResult[1]));
		
		System.out.print(String.format("%-12s", "SSTF"));
		System.out.print(String.format("%-25d", sstfResult[0]));
		System.out.println(String.format("%-30s" ,sstfResult[1]));
		
		System.out.print(String.format("%-12s", "SCAN"));
		System.out.print(String.format("%-25d", scanResult[0]));
		System.out.println(String.format("%-30s" ,scanResult[1]));
		
		System.out.print(String.format("%-12s", "C-SCAN"));
		System.out.print(String.format("%-25d", cscanResult[0]));
		System.out.println(String.format("%-30s" ,cscanResult[1]));
		
	}
}
