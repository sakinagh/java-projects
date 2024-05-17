package assignment6;

import java.util.Arrays;

/**
 * A class that implements the merge sort algorithm.
 */
public class MergeSort {
	private static int comparisons = 0;

	/**
	 * Sorts an array of strings using the merge sort algorithm.
	 * 
	 * @param arr The array of strings to be sorted.
	 */
	public static void mergeSort(String[] arr) {
		int n = arr.length;
		if(n<2) return;

		int mid = n/2;

		String[] Sleft = Arrays.copyOfRange(arr, 0, mid);
		String[] Sright = Arrays.copyOfRange(arr, mid, n);

		mergeSort(Sleft);
		mergeSort(Sright);

		merge(Sleft, Sright, arr);
	}

	/**
	 * Merges the contents of two arrays Sleft and Sright into a properly sized array S.
	 * 
	 * @param Sleft  The left array to be merged.
	 * @param Sright The right array to be merged.
	 * @param S      The array to merge the contents into.
	 */
	public static void merge(String[] Sleft, String[] Sright, String[] S) {
		int i = 0, j = 0;
		while (i + j < S.length) {
			comparisons++;
			if (j == Sright.length || (i < Sleft.length && Sleft[i].compareTo(Sright[j]) < 0)) {
				S[i+j] = Sleft[i++]; // copy ith element of S1 and increment i
			} else {
				S[i+j] = Sright[j++]; // copy jth element of S2 and increment j
			}
			
		}
	}

	/**
	 * Retrieves the number of comparisons performed during the last merge sort operation.
	 * 
	 * @return The number of comparisons performed.
	 */
	public static int getComparisonsMerge() {
		return comparisons;
	}

}
