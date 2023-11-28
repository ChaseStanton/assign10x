package assign10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class BinaryMaxHeap<E> implements PriorityQueue<E> {
		private E[] backing;
	    private int size;
	    private int maxSize;
	    private Comparator<? super E> custom;

	    @SuppressWarnings("unchecked")
		public BinaryMaxHeap(){
	        backing = (E[]) new Object[16];
	        maxSize = 16;
	        size = 0;
	    }

	    @SuppressWarnings("unchecked")
		public BinaryMaxHeap(Comparator<? super E> custom){
	        this.custom = custom; 
	        backing = (E[]) new Object[16];
	        maxSize = 16;
	        size = 0;
	    }
	    @SuppressWarnings("unchecked")
		public BinaryMaxHeap(List<? extends E> list){
	    	

	        this.maxSize = list.size();
	        this.size = maxSize;
	        this.backing = (E[]) new Object[maxSize + 1]; 

	        int i = 1;
	        for (E item : list) {
	            backing[i++] = item;
	        }

	        buildHeap();
	    }
	    @SuppressWarnings("unchecked")
		public BinaryMaxHeap(List<? extends E> list, Comparator<? super E> custom){
	    	

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
	    	
	    


	    
	    private void buildHeap() {
	    	for(int i = size/2; i >= 1; i--)
	    		percolateDown(i);
	    		
	    }
	    

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


@Override
public void add(E item) {
	if(size >= backing.length - 1) {
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


@Override
public E peek() throws NoSuchElementException {
	if(size == 0) {
		throw new NoSuchElementException("The heap is empty");
	}
	return backing[1];
}


@Override
public E extractMax() throws NoSuchElementException {
	if(size == 0) {
		throw new NoSuchElementException("The heap is empty");
	}
	E maxElement = backing[1];
	backing[1] = backing[size];
	size--;
	
	percolateDown(1);
	
	return maxElement;
}


@Override
public int size() {
	return size;
}


@Override
public boolean isEmpty() {
	if(size == 0)
		return true;
	return false;
}


@SuppressWarnings("unchecked")
@Override
public void clear() {
	backing = (E[]) new Object[maxSize];
    size = 0;

}


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
