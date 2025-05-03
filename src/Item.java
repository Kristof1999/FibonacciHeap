import java.util.Optional;

public class Item {
    public Optional<CircularDoublyLinkedList> child = Optional.empty();
    public Item left = this;
    public Item right = this;
    public int value, rank;
    public boolean marked;

    public void setChild(Item child) {
        if (this.child.isEmpty()) {
            this.child = Optional.of(new CircularDoublyLinkedList());
        } else {
            this.child.get().insertAtEnd(child);
        }
    }
}
