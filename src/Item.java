import java.util.Optional;

public class Item {
    public Optional<Item> parent = Optional.empty();
    public Optional<Item> child = Optional.empty();
    public Item left = this;
    public Item right = this;
    public int value, rank;
    public boolean marked;

    public void setParent(Item parent) {
        this.parent = Optional.of(parent);
    }

    public void setChild(Item child) {
        this.child = Optional.of(child);
    }
}
