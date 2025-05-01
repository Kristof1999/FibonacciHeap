import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.OptionalInt;

import static org.junit.jupiter.api.Assertions.*;

class BinaryHeapTest {

    private int[] input = new int[] {100, 19, 36, 17, 3, 25, 1, 2, 7};

    @Test
    public void constructionTest() {
        var heap = new BinaryHeap(input);
        assertArrayEquals(input, heap.getAsArray());
    }

    @Test
    public void extractTest() {
        var heap = new BinaryHeap(input);
        assertEquals(100, heap.extract().getAsInt());
        assertEquals(36, heap.extract().getAsInt());
        assertEquals(25, heap.extract().getAsInt());
        assertEquals(19, heap.extract().getAsInt());
        assertEquals(17, heap.extract().getAsInt());
        assertEquals(7, heap.extract().getAsInt());
        assertEquals(3, heap.extract().getAsInt());
        assertEquals(2, heap.extract().getAsInt());
        assertEquals(1, heap.extract().getAsInt());
        assertEquals(OptionalInt.empty(), heap.extract());
    }

}