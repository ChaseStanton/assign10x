package assign10x;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class BinaryMaxHeap<E extends Comparable<? super E>> implements PriorityQueue<E> {
	private E[] backing;
	private int size;
	private int maxSize;
	private Comparator<? super E> custom;

	@SuppressWarnings("unchecked")
	public BinaryMaxHeap() {
		backing = (E[]) new Object[16];
		maxSize = 16;
		size = 0;
	}

	@SuppressWarnings("unchecked")
	public BinaryMaxHeap(Comparator<? super E> custom) {
		this.custom = custom;
		backing = (E[]) new Object[16];
		maxSize = 16;
		size = 0;
	}

	@SuppressWarnings("unchecked")
	public BinaryMaxHeap(List<? extends E> list) {
		size = list.size();
		maxSize = size;
		backing = (E[]) new Object[size];
		for (int i = 0; i < size; i++) {
			backing[i] = list.get(i);
		}
		buildHeap();
	}

	@SuppressWarnings("unchecked")
	public BinaryMaxHeap(List<? extends E> list, Comparator<? super E> custom) {
		this.custom = custom;
		size = list.size();
		maxSize = size;
		backing = (E[]) new Object[size];
		for (int i = 0; i < size; i++) {
			backing[i] = list.get(i);
		}
		buildHeap();
	}

	private void buildHeap() {
		int index = (size / 2) - 1;
		for (int i = index; i >= 0; i--)
			percolateDown(i);

	}

	@SuppressWarnings("unchecked")
	private void percolateDown(int index) {
		int leftChild, rightChild, maxIndex;
		while (index < size / 2) {
			leftChild = 2 * index + 1;
			rightChild = 2 * index + 2;
			maxIndex = index;

			if (leftChild < size && innerCompare(leftChild, maxIndex) > 0) {
				maxIndex = leftChild;
			}
			if (rightChild < size && innerCompare(rightChild, maxIndex) > 0) {
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

	@Override
	public void add(E item) {
		if (size >= backing.length) {
			@SuppressWarnings("unchecked")
			E[] temp = (E[]) new Object[backing.length * 2];
			for (int i = 0; i < backing.length; i++) {
				temp[i] = backing[i];
			}
			backing = temp;
		}
		backing[size] = item;
		size++;

		percolateUp(size - 1);

	}

	private void percolateUp(int index) {
		int parentIndex = (index - 1) / 2;
		while (index > 0 && innerCompare(index, parentIndex) > 0) {
			E temp = backing[index];
			backing[index] = backing[parentIndex];
			backing[parentIndex] = temp;
			index = parentIndex;
			parentIndex = (index - 1) / 2;
		}
	}

	@SuppressWarnings("unchecked")
	private int innerCompare(int a, int b) {
		if (custom != null) {
			return custom.compare((E) backing[a], (E) backing[b]);
		} else {
			return ((E) backing[a]).compareTo((E) backing[b]);
		}
	}

	@Override
	public E peek() throws NoSuchElementException {
		return this.backing[0];
	}

	@Override
public E extractMax() throws NoSuchElementException {
    if (size == 0) {
        throw new NoSuchElementException("Heap is empty");
    }

    E maxItem = this.backing[0];

    if (this.custom != null) {
        // Handle the case with a custom comparator (not provided in the snippet)
        // You may need to implement a custom percolateDown method based on your comparator logic
        // Custom percolateDown(this.custom) or similar logic here
    } else {
        // Standard percolateDown for a max heap
        percolateDown();
    }

    return maxItem;
}


	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		if(size == 0){
			return true;
		}
		return false;
	}

	@Override
	public void clear() {
		this.backing = (E[]) new Object[16];
		this.maxSize = 16;
		this.size = 0;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'toArray'");
	}
}
