import java.util.Optional;

public class Item {
    public Optional<CircularDoublyLinkedList> child = Optional.empty();
    public Item left = this;
    public Item right = this;
    public int value;
    public boolean marked;

    public void addToChildren(Item item) {
        if (child.isEmpty()) {
            child = Optional.of(new CircularDoublyLinkedList());
        }

        child.get().insertAtEnd(item);
        child.get().setParent(this);
    }

    public int getRank() {
        if (child.isEmpty()) {
            return 0;
        } else {
            return child.get().size;
        }
    }
}
