import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CircularDoublyLinkedListTest {

    private Item item1 = new Item();
    private Item item2 = new Item();
    private Item item3 = new Item();

    @BeforeEach
    void setUp() {
        item1.value = 1;
        item2.value = 2;
        item3.value = 3;
    }

    @Test
    public void insert1Test() {
        var list = new CircularDoublyLinkedList();
        list.insertAtEnd(item1);
        assertEquals(item1.value, list.first.get().value);
        assertEquals(item1.value, list.last.get().value);
    }

    @Test
    public void insert2Test() {
        var list = new CircularDoublyLinkedList();
        list.insertAtEnd(item1);
        list.insertAtEnd(item2);
        assertEquals(item1.value, list.first.get().value);
        assertEquals(item2.value, list.last.get().value);
    }

    @Test
    public void deleteFirstTest() {
        var list = new CircularDoublyLinkedList();
        list.insertAtEnd(item1);
        list.insertAtEnd(item2);
        list.insertAtEnd(item3);
        list.delete(list.first.get());
        assertEquals(item2.value, list.first.get().value);
        assertEquals(item3.value, list.last.get().value);
    }

    @Test
    public void deleteMiddleTest() {
        var list = new CircularDoublyLinkedList();
        list.insertAtEnd(item1);
        list.insertAtEnd(item2);
        list.insertAtEnd(item3);
        list.delete(item2);
        assertEquals(item1.value, list.first.get().value);
        assertEquals(item3.value, list.last.get().value);
    }

    @Test
    public void deleteLastTest() {
        var list = new CircularDoublyLinkedList();
        list.insertAtEnd(item1);
        list.insertAtEnd(item2);
        list.insertAtEnd(item3);
        list.delete(list.last.get());
        assertEquals(item1.value, list.first.get().value);
        assertEquals(item2.value, list.last.get().value);
    }

    @Test
    public void delete2Test() {
        var list = new CircularDoublyLinkedList();
        list.insertAtEnd(item1);
        list.insertAtEnd(item2);
        list.insertAtEnd(item3);
        list.delete(list.first.get());
        list.delete(list.first.get());
        assertEquals(item3.value, list.first.get().value);
        assertEquals(item3.value, list.last.get().value);
    }

    @Test
    public void deleteAllTest() {
        var list = new CircularDoublyLinkedList();
        list.insertAtEnd(item1);
        list.insertAtEnd(item2);
        list.insertAtEnd(item3);
        list.delete(list.first.get());
        list.delete(list.first.get());
        list.delete(list.first.get());
        assertEquals(Optional.empty(), list.first);
        assertEquals(Optional.empty(), list.first);
    }

    @Test
    public void concatenateEmptyWithEmpty() {
        var list1 = new CircularDoublyLinkedList();
        var list2 = new CircularDoublyLinkedList();
        list1.concatenate(list2);
        assertEquals(0, list1.size);
    }

    @Test
    public void concatenateNonEmptyWithEmpty() {
        var list1 = new CircularDoublyLinkedList();
        list1.insertAtEnd(item1);
        var list2 = new CircularDoublyLinkedList();
        list1.concatenate(list2);
        assertEquals(1, list1.size);
        assertEquals(item1.value, list1.first.get().value);
        assertEquals(item1.value, list1.last.get().value);
    }

    @Test
    public void concatenateEmptyWithNonEmpty() {
        var list1 = new CircularDoublyLinkedList();
        var list2 = new CircularDoublyLinkedList();
        list2.insertAtEnd(item1);
        list1.concatenate(list2);
        assertEquals(1, list1.size);
        assertEquals(item1.value, list1.first.get().value);
        assertEquals(item1.value, list1.last.get().value);
    }

    @Test
    public void concatenateNonEmptyWithNonEmpty() {
        var list1 = new CircularDoublyLinkedList();
        list1.insertAtEnd(item1);
        var list2 = new CircularDoublyLinkedList();
        list2.insertAtEnd(item2);
        list1.concatenate(list2);
        assertEquals(2, list1.size);
        assertEquals(item1.value, list1.first.get().value);
        assertEquals(item2.value, list1.last.get().value);
    }
}