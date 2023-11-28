package assign10;

import java.util.ArrayList;
import java.util.Random;

public class Assign10TImingExperiment {

	
public static void priorityQueueExp() {
	 System.out.println("N\tadd time (ns) \t peek time (ns) \t extractMax time (nslrll");
	 	int startN = 1000;
	    int endN = 10000;
	    int stepSize = 1000;
	    Random rng = new Random();
	    for (int N = startN; N <= endN; N += stepSize) {
	    	BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>();
	    	long startTimeAdd = System.nanoTime();
	        for (int i = 0; i < N; i++) {
	            heap.add(rng.nextInt());
	        }
	        long stopTimeAdd = System.nanoTime();
	        long addTime = stopTimeAdd - startTimeAdd;
	        
	       
	        long startTimePeek = System.nanoTime();
	        for(int i = 0; i < N; i++) {
	        	heap.peek();
	        }
	        long stopTimePeek = System.nanoTime();
	        long peekTime = stopTimePeek - startTimePeek;
	        
	        long startTimeExtract = System.nanoTime();
	        for(int i = 0; i < N; i++) {
	        	heap.extractMax();
	        }
	        long stopTimeExtract = System.nanoTime();
	        long extractTime = stopTimeExtract - startTimeExtract;
	        
	        System.out.println(N + "\t    " + addTime + "\t     " + peekTime + "\t      " + extractTime);

	    }
	 
}

public static void findKthLargestExp() {
	System.out.println("N\t findKthLargestSort (ns) \t findKthLargestHeap");
 	int startN = 1000;
    int endN = 10000;
    int stepSize = 1000;
    Random rng = new Random();
    for (int N = startN; N <= endN; N += stepSize) {
    	ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            list.add(rng.nextInt());
        }
        Random range = new Random(N);
    	int k = range.nextInt(N);
        long startTimeHeap = System.nanoTime();
        for (int i = 0; i < N; i++) {
            FindKLargest.findKLargestHeap(list, k);
        }
        long stopTimeHeap = System.nanoTime();
        long heapTime = stopTimeHeap - startTimeHeap;
        
        long startTimeSort = System.nanoTime();
        for(int i = 0; i < N; i++) {
        	FindKLargest.findKLargestSort(list, k);
        }
        long stopTimeSort = System.nanoTime();
        long sortTime = stopTimeSort - startTimeSort;
        
        System.out.println(N + "\t    " + sortTime + "\t     " + heapTime);

        
        

        
    }
}
	public static void main(String[] args) {
		priorityQueueExp();
		System.out.println();
		findKthLargestExp();

	}

}
