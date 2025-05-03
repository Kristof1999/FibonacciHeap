import java.util.Optional;

public class CircularDoublyLinkedList {
    private Optional<Item> first = Optional.empty();
    private Optional<Item> last = Optional.empty();
    public Optional<Item> parent = Optional.empty();


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
    }

    public void concatenate(CircularDoublyLinkedList other) {
        first.get().left = other.last.get();
        other.last.get().right = first.get();
        last.get().right = other.first.get();
        other.first.get().left = last.get();
    }

    public void setParent(Item parent) {
        this.parent = Optional.of(parent);
    }
}
