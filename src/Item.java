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
            child.get().setParent(this);
        }

        child.get().insertAtEnd(item);
    }

    public int getRank() {
        if (child.isEmpty()) {
            return 0;
        } else {
            return child.get().size;
        }
    }

    @Override
    public String toString() {
        if (child.isPresent()) {
            return value + " - " + child.get().toString();
        } else {
            return value + "";
        }
    }
}
