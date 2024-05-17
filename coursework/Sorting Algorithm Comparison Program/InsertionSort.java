package assignment6;

/**
 * A class that implements the insertion sort algorithm.
 */
public class InsertionSort {
	private static int comparisons = 0;

	/**
	 * Sorts an array of strings using the insertion sort algorithm.
	 * 
	 * @param arr The array of strings to be sorted.
	 */
	public static void insertionSort(String[] arr) {
		int n = arr.length;
		for(int i = 1; i < n; i++) {
			String key = arr[i];
			int j = i - 1;
			comparisons++;
			while(j >= 0 && arr[j].compareTo(key) >0) {
				arr[j + 1] = arr[j];
				j = j - 1;
				comparisons++;
			}
			arr[j + 1] = key;
		}
	}

	/**
	 * Retrieves the number of comparisons performed during the last insertion sort operation.
	 * 
	 * @return The number of comparisons performed.
	 */
	public static int getComparisonsInsertion() {
		return comparisons;
	}

}
