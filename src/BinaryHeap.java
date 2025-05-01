import java.util.Arrays;
import java.util.OptionalInt;

// src: https://en.wikipedia.org/wiki/Binary_heap
public class BinaryHeap {
    private int[] heap;
    private int lastIndex = 0;

    public BinaryHeap(int[] input) {
        // input is an arbitrary array of ints, ie not sorted, no heap property
        heap = new int[input.length];
        for(var value : input) {
            insert(value);
        }
    }

    public void insert(int x) {
        if (lastIndex > heap.length) {
            heap = Arrays.copyOf(heap, heap.length*2);
        }

        heap[lastIndex] = x;

        int index = lastIndex;
        while(parentIndexOf(index).isPresent() && heapPropertyViolated(index)) {
            swapAtIndexes(parentIndexOf(index).getAsInt(), index);
            index = parentIndexOf(index).getAsInt();
        }
        lastIndex++;
    }

    public int[] getAsArray() {
        // remove zeros?
        return heap;
    }

    private boolean heapPropertyViolated(int index) {
        var leftChildGreater = false;
        if (leftChildIndexOf(index).isPresent())
            leftChildGreater = heap[leftChildIndexOf(index).getAsInt()] > heap[index];

        var rightChildGreater = false;
        if (rightChildIndexOf(index).isPresent())
            rightChildGreater = heap[rightChildIndexOf(index).getAsInt()] > heap[index];

        var parentSmaller = false;
        if (parentIndexOf(index).isPresent())
            parentSmaller = heap[parentIndexOf(index).getAsInt()] < heap[index];

        return leftChildGreater || rightChildGreater || parentSmaller;
    }

    private void swapAtIndexes(int index1, int index2) {
        int temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    private OptionalInt parentIndexOf(int index) {
        var parentIndex = (int) Math.floor((index - 1) / 2.0);

        if (parentIndex >= 0)
            return OptionalInt.of(parentIndex);
        else
            return OptionalInt.empty();
    }

    private OptionalInt leftChildIndexOf(int index) {
        var childIndex = 2*index + 1;

        if (childIndex < heap.length)
            return OptionalInt.of(childIndex);
        else
            return OptionalInt.empty();
    }

    private OptionalInt rightChildIndexOf(int index) {
        var childIndex = 2*index + 2;

        if (childIndex < heap.length)
            return OptionalInt.of(childIndex);
        else
            return OptionalInt.empty();
    }
}
