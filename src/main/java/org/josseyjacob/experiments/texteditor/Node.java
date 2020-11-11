package org.josseyjacob.experiments.texteditor;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Node<T> {

    private final T value;

    private final Node<T> previous;

    @Setter
    private Node<T> next;

    public Node(T value, Node<T> previous) {
        this.value = value;
        this.previous = previous;
    }

    public Node<T> nextValue(T nextValue) {
        this.next = new Node<>(nextValue, this);
        return this.next;
    }
}
