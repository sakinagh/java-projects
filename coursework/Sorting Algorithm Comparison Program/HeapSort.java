package assignment6;

/**
 * A class that implements the heap sort algorithm.
 */
public class HeapSort {
	private static int comparisons = 0;

	/**
	 * Sorts an array of strings using the heap sort algorithm.
	 * 
	 * @param arr The array of strings to be sorted.
	 */
	public static void heapSort(String[] arr) {
		int n = arr.length;
		String[] heap = new String[n];
		for (int i = 0; i < n; i++) {
			insertItem(heap, i, arr[i]);
		}

		// Extract elements from heap one by one
		for (int i = 0; i < n; i++) {
			arr[i] = removeMin(heap, n - i);
		}
	}

	/**
	 * Inserts an item into the heap at the specified position.
	 *
	 * @param a    The heap array.
	 * @param size The size of the heap.
	 * @param key  The key to be inserted.
	 */
	public static void insertItem(String[] a, int size, String key) {
		a[size] = key;
		upheap(a, size);
	}

	/**
	 * Performs upheap operation to restore the heap property after an insertion.
	 *
	 * @param a     The heap array.
	 * @param index The index of the inserted item.
	 */
	public static void upheap(String[] a, int index) {
		int parentIndex = (index - 1) / 2;
		comparisons++;
		while (index > 0 && a[index].compareTo(a[parentIndex]) < 0) {
			swap(a, index, parentIndex);
			index = parentIndex;
			parentIndex = (index - 1) / 2;
		}
	}

	/**
	 * Removes the minimum element from the heap.
	 *
	 * @param a    The heap array.
	 * @param size The current size of the heap.
	 * @return The minimum element removed from the heap.
	 */
	public static String removeMin(String[] a, int size) {
		String min = a[0];
		a[0] = a[size - 1];
		downheap(a, 0, size - 1);
		return min;
	}

	/**
	 * Swaps two elements in the array.
	 *
	 * @param a       The array.
	 * @param index1  The index of the first element.
	 * @param index2  The index of the second element.
	 */
	public static void swap(String[] a, int index1, int index2) {
		String temp = a[index1];
		a[index1] = a[index2];
		a[index2] = temp;
	}

	/**
	 * Performs downheap operation to restore the heap property after a removal.
	 *
	 * @param a     The heap array.
	 * @param index The index of the element to downheap from.
	 * @param size  The current size of the heap.
	 */
	public static void downheap(String[] a, int index, int size) {
		int leftChildIndex = 2 * index + 1;
		int rightChildIndex = 2 * index + 2;
		int smallerChildIndex;
		while (leftChildIndex < size) {
			comparisons++;
			if (rightChildIndex < size && a[leftChildIndex].compareTo(a[rightChildIndex]) > 0) {
				smallerChildIndex = rightChildIndex; 
			} else {
				smallerChildIndex = leftChildIndex; 
			}
			comparisons++;
			if (a[index].compareTo(a[smallerChildIndex]) > 0) {
				swap(a, index, smallerChildIndex);
				index = smallerChildIndex;
				leftChildIndex = 2 * index + 1;
				rightChildIndex = 2 * index + 2;
			} else {
				break;
			}
		}
	}

	/**
	 * Retrieves the number of comparisons performed during the last heap sort operation.
	 * 
	 * @return The number of comparisons performed.
	 */
	public static int getComparisonsHeap() {
		return comparisons;
	}
}
