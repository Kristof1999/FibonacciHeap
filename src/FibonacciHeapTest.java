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
}