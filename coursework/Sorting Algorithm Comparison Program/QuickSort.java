package assignment6;

/**
 * A class that implements the quicksort algorithm.
 */
public class QuickSort {
	private static int comparisons = 0;

	/**
	 * Sorts an array of strings using the quicksort algorithm.
	 * 
	 * @param arr The array of strings to be sorted.
	 */
	public static void quickSort(String[] arr) {
		int n = arr.length;
		quickSortInPlace(arr, 0, n-1); //low, high
	}

	/**
	 * Sorts the subarray S[a..b] inclusive in place.
	 * 
	 * @param S The array to be sorted.
	 * @param a The low index of the subarray.
	 * @param b The high index of the subarray.
	 */
	public static void quickSortInPlace(String[] S, int a, int b) {
		if (a >= b) return; // subarray is trivially sorted
		int left = a;
		int right = b-1;
		String pivot = S[b];
		String temp; // temp object used for swapping
		
		while (left <= right) {
			comparisons++;
			// scan until reaching value equal or larger than pivot (or right marker)
			while (left <= right && S[left].compareTo(pivot) < 0) left++; comparisons++;

			// scan until reaching value equal or smaller than pivot (or left marker)
			while (left <= right && S[right].compareTo(pivot) >= 0) right--; comparisons++;

			if (left <= right) { // indices did not strictly cross
				comparisons++;
				// so swap values and shrink range
				temp = S[left]; S[left] = S[right]; S[right] = temp;
				left++; right--;
			}
		}
		// put pivot into its final place (currently marked by left index)
		temp = S[left]; S[left] = S[b]; S[b] = temp;
		// make recursive calls
		quickSortInPlace(S, a, left - 1);
		quickSortInPlace(S, left + 1, b);
	}

	/**
	 * Retrieves the number of comparisons performed during the last quicksort operation.
	 * 
	 * @return The number of comparisons performed.
	 */
	public static int getComparisonsQuick() {
		return comparisons;
	}

}
