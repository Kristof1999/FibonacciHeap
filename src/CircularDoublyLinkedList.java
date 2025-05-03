import java.util.Optional;

// src: https://en.wikipedia.org/wiki/Doubly_linked_list
public class CircularDoublyLinkedList {
    // invariant: first and last both must be empty or non-empty
    public Optional<Item> first = Optional.empty();
    public Optional<Item> last = Optional.empty();
    public Optional<Item> parent = Optional.empty();
    public int size = 0;

    public void insertAtEnd(Item item) {
        if (first.isPresent()) {
            item.right = last.get().right;
            item.left = last.get();
            last.get().right.left = item;
            last.get().right = item;
            last = Optional.of(item);
        } else {
            first = Optional.of(item);
            last = Optional.of(item);
        }
        size++;
    }

    public void delete(Item item) {
        if (first.isEmpty())
            throw new IllegalStateException("Deleting from empty list!");

        if (first.get().value == item.value)
            first = Optional.of(first.get().right);

        if (last.get().value == item.value)
            last = Optional.of(last.get().left);

        // assumption: no duplicates
        if (first.get().value == item.value && last.get().value == item.value) {
            first = Optional.empty();
            last = Optional.empty();
        } else {
            item.left.right = item.right;
            item.right.left = item.left;
            item.left = item;
            item.right = item;
        }
        size--;
    }

    public void concatenate(CircularDoublyLinkedList other) {
        first.get().left = other.last.get();
        other.last.get().right = first.get();
        last.get().right = other.first.get();
        other.first.get().left = last.get();
        size += other.size;
    }

    public void setParent(Item parent) {
        this.parent = Optional.of(parent);
    }
}
