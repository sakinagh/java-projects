package assignment6;

import java.util.Scanner;

/**
 * The main class for Project 6, which measures the performance of various sorting algorithms.
 */
public class Project6 {

	/**
	 * The main method reads input, calls sorting algorithms, and measures their performance.
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		// Read the number of words to be sorted
		int numWords = scan.nextInt();
		scan.nextLine(); // Consume the newline character
		// Read the words to be sorted
		String[] words = new String[numWords];
		for (int i = 0; i < numWords; i++) {
			words[i] = scan.nextLine();
		}

		// Close the scanner
		scan.close();

		// Print header for the results
		System.out.println(" ");
		System.out.println("   Algorithm    | Comparisons | Time (Milliseconds)");

		// Call and measure performance of sorting algorithms
		measureSortingAlgorithm("Selection Sort", words.clone(), SelectionSort::selectionSort, SelectionSort::getComparisonsSelection);
		measureSortingAlgorithm("Insertion Sort", words.clone(), InsertionSort::insertionSort, InsertionSort::getComparisonsInsertion);
		measureSortingAlgorithm("Heap Sort", words.clone(), HeapSort::heapSort, HeapSort::getComparisonsHeap);
		measureSortingAlgorithm("Merge Sort", words.clone(), MergeSort::mergeSort, MergeSort::getComparisonsMerge);
		measureSortingAlgorithm("Quick Sort", words.clone(), QuickSort::quickSort, QuickSort::getComparisonsQuick);
	}

	/**
	 * Measures the performance of a sorting algorithm.
	 * 
	 * @param algorithm        The name of the sorting algorithm.
	 * @param words            The array of words to be sorted.
	 * @param sortingAlgorithm The sorting algorithm to be measured.
	 * @param counterFunction  The function to retrieve the comparisons count for the sorting algorithm.
	 */
	public static void measureSortingAlgorithm(String algorithm, String[] words, SortingAlgorithm sortingAlgorithm, ComparisonsCounter counterFunction) {
		long startTime = System.nanoTime();
		sortingAlgorithm.sort(words);
		long endTime = System.nanoTime();
		double elapsedTimeMilliseconds = (double) (endTime - startTime) / 1_000_000.0;

		// Print the results
		System.out.printf("%-15s | %11d | %15.2f\n", algorithm, counterFunction.getComparisons(), elapsedTimeMilliseconds);
		
	}

}

/**
 * Functional interface for sorting algorithms.
 */
interface SortingAlgorithm {
	void sort(String[] arr);
}

/**
 * Functional interface to retrieve the comparisons count for a sorting algorithm.
 */
@FunctionalInterface
interface ComparisonsCounter {
	int getComparisons();
}

