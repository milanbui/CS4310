

public class SortAlgorithms {
	
	/***************************************************************************
	 * selectionSort
	 * 
	 * Sort the given array from the start index to the end index using a 
	 * selection sort.
	 * 
	 * @param intArr the unsorted array
	 * @param start  the starting index
	 * @param end    the ending index
	 ***************************************************************************/
	public static void selectionSort(int[] intArr, int start, int end) {
		
		for(int i = start; i < end-1; i++) {
			
			int minIndex = i; // index of current minimum
			
			// From unsorted part start to end
			for(int j = i+1; j < end; j++) {
				
				// If the number at index j is less than the current min index,
				// set min index to j
				if(intArr[j] < intArr[minIndex]) {
					
					minIndex = j;
					
				}
			}
			
			// Swap the current number with the min number
			swap(intArr, minIndex, i);
		}
		
		
	}
	
	/***************************************************************************
	 * mergeSort
	 * 
	 * Merges the two sorted halves of an array and copies the complete sorted
	 * array into a result array.
	 * 
	 * @param intArr     the unsorted array
	 * @param resultArr  the resulting sorted array
	 * @param middle     the middle index (end of 1st half, start of 2nd half) 
	 ***************************************************************************/
	public static void mergeSort(int[] intArr, int[] resultArr, int middle) {
		
		int index1 = 0;         // left half start index
		int index2 = middle;    // right half start index
		int i = 0;              // result array index

		// While index1 and index2 are both still in range
		while(index1 < middle && index2 < intArr.length) {
			
			// If number from left is less than the number from right, insert
			// left number into result array
			if(intArr[index1] <= intArr[index2]) {
				
				resultArr[i] = intArr[index1];
				index1++;
			}
			// Else, insert right number
			else {
				resultArr[i] = intArr[index2];
				index2++;
			}
		
			i++;
			
		}
		
		// Insert remaining integers from left side
		while(index1 < middle) {
			
			resultArr[i] = intArr[index1];
			i++;
			index1++;
			
		}
		
		// Insert remaining integers from right side
		while(index2 < intArr.length) {
			
			resultArr[i] = intArr[index2];
			i++;
			index2++;
			
		}
		
	}
	
	/***************************************************************************
	 * swap
	 * 
	 * Swaps integers at the two given indices in the given array.
	 * 
	 * @param numArr  the array of integers
	 * @param first   the index of the first number
	 * @param second  the index of the second number
	 **************************************************************************/
	private static void swap(int[] numArr, int first, int second) {
		
		int temp = numArr[first];
		numArr[first] = numArr[second];
		numArr[second] = temp;
		
	}
}
