import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BinaryHeapTest {

    @Test
    public void constructionTest() {
        var input = new int[] {100, 19, 36, 17, 3, 25, 1, 2, 7};
        var heap = new BinaryHeap(input);
        System.out.println(Arrays.toString(heap.getAsArray()));
        assertArrayEquals(input, heap.getAsArray());
    }

}