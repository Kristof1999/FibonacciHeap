import java.util.Optional;
import java.util.OptionalInt;

// src:
// - https://en.wikipedia.org/wiki/Fibonacci_heap
// - https://en.wikipedia.org/wiki/Doubly_linked_list
// - MICHAEL L. FREDMAN and ROBERT ENDRE TARJAN: Fibonacci Heaps and Their Uses in Improved Network
//   Optimization Algorithms
public class FibonacciHeap {
    private Optional<Item> minItem = Optional.empty();

    public FibonacciHeap(int[] items) {
        if (items.length == 0)
            return;

        for (var item : items) {
            insert(item);
        }
    }

    public OptionalInt getMin() {
        if(minItem.isEmpty())
            return OptionalInt.empty();
        else
            return OptionalInt.of(minItem.get().value);
    }

    public void insert(int itemValue) {
        var item = new Item();
        item.value = itemValue;
        if (minItem.isEmpty()) {
            minItem = Optional.of(item);
            return;
        }

        item.right = minItem.get().right;
        item.left = minItem.get();
        minItem.get().right.left = item;
        minItem.get().right = item;

        if(minItem.get().value > item.value) {
            minItem = Optional.of(item);
        }
    }
}
