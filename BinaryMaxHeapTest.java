package assign10x;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BinaryMaxHeapTest {
	private BinaryMaxHeap<Integer> integerHeap;
    private BinaryMaxHeap<String> stringHeap;

	@BeforeEach
	void setUp() throws Exception {
		integerHeap = new BinaryMaxHeap<>();
        stringHeap = new BinaryMaxHeap<>(Comparator.reverseOrder());
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

        Integer[] expectedArray = {15, 10, 5 };
        assertArrayEquals(expectedArray, integerHeap.toArray());

        stringHeap.add("apple");
        stringHeap.add("banana");
        stringHeap.add("orange");

        String[] expectedStringArray = { null, "orange", "banana", "apple" };
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

}
