package assign10x;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class BinaryMaxHeap<E> implements PriorityQueue {
	private Object[] backing;
	private int size;
	private int maxSize;
	private Comparator custom;

	public BinaryMaxHeap(){
		backing = (E[]) new Object[16];
		maxSize = 16;
		size = 0;
	}

	public BinaryMaxHeap(Comparator<? super E> custom){
		this.custom = custom; 
		backing = (E[]) new Object[16];
		maxSize = 16;
		size = 0;
	}

	@Override
	public void add(Object item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object peek() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object extractMax() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

}
