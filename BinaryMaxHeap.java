package assign10;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A binary max heap implementation that supports priority queue operations.
 * Elements in the heap are arranged in a way that the root is greater than or
 * equal to its children.
 *
 * @param <E> the type of elements in the heap
 */
public class BinaryMaxHeap<E> implements PriorityQueue<E> {
	private E[] backing;
	private int size;
	private int maxSize;
	private Comparator<? super E> custom;

	/**
	 * Constructs an empty binary max heap with an initial capacity of 16.
	 */
	@SuppressWarnings("unchecked")
	public BinaryMaxHeap() {
		backing = (E[]) new Object[16];
		maxSize = 16;
		size = 0;
	}

	/**
	 * Constructs an empty binary max heap with a specified custom comparator and
	 * an initial capacity of 16.
	 *
	 * @param custom the comparator used to order the elements, or null for natural
	 *               ordering
	 */
	@SuppressWarnings("unchecked")
	public BinaryMaxHeap(Comparator<? super E> custom) {
		this.custom = custom;
		backing = (E[]) new Object[16];
		maxSize = 16;
		size = 0;
	}

	/**
	 * Constructs a binary max heap from a list of elements. The elements are added
	 * in the order they appear in the list.
	 *
	 * @param list the list of elements
	 */
	@SuppressWarnings("unchecked")
	public BinaryMaxHeap(List<? extends E> list) {

		this.maxSize = list.size();
		this.size = maxSize;
		this.backing = (E[]) new Object[maxSize + 1];

		int i = 1;
		for (E item : list) {
			backing[i++] = item;
		}

		buildHeap();
	}

	/**
	 * Constructs a binary max heap from a list of elements using a custom
	 * comparator.
	 * The elements are added in the order they appear in the list.
	 *
	 * @param list   the list of elements
	 * @param custom the comparator used to order the elements, or null for natural
	 *               ordering
	 */
	@SuppressWarnings("unchecked")
	public BinaryMaxHeap(List<? extends E> list, Comparator<? super E> custom) {

		this.custom = custom;
		this.maxSize = list.size();
		this.size = maxSize;
		this.backing = (E[]) new Object[maxSize + 1];

		int i = 1;
		for (E item : list) {
			backing[i++] = item;
		}

		buildHeap();
	}

	/**
	 * Builds the heap from the elements in the backing array.
	 */
	private void buildHeap() {
		for (int i = size / 2; i >= 1; i--)
			percolateDown(i);

	}

	/**
	 * Restores the heap property by percolating the element at the given index down
	 * the heap.
	 *
	 * @param index the index of the element to be percolated down
	 */
	private void percolateDown(int index) {
		int leftChild, rightChild, maxIndex;
		while ((2 * index) <= size) {
			leftChild = 2 * index;
			rightChild = 2 * index + 1;
			maxIndex = index;

			if (leftChild <= size && innerCompare(leftChild, maxIndex) > 0) {
				maxIndex = leftChild;
			}
			if (rightChild <= size && innerCompare(rightChild, maxIndex) > 0) {
				maxIndex = rightChild;
			}
			if (maxIndex != index) {
				E temp = backing[index];
				backing[index] = backing[maxIndex];
				backing[maxIndex] = temp;
				index = maxIndex;
			} else {
				break;
			}
		}
	}

	/**
	 * Adds an element to the binary max heap.
	 *
	 * @param item the element to be added
	 */
	@Override
	public void add(E item) {
		if (size >= backing.length - 1) {
			@SuppressWarnings("unchecked")
			E[] temp = (E[]) new Object[backing.length * 2];
			for (int i = 1; i <= size; i++) {
				temp[i] = backing[i];
			}
			backing = temp;
		}
		size++;
		backing[size] = item;

		percolateUp(size);

	}

	/**
	 * Restores the heap property by percolating the added element up the heap.
	 *
	 * @param index the index of the added element
	 */
	private void percolateUp(int index) {
		while (index > 1) {
			int parentIndex = index / 2;
			if (innerCompare(index, parentIndex) > 0) {
				E temp = backing[index];
				backing[index] = backing[parentIndex];
				backing[parentIndex] = temp;
				index = parentIndex;
			} else {
				break;
			}
		}
	}

	/**
	 * Compares two elements in the heap at the given indices.
	 *
	 * @param a the index of the first element
	 * @param b the index of the second element
	 * @return a negative integer, zero, or a positive integer as the first element
	 *         is less than, equal to, or greater than the second element
	 */
	@SuppressWarnings("unchecked")
	private int innerCompare(int a, int b) {
		if (custom != null) {
			return custom.compare((E) backing[a], (E) backing[b]);
		} else {
			Comparable<? super E> comparableA = (Comparable<? super E>) backing[a];
			Comparable<? super E> comparableB = (Comparable<? super E>) backing[b];

			if (comparableA != null && comparableB != null) {
				return comparableA.compareTo((E) backing[b]);
			} else {
				if (comparableA == null && comparableB == null) {
					return 0;
				} else if (comparableA == null) {
					return -1;
				} else {
					return 1;
				}
			}
		}
	}

	/**
	 * Retrieves the maximum element in the binary max heap without removing it.
	 *
	 * @return the maximum element in the heap
	 * @throws NoSuchElementException if the heap is empty
	 */
	@Override
	public E peek() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException("The heap is empty");
		}
		return backing[1];
	}

	/**
	 * Retrieves and removes the maximum element from the binary max heap.
	 *
	 * @return the maximum element in the heap
	 * @throws NoSuchElementException if the heap is empty
	 */
	@Override
	public E extractMax() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException("The heap is empty");
		}
		E maxElement = backing[1];
		backing[1] = backing[size];
		size--;

		percolateDown(1);

		return maxElement;
	}

	/**
	 * Returns the number of elements currently in the binary max heap.
	 *
	 * @return the number of elements in the heap
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Checks if the binary max heap is empty.
	 *
	 * @return true if the heap is empty, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		if (size == 0)
			return true;
		return false;
	}

	/**
	 * Clears the binary max heap, removing all elements.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		backing = (E[]) new Object[maxSize];
		size = 0;

	}

	/**
	 * Returns an array containing all elements in the binary max heap. The order
	 * of the elements in the array corresponds to their order in the backing array,
	 * not their priority in the heap.
	 *
	 * @return an array containing all elements in the heap
	 */
	@Override
	public E[] toArray() {
		@SuppressWarnings("unchecked")
		E[] arr = (E[]) new Object[size];
		for (int i = 0; i < size; i++) {
			arr[i] = backing[i + 1];
		}
		return arr;
	}

}