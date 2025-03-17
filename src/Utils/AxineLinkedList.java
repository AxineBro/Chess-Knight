package Utils;

import java.util.*;

class AxineLinkedList<E> implements Iterable<E> {
    private int size = 0;
    private Node<E> first;
    private Node<E> last;

    private class Node<E> {
        E item;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
        Node(E element) {
            this.item = element;
        }
    }

    public void enqueue(E e) {
        Node<E> newNode = new Node<>(e);
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        E item = first.item;
        first = first.next;
        size--;
        if (isEmpty()) {
            last = null;
        }
        return item;
    }

    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return first.item;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        first = last = null;
        size = 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> curr = first;

            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public E next() {
                E value = curr.item;
                curr = curr.next;
                return value;
            }
        };
    }
}