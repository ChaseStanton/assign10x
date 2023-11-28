package assign10x;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This class contains generic static methods for finding the k largest items in
 * a list.
 * 
 * @author Prof. Parker and ??
 * @version ??
 */
public class FindKLargest {

	/**
	 * Determines the k largest items in the given list, using a binary max heap and
	 * the natural ordering of the items.
	 * 
	 * @param items - the given list
	 * @param k     - the number of largest items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of
	 *                                  the given list
	 */
	public static <E extends Comparable<? super E>> List<E> findKLargestHeap(List<E> items, int k)
			throws IllegalArgumentException {
		if (items.size() == 0)
			return null;
		if (k > items.size() || k < 0) {
			throw new IllegalArgumentException("k must be in between 0 and the size of the list");
		}

		BinaryMaxHeap<E> heap = new BinaryMaxHeap<E>(items);
		List<E> result = new ArrayList<>();
		for (int i = 0; i < k; i++) {
			result.add(heap.extractMax());
		}

		return result;

	}

	/**
	 * Determines the k largest items in the given list, using a binary max heap.
	 * 
	 * @param items - the given list
	 * @param k     - the number of largest items
	 * @param cmp   - the comparator defining how to compare items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of
	 *                                  the given list
	 */
	public static <E> List<E> findKLargestHeap(List<E> items, int k, Comparator<? super E> cmp)
			throws IllegalArgumentException {
		if (items.size() == 0)
			return null;
		if (k > items.size() || k < 0) {
			throw new IllegalArgumentException("k must be in between 0 and the size of the list");
		}

		BinaryMaxHeap<E> heap = new BinaryMaxHeap<E>(items, cmp);
		List<E> result = new ArrayList<>();
		for (int i = 0; i < k; i++) {
			result.add(heap.extractMax());
		}

		return result;
	}

	/**
	 * Determines the k largest items in the given list, using Java's sort routine
	 * and the natural ordering of the items.
	 * 
	 * @param items - the given list
	 * @param k     - the number of largest items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of
	 *                                  the given list
	 */
	public static <E extends Comparable<? super E>> List<E> findKLargestSort(List<E> items, int k)
			throws IllegalArgumentException {
		if (items.size() == 0)
			return null;
		if (k > items.size() || k < 0) {
			throw new IllegalArgumentException("k must be in between 0 and the size of the list");
		}

		Collections.sort(items);
		int startingIndex = items.size() - k;
		while (startingIndex > 0) {
			items.remove(0);
			startingIndex--;
		}

		return items;
	}

	/**
	 * Determines the k largest items in the given list, using Java's sort routine.
	 * 
	 * @param items - the given list
	 * @param k     - the number of largest items
	 * @param cmp   - the comparator defining how to compare items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of
	 *                                  the given list
	 */
	public static <E> List<E> findKLargestSort(List<E> items, int k, Comparator<? super E> cmp)
			throws IllegalArgumentException {
		if (items.size() == 0)
			return null;
		if (k > items.size() || k < 0) {
			throw new IllegalArgumentException("k must be in between 0 and the size of the list");
		}
		items.sort(cmp);
		int startingIndex = items.size() - k;
		while (startingIndex > 0) {
			items.remove(0);
			startingIndex--;
		}

		return items;
	}
}