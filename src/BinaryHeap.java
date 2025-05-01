import java.util.Arrays;
import java.util.OptionalInt;

// src: https://en.wikipedia.org/wiki/Binary_heap
public class BinaryHeap {
    private int[] heap;
    // index of last element = validLength - 1
    // length of the valid portion of the heap
    private int validLength = 0;

    public BinaryHeap(int[] input) {
        // input is an arbitrary array of ints, ie not sorted, no heap property
        heap = new int[input.length];
        for(var value : input) {
            insert(value);
        }
    }

    public void insert(int x) {
        if (validLength > heap.length) {
            heap = Arrays.copyOf(heap, heap.length*2);
        }

        heap[validLength] = x;

        int index = validLength;
        while(parentIndexOf(index).isPresent() && heapPropertyViolated(index)) {
            swapAtIndexes(parentIndexOf(index).getAsInt(), index);
            index = parentIndexOf(index).getAsInt();
        }
        validLength++;
    }

    public OptionalInt extract() {
        if(validLength == 0)
            return OptionalInt.empty();

        var top = heap[0];
        swapAtIndexes(0, validLength-1);
        validLength--;

        int index = 0;
        while(heapPropertyViolated(index)) {
            int largestIndex = index;
            if (leftChildIndexOf(index).isPresent() && heap[leftChildIndexOf(index).getAsInt()] > heap[largestIndex])
                largestIndex = leftChildIndexOf(index).getAsInt();

            if (rightChildIndexOf(index).isPresent() && heap[rightChildIndexOf(index).getAsInt()] > heap[largestIndex])
                largestIndex = rightChildIndexOf(index).getAsInt();

            if (largestIndex != index)
                swapAtIndexes(largestIndex, index);
            index = largestIndex;
        }

        return OptionalInt.of(top);
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

        if (childIndex < validLength)
            return OptionalInt.of(childIndex);
        else
            return OptionalInt.empty();
    }

    private OptionalInt rightChildIndexOf(int index) {
        var childIndex = 2*index + 2;

        if (childIndex < validLength)
            return OptionalInt.of(childIndex);
        else
            return OptionalInt.empty();
    }
}
