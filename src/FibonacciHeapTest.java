import org.junit.jupiter.api.Test;

import java.util.OptionalInt;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciHeapTest {

    private int[] input = new int[] {100, 19, 36, 17, 3, 25, 1, 2, 7};

    @Test
    public void constructionTest() {
        var heap = new FibonacciHeap(input);
    }

    @Test
    public void minTest() {
        var heap = new FibonacciHeap(input);
        assertEquals(1, heap.getMin().getAsInt());
    }

    @Test
    public void minEmptyTest() {
        var heap = new FibonacciHeap(new int[0]);
        assertEquals(OptionalInt.empty(), heap.getMin());
    }

    @Test
    public void extractMinTest() {
        var heap = new FibonacciHeap(input);
        assertEquals(1, heap.extractMin().getAsInt());
        assertEquals(2, heap.extractMin().getAsInt());
        assertEquals(3, heap.extractMin().getAsInt());
        assertEquals(7, heap.extractMin().getAsInt());
        assertEquals(17, heap.extractMin().getAsInt());
        assertEquals(19, heap.extractMin().getAsInt());
        assertEquals(25, heap.extractMin().getAsInt());
        assertEquals(36, heap.extractMin().getAsInt());
        assertEquals(100, heap.extractMin().getAsInt());
        assertEquals(OptionalInt.empty(), heap.extractMin().getAsInt());
    }
}