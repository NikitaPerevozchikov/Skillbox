package double_linked_list;

import java.util.Objects;

public class DoubleLinkedList<T> {

  private ListItem<T> head;
  private ListItem<T> tail;
  private int size;

  public ListItem<T> getHeadElement() {
    return head;
  }

  public ListItem<T> getTailElement() {
    return tail;
  }

  public ListItem<T> popHeadElement() {
    ListItem<T> item = null;
    if (head != null) {
      item = head;
      if (head.next != null) {
        head = head.next;
        head.prev = null;
        item.next = null;
      } else {
        tail = null;
        head = null;
      }
      size--;
    }
    return item;
  }

  public ListItem<T> popTailElement() {
    ListItem<T> item = null;
    if (tail != null) {
      item = tail;
      if (tail.prev != null) {
        tail = tail.prev;
        tail.next = null;
        item.prev = null;
      } else {
        tail = null;
        head = null;
      }
      size--;
    }
    return item;
  }

  public void removeHeadElement() {
    if (head.next == null) {
      head = null;
      tail = null;
    }
    if (head != null) {
      head = head.next;
      head.prev = null;
    }
    size--;
  }

  public void removeTailElement() {
    if (tail.prev == null) {
      tail = null;
      head = null;
    }
    if (tail != null) {
      tail = tail.prev;
      tail.next = null;
    }
    size--;
  }

  public void addToHead(T data) {
    ListItem<T> item = new ListItem<>(data);
    size++;
    if (head == null) {
      tail = item;
    } else {
      head.prev = item;
      item.next = head;
    }
    head = item;
  }

  public void addToTail(T data) {
    ListItem<T> item = new ListItem<>(data);
    size++;
    if (tail == null) {
      head = item;
    } else {
      tail.next = item;
      item.prev = tail;
    }
    tail = item;
  }

  public int getSize() {
    return size;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DoubleLinkedList<T> that = (DoubleLinkedList<T>) o;
    return Objects.equals(head, that.head) && Objects.equals(tail, that.tail);
  }

  @Override
  public int hashCode() {
    return Objects.hash(head, tail);
  }

  @Override
  public String toString() {

    StringBuilder stringBuilder = new StringBuilder(head.toString());
    ListItem<T> item = head;
    while (item.next != null) {
      if (item.next.prev == item) {
        stringBuilder.append("<-");
      }

      stringBuilder.append(" -> ").append(item.next);
      item = item.next;
    }

    return "DoubleLinkedList{size=" + size + "\n" + stringBuilder.toString() + "}";
  }
}
