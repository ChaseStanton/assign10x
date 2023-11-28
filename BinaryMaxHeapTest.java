package assign10;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import java.util.Comparator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BinaryMaxHeapTest {
	private BinaryMaxHeap<Integer> integerHeap;
    private BinaryMaxHeap<String> stringHeap;
    private BinaryMaxHeap<Integer> listIntHeap;
    private BinaryMaxHeap<Integer> listIntHeapComparator;

    

	@BeforeEach
	void setUp() throws Exception {
		integerHeap = new BinaryMaxHeap<>();
        stringHeap = new BinaryMaxHeap<>(Comparator.reverseOrder());
        ArrayList<Integer> items = new ArrayList<>();
        items.add(1);
        items.add(2);
        items.add(3);
        items.add(4);
        items.add(5);
        listIntHeap = new BinaryMaxHeap<>(items);
        listIntHeapComparator = new BinaryMaxHeap<>(items, Comparator.reverseOrder());

	}
	
	@Test
	public void listComparatorConstructor() {
		assertEquals(5, listIntHeap.size());
		assertEquals(5, listIntHeapComparator.size());
		
		assertEquals(5, listIntHeap.extractMax());
		assertEquals(1, listIntHeapComparator.extractMax());

		assertEquals(4, listIntHeap.peek());
		assertEquals(2, listIntHeapComparator.peek());

	}

	@Test
    public void testAddAndPeek() {
        integerHeap.add(10);
        integerHeap.add(5);
        integerHeap.add(15);

        assertEquals(15, (int) integerHeap.peek());

        stringHeap.add("apple");
        stringHeap.add("banana");
        stringHeap.add("orange");

        assertEquals("apple", stringHeap.peek());
    }

	@Test
    public void testExtractMax() {
        integerHeap.add(10);
        integerHeap.add(5);
        integerHeap.add(15);

        assertEquals(15, (int) integerHeap.extractMax());
        assertEquals(10, (int) integerHeap.peek());

        stringHeap.add("apple");
        stringHeap.add("banana");
        stringHeap.add("orange");

        assertEquals("apple", stringHeap.extractMax());
        assertEquals("banana", stringHeap.peek());
    }

	@Test
    public void testSizeAndIsEmpty() {
        assertTrue(integerHeap.isEmpty());
        assertEquals(0, integerHeap.size());

        integerHeap.add(10);
        integerHeap.add(5);

        assertFalse(integerHeap.isEmpty());
        assertEquals(2, integerHeap.size());

        integerHeap.extractMax();

        assertFalse(integerHeap.isEmpty());
        assertEquals(1, integerHeap.size());

        integerHeap.clear();

        assertTrue(integerHeap.isEmpty());
        assertEquals(0, integerHeap.size());
    }

	@Test
    public void testToArray() {
        integerHeap.add(10);
        integerHeap.add(5);
        integerHeap.add(15);

        Integer[] expectedArray = {15, 5, 10 };
        assertArrayEquals(expectedArray, integerHeap.toArray());

        stringHeap.add("apple");
        stringHeap.add("banana");
        stringHeap.add("orange");

        String[] expectedStringArray = {"apple", "banana", "orange" };
        assertArrayEquals(expectedStringArray, stringHeap.toArray());
    }

	@Test
    public void testCustomComparator() {
        // Test with custom comparator for reverse order
        BinaryMaxHeap<Integer> customHeap = new BinaryMaxHeap<>(Comparator.reverseOrder());
        customHeap.add(10);
        customHeap.add(5);
        customHeap.add(15);

        assertEquals(5, (int) customHeap.extractMax());
        assertEquals(10, (int) customHeap.peek());

        // Test with custom comparator for string length
        BinaryMaxHeap<String> lengthHeap = new BinaryMaxHeap<>(Comparator.comparing(String::length));
        lengthHeap.add("apple");
        lengthHeap.add("banana");
        lengthHeap.add("orange");

        assertEquals("banana", lengthHeap.extractMax());
        assertEquals("orange", lengthHeap.peek());
    }

	@Test
    public void testAddAndExtractMaxWithDuplicates() {
        integerHeap.add(10);
        integerHeap.add(5);
        integerHeap.add(15);
        integerHeap.add(10); // Duplicate element

        assertEquals(15, (int) integerHeap.extractMax());
        assertEquals(10, (int) integerHeap.extractMax());
        assertEquals(10, (int) integerHeap.extractMax());
        assertEquals(5, (int) integerHeap.extractMax());
    }

    @Test
    public void testAddAndExtractMaxWithNulls() {
        BinaryMaxHeap<Integer> nullHeap = new BinaryMaxHeap<>();

        nullHeap.add(10);
        nullHeap.add(null);
        nullHeap.add(15);

        assertNull(nullHeap.extractMax());
        assertEquals(10, (int) nullHeap.extractMax());
        assertEquals(15, (int) nullHeap.extractMax());
    }

    @Test
    public void testPeekOnEmptyHeap() {
        assertTrue(integerHeap.isEmpty());
        assertThrows(NoSuchElementException.class, () -> {
			integerHeap.extractMax();
		});
    }

    @Test
    public void testExtractMaxOnEmptyHeap() {
        assertTrue(integerHeap.isEmpty());
        assertThrows(NoSuchElementException.class, () -> {
            integerHeap.extractMax();
        });
    }


    @Test
    public void testEmptyHeapToArray() {
        assertTrue(integerHeap.isEmpty());
        Integer[] emptyArray = {};
        assertArrayEquals(emptyArray, integerHeap.toArray());
    }
}
