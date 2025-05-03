import java.util.Optional;
import java.util.OptionalInt;

// src:
// - https://en.wikipedia.org/wiki/Fibonacci_heap
// - MICHAEL L. FREDMAN and ROBERT ENDRE TARJAN: Fibonacci Heaps and Their Uses in Improved Network
//   Optimization Algorithms
public class FibonacciHeap {
    private Optional<Item> minItem = Optional.empty();
    private CircularDoublyLinkedList roots;

    public FibonacciHeap(int[] items) {
        if (items.length == 0)
            return;

        roots = new CircularDoublyLinkedList();
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
            roots.insertAtEnd(item);
            return;
        }

        if(minItem.get().value > item.value) {
            minItem = Optional.of(item);
        }

        roots.insertAtEnd(item);
    }

    public OptionalInt extractMin() {
        if (minItem.isEmpty())
            return OptionalInt.empty();

        var minValue = minItem.get().value;
        roots.delete(minItem.get());
        // each child of the minimum becomes a new root
        if (minItem.get().child.isPresent())
            roots.concatenate(minItem.get().child.get());

        // size could be less...
        // given a rank x as index, it points to a root which has rank x
        Optional<Item>[] rankPointers = new Optional[roots.size];
        for (var i = 0; i < rankPointers.length; i++) {
            rankPointers[i] = Optional.empty();
        }

        var item = roots.first.get();
        minItem = Optional.of(item);
        // assumption: no duplicates
        while(item.value != roots.last.get().value) {
            if(rankPointers[item.getRank()].isEmpty()) {
                rankPointers[item.getRank()] = Optional.of(item);
            } else {
                int newRank = item.getRank();
                while (newRank < rankPointers.length && rankPointers[newRank].isPresent()) {
                    var item1 = rankPointers[item.getRank()].get();
                    rankPointers[item.getRank()] = Optional.empty();
                    var item2 = item;
                    // link
                    if(item1.value > item2.value) {
                        roots.delete(item1);
                        item2.addToChildren(item1);
                        item = item2;
                    } else {
                        roots.delete(item2);
                        item1.addToChildren(item2);
                        item = item1;
                    }
                    newRank++;
                }
                rankPointers[newRank] = Optional.of(item);
            }

            if(minItem.get().value > item.value) {
                minItem = Optional.of(item);
            }

            item = item.right;
        }

        return OptionalInt.of(minValue);
    }
}
