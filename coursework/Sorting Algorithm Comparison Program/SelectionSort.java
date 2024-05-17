package assignment6;

/**
 * A class that implements the selection sort algorithm.
 */
public class SelectionSort {
	private static int comparisons = 0;

	/**
	 * Sorts an array of strings using the selection sort algorithm.
	 * 
	 * @param arr The array of strings to be sorted.
	 */
	public static void selectionSort(String[] arr) {
		int n = arr.length;
		for(int i = 0; i < n - 1; i++) {
			int min_idx = i;
			for(int j = i + 1; j < n; j++) {
				comparisons++;
				if(arr[j].compareTo(arr[min_idx]) <0) {
					min_idx = j;
				}
			}
			String temp = arr[min_idx];
			arr[min_idx] = arr[i];
			arr[i] = temp;
		}
	}

	/**
	 * Retrieves the number of comparisons performed during the last selection sort operation.
	 * 
	 * @return The number of comparisons performed.
	 */
	public static int getComparisonsSelection() {
		return comparisons;
	}

}
