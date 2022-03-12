/*******************************************************************************
 * Name      : Milan Bui
 * Date      : 18 March 2022
 * Class     : CS 4310.01
 * Assignment: Project 1 - Task #1
 *      Write a multithreaded sorting program that works as follows:
 *      A list of integers is divided into two smaller lists of equal size. Two 
 *      separate threads (which we will term sorting threads) sort each sub-list 
 *      using a sorting algorithm of your choice. The two sub-lists are then 
 *      merged by a third thread—a merging thread —which merges the two sub-lists 
 *      into a single sorted list.
 ******************************************************************************/

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		// Variables
		int[] unsortedArray;
		int[] sortedArray;
		int size;
		Scanner in = new Scanner(System.in);
		
		// INPUT - Prompt user for input
		System.out.print(String.format("%35s", "-").replace(' ', '-'));
		System.out.print(" Multithreaded Sorting Application ");
		System.out.println(String.format("%35s", "-").replace(' ', '-'));
		System.out.print("Please enter a list of integers separated with spaces: ");
		String input = in.nextLine();
		
		// Parses the string
		String[] numbers = input.split(" ");
		
		// Uses parsed string to find array size and initialize integer array
		size = numbers.length;
		unsortedArray = new int[size];		
		sortedArray = new int[size];
		
		for(int i = 0; i < size; i++) {
			unsortedArray[i] = Integer.parseInt(numbers[i]);  // converts string to int
		}
		
		// CALC - sorting
		
		// Finds middle of array. (Will be the end of the 1st sub array and start of the 2nd)
		int middle = size / 2;
		
		// OUTPUT - displays the unsorted list
		System.out.println("\nUnsorted List of Integers: ");
		
		for(int num : unsortedArray) {
			System.out.print(num + " ");
		}
		
		// Sort threads
		SortAlgorithms.selectionSort(unsortedArray, 0, middle);     // left
		SortAlgorithms.selectionSort(unsortedArray, middle, size);  // right
		
		// OUTPUT - displays the partially sorted list
		System.out.println("\n\nList of Integers Before Merge: ");
		
		for(int num : unsortedArray) {
			System.out.print(num + " ");
		}
		
		// Merge thread
		SortAlgorithms.mergeSort(unsortedArray, sortedArray, middle);
		
		
		// OUTPUT - displays the final sorted list
		System.out.println("\n\nSorted List of Integers: ");
		
		for(int num : sortedArray) {
			System.out.print(num + " ");
		}
		
		in.close();
		
	}
}
